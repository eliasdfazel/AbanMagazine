/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/15/20 9:54 AM
 * Last modified 11/15/20 9:05 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Ads

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.google.android.gms.ads.*

class AdsConfiguration (private val appCompatActivity: AppCompatActivity) {

    var getInterstitialAd: InterstitialAd? = null

    fun initialize() {

        if (!BuildConfig.DEBUG) {

            MobileAds.initialize(appCompatActivity) { initializationStatus -> }

            interstitialAdsLoadShow()

            bannerAdsLoadShow()

            val testDeviceIds = listOf("3E192B3766F6EDE8127A5ADFAA0E7B67", "A06676F37C8588BFF7D434B66274567A", "F54D998BCE077711A17272B899B44798")

            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build()
            MobileAds.setRequestConfiguration(configuration)

        }

    }

    private fun interstitialAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        val interstitialAd: InterstitialAd = InterstitialAd(appCompatActivity)
        this@AdsConfiguration.getInterstitialAd = interstitialAd

        interstitialAd.setImmersiveMode(true)

        when(appCompatActivity) {
            is HomePage -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Home Page Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.homePageInterstitial)

            }
            is SinglePostView -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Post View Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.postViewInterstitial)

            }
            is FavoritesPostsView -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Favorites Posts View Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.favoritesPostsViewInterstitial)

            }
        }

        interstitialAd.loadAd(adRequest)
        interstitialAd.adListener = object : AdListener() {

            override fun onAdLoaded() {
                super.onAdLoaded()

                if (!appCompatActivity.isFinishing) {
                    interstitialAd.show()
                }

            }

            override fun onAdFailedToLoad(errorCode: LoadAdError) {
                super.onAdFailedToLoad(errorCode)

                interstitialAd.loadAd(adRequest)

            }

        }

        interstitialAd.setOnPaidEventListener {

        }

    }

    private fun bannerAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        when(appCompatActivity) {
            is HomePage -> {

                (appCompatActivity).homePageViewBinding.bannerAdViewTop.loadAd(adRequest)

                (appCompatActivity).homePageViewBinding.bannerAdViewTop.adListener = object: AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).homePageViewBinding.bannerAdViewTop.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (appCompatActivity).homePageViewBinding.bannerAdViewTop.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdLeftApplication() {

                    }

                    override fun onAdClosed() {

                    }

                }

                (appCompatActivity).homePageViewBinding.bannerAdViewBottom.loadAd(adRequest)

                (appCompatActivity).homePageViewBinding.bannerAdViewBottom.adListener = object: AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).homePageViewBinding.bannerAdViewBottom.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (appCompatActivity).homePageViewBinding.bannerAdViewBottom.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdLeftApplication() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
            is SinglePostView -> {

                (appCompatActivity).postsViewUiBinding.bannerAdView.loadAd(adRequest)

                (appCompatActivity).postsViewUiBinding.bannerAdView.adListener = object: AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).postsViewUiBinding.bannerAdView.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (appCompatActivity).postsViewUiBinding.bannerAdView.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdLeftApplication() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
            is FavoritesPostsView -> {

                (appCompatActivity).favoritePostsBinding.bannerAdView.loadAd(adRequest)

                (appCompatActivity).favoritePostsBinding.bannerAdView.adListener = object: AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).favoritePostsBinding.bannerAdView.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (appCompatActivity).favoritePostsBinding.bannerAdView.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdLeftApplication() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
        }

    }

}