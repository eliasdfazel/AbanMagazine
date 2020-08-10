/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 6:09 AM
 * Last modified 8/10/20 6:09 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder.SearchResultJsonStructure
import com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder.SearchResultsLiveData
import com.abanabsalan.aban.magazine.SearchConfigurations.UI.Adapter.SearchResultsPostsAdapter
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.SearchResultsViewBinding
import org.json.JSONArray

class SearchResults : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    lateinit var searchResultsViewBinding: SearchResultsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchResultsViewBinding = SearchResultsViewBinding.inflate(layoutInflater)
        setContentView(searchResultsViewBinding.root)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeDark -> {

                searchResultsViewBinding.root.setBackgroundColor(getColor(R.color.dark))

                window.statusBarColor = getColor(R.color.dark)
                window.navigationBarColor = getColor(R.color.dark)

            }
            ThemeType.ThemeLight -> {

                searchResultsViewBinding.root.setBackgroundColor(getColor(R.color.light))

                window.statusBarColor = getColor(R.color.light)
                window.navigationBarColor = getColor(R.color.light)

            }
        }

        searchResultsViewBinding.root.post {
            searchResultsViewBinding.loadingView.playAnimation()
        }

        val fullRawJsonArrayData: String = intent.getStringExtra(Intent.EXTRA_TEXT)

        val fullJsonArrayData: JSONArray = JSONArray(fullRawJsonArrayData)

        val listOfPostIds: MutableMap<String, String> = HashMap<String, String>()

        for (i in 0 until fullJsonArrayData.length()) {

            val postId = fullJsonArrayData.getJSONObject(i).getString(SearchResultJsonStructure.SearchResultPostId)


            listOfPostIds[postId] = postId

        }

        searchResultsViewBinding.searchResultRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 379), RecyclerView.VERTICAL, false)

        val searchResultsPostsAdapter: SearchResultsPostsAdapter = SearchResultsPostsAdapter(this@SearchResults, overallTheme)

        val searchResultsLiveData = ViewModelProvider(this@SearchResults).get(SearchResultsLiveData::class.java)

        searchResultsLiveData.allSearchResultsPosts.observe(this@SearchResults, Observer {

            if (it.isNotEmpty()) {

                searchResultsViewBinding.loadingView.pauseAnimation()
                searchResultsViewBinding.loadingView.visibility = View.INVISIBLE

                searchResultsPostsAdapter.postsItemData.clear()

                searchResultsPostsAdapter.postsItemData.addAll(it)

                searchResultsViewBinding.searchResultRecyclerView.adapter = searchResultsPostsAdapter

            }

        })

        searchResultsLiveData.retrieveAllDataOfSearchResults(searchResultsLiveData.getSearchResultPostsEndpoints(listOfPostIds))

    }

    override fun onBackPressed() {

        this@SearchResults.finish()
        overridePendingTransition(0, R.anim.fade_out)

    }

}