package com.gregkluska.shoppingcenter.ui.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.gregkluska.shoppingcenter.R
import com.gregkluska.shoppingcenter.models.Store
import com.gregkluska.shoppingcenter.models.StoreCategory
import kotlinx.android.synthetic.main.layout_store_list_item.view.*

class MainRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CATEGORY_TYPE = 1
        const val STORE_TYPE = 2
    }

    @Suppress("PrivatePropertyName")
    private val CATEGORY_DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoreCategory>() {
        override fun areItemsTheSame(oldItem: StoreCategory, newItem: StoreCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoreCategory, newItem: StoreCategory): Boolean {
            return oldItem == newItem
        }
    }

    @Suppress("PrivatePropertyName")
    private val STORE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }
    }

    private val categoryDiffer = AsyncListDiffer(this, CATEGORY_DIFF_CALLBACK)
    private val storeDiffer = AsyncListDiffer(this, STORE_DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view : View

        when (viewType) {
            CATEGORY_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_category_list_item,
                    parent,
                    false
                )
                return StoreCategoryViewHolder(view, interaction)
            }
            STORE_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_store_list_item,
                    parent,
                    false
                )
                return StoreViewHolder(view, interaction)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_category_list_item,
                    parent,
                    false
                )
                return StoreCategoryViewHolder(view, interaction)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(storeDiffer.currentList.size > 0) {
            return STORE_TYPE
        }
        return CATEGORY_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoreCategoryViewHolder -> {
                holder.bind(categoryDiffer.currentList[position])
            }

            is StoreViewHolder -> {
                holder.bind(storeDiffer.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryDiffer.currentList.size + storeDiffer.currentList.size
    }

    fun submitCategoryList(list: List<StoreCategory>) {
        storeDiffer.submitList(null)
        categoryDiffer.submitList(list)
    }

    fun submitStoreList(list: List<Store>) {
        categoryDiffer.submitList(null)
        storeDiffer.submitList(list)
    }

    class StoreCategoryViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: StoreCategory) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onCategoryItemSelected(adapterPosition, item)
            }
            Log.d("AppDebug", "categoryItem: $item")
        }
    }

    class StoreViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Store) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onStoreItemSelected(adapterPosition, item)
            }

            itemView.store_name.text = item.name
            Log.d("AppDebug", "storeItem: $item")
        }
    }

    interface Interaction {
        fun onCategoryItemSelected(position: Int, item: StoreCategory)
        fun onStoreItemSelected(position: Int, item: Store)
    }
}