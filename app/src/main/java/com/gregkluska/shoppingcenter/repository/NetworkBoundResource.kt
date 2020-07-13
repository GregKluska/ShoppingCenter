package com.gregkluska.shoppingcenter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gregkluska.shoppingcenter.util.GenericApiResponse
import com.gregkluska.shoppingcenter.util.GenericApiResponse.*
import com.gregkluska.shoppingcenter.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <CacheObject>
 * @param <RequestObject>
</RequestType></ResultType> */
abstract class NetworkBoundResource<CacheObject, RequestObject> {

    private val result = MediatorLiveData<Resource<CacheObject>>()

    private val job = Job()

    init {
        val context = Dispatchers.IO
        context + job

        CoroutineScope(context).launch {
            result.value = Resource.loading(null)
            val dbSource = loadFromDb()
            result.addSource(dbSource) { data ->
                result.removeSource(dbSource)
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource)
                } else {
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<CacheObject>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    //saveCallResult(processResponse(response))
                    // we specially request a new live data,
                    // otherwise we will get immediately last cached value,
                    // which may not be updated with latest results received from network.
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }

                }
                is ApiEmptyResponse -> {
                    // reload from disk whatever we had
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    private fun setValue(newValue: Resource<CacheObject>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected abstract fun onFetchFailed()

    protected abstract fun shouldFetch(data: CacheObject?): Boolean

    protected abstract fun loadFromDb(): LiveData<CacheObject>

    protected abstract fun createCall(): LiveData<GenericApiResponse<RequestObject>>

    protected abstract suspend fun saveCallResult(item: RequestObject)
}