package com.gregkluska.shoppingcenter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.api.ApiService
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.persistence.AppDatabase
import com.gregkluska.shoppingcenter.persistence.AppDatabase.Companion.DATABASE_NAME
import com.gregkluska.shoppingcenter.persistence.StoreCategoryDao
import com.gregkluska.shoppingcenter.persistence.StoreDao
import com.gregkluska.shoppingcenter.repository.main.MainRepository
import com.gregkluska.shoppingcenter.util.Constants.Companion.BASE_URL
import com.gregkluska.shoppingcenter.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder) : ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideMainRepository(
        storeDao: StoreDao,
        storeCategoryDao: StoreCategoryDao,
        apiService: ApiService
    ): MainRepository {
        return MainRepository(storeDao, storeCategoryDao, apiService)
    }

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext app: Context): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideStoreDao(db: AppDatabase): StoreDao {
        return db.getStoreDao();
    }

    @Singleton
    @Provides
    fun provideStoreCategoryDao(db: AppDatabase): StoreCategoryDao {
        return db.getStoreCategoryDao();
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext app: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(app)
            .setDefaultRequestOptions(requestOptions)
    }

}