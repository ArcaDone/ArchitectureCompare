package com.ddd.architecturecompare.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ddd.architecturecompare.R
import com.ddd.architecturecompare.model.RedditItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RedditItem>?) {
    val adapter = recyclerView.adapter as ItemGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}
