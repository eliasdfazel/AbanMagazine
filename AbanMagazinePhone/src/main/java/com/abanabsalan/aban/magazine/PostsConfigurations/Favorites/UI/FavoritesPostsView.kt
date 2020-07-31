/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 8:14 AM
 * Last modified 7/31/20 8:11 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.Utils.Ads.AdsConfiguration
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.FavoritePostsBinding

class FavoritesPostsView : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val favoritesPostsLiveData: PostsLiveData by lazy {
        ViewModelProvider(this@FavoritesPostsView).get(PostsLiveData::class.java)
    }

    val adsConfiguration: AdsConfiguration by lazy {
        AdsConfiguration(this@FavoritesPostsView)
    }

    lateinit var favoritePostsBinding: FavoritePostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritePostsBinding = FavoritePostsBinding.inflate(layoutInflater)

        adsConfiguration.initialize()

        favoritesPostsLiveData.allFavoritedPosts.observe(this@FavoritesPostsView, Observer {



        })

    }

    override fun onResume() {
        super.onResume()

        if (adsConfiguration.interstitialAd.isLoaded) {
            adsConfiguration.interstitialAd.show()
        }

    }

}