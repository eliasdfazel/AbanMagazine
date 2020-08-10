/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 5:10 AM
 * Last modified 8/10/20 5:09 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder.SearchResultJsonStructure
import com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder.SearchResultsLiveData
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.SearchPopupUiViewBinding
import org.json.JSONArray

class SearchResults : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    lateinit var searchPopupUiViewBinding: SearchPopupUiViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPopupUiViewBinding = SearchPopupUiViewBinding.inflate(layoutInflater)
        setContentView(searchPopupUiViewBinding.root)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeDark -> {

                window.statusBarColor = getColor(R.color.dark)
                window.navigationBarColor = getColor(R.color.dark)

            }
            ThemeType.ThemeLight -> {

                window.statusBarColor = getColor(R.color.light)
                window.navigationBarColor = getColor(R.color.light)

            }
        }

        val fullRawJsonArrayData: String = intent.getStringExtra(Intent.EXTRA_TEXT)

        val fullJsonArrayData: JSONArray = JSONArray(fullRawJsonArrayData)

        val listOfPostIds: MutableMap<String, String> = HashMap<String, String>()

        for (i in 0 until fullJsonArrayData.length()) {

            val postId = fullJsonArrayData.getJSONObject(i).getString(SearchResultJsonStructure.SearchResultPostId)


            listOfPostIds[postId] = postId

        }

        val searchResultsLiveData = ViewModelProvider(this@SearchResults).get(SearchResultsLiveData::class.java)

        searchResultsLiveData.allSearchResultsPosts.observe(this@SearchResults, Observer {

            if (it.isNotEmpty()) {



            }

        })

        searchResultsLiveData.retrieveAllDataOfSearchResults(searchResultsLiveData.getSearchResultPostsEndpoints(listOfPostIds))

    }

    override fun onBackPressed() {

        this@SearchResults.finish()
        overridePendingTransition(0, R.anim.fade_out)

    }

}