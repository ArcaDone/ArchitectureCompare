package com.ddd.architecturecompare.mvc

import android.util.Log
import com.ddd.architecturecompare.model.RedditItem
import com.ddd.architecturecompare.model.RedditResponse
import com.ddd.architecturecompare.model.RedditService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvcController(private val view: MvcFragment, private val search: String) {

    init {
        fetchItems()
    }

    private fun fetchItems() {
        RedditService().getResult(search).enqueue(object : Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Log.e("MyLog", "Error -> ${t.message}")
                view.onError()
            }

            override fun onResponse(
                call: Call<RedditResponse>,
                response: Response<RedditResponse>
            ) {
                val redditItemList = response.body()?.data?.children?.map {
                    val item = it.data
                    RedditItem(item.title, item.thumbnail)
                }
                view.setValues(redditItemList ?: mutableListOf())
                Log.d("MyLog", "Success -> size: ${redditItemList?.size}")
            }
        })
    }

    fun onRefresh() {
        fetchItems()
    }
}
