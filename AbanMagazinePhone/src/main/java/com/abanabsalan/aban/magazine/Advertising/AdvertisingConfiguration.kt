/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 5/10/21, 4:30 AM
 * Last modified 5/10/21, 4:30 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Advertising

import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdvertisingConfiguration(private val appCompatActivity: AppCompatActivity) {

    var getInterstitialAd: InterstitialAd? = null

    fun initialize() {

//        if (!BuildConfig.DEBUG) {

            MobileAds.initialize(appCompatActivity) { initializationStatus -> }

            interstitialAdsLoadShow()

            bannerAdsLoadShow()

            val testDeviceIds = listOf(
                "3E192B3766F6EDE8127A5ADFAA0E7B67",
                "A06676F37C8588BFF7D434B66274567A",
                "F54D998BCE077711A17272B899B44798"
            )

            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build()
            MobileAds.setRequestConfiguration(configuration)

//        }

    }

    private fun interstitialAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        var adUnitId = appCompatActivity.getString(R.string.homePageInterstitial)

        when(appCompatActivity) {
            is HomePage -> {
                Log.d(
                    this@AdvertisingConfiguration.javaClass.simpleName,
                    "Home Page Requesting Ads"
                )

                adUnitId = appCompatActivity.getString(R.string.homePageInterstitial)

            }
            is SinglePostView -> {
                Log.d(
                    this@AdvertisingConfiguration.javaClass.simpleName,
                    "Post View Requesting Ads"
                )

                adUnitId = appCompatActivity.getString(R.string.postViewInterstitial)

            }
            is FavoritesPostsView -> {
                Log.d(
                    this@AdvertisingConfiguration.javaClass.simpleName,
                    "Favorites Posts View Requesting Ads"
                )

                adUnitId = appCompatActivity.getString(R.string.favoritesPostsViewInterstitial)

            }
        }

        InterstitialAd.load(appCompatActivity, adUnitId, adRequest, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {

            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                interstitialAd.setImmersiveMode(true)

                if (!appCompatActivity.isFinishing) {
                    interstitialAd.show(appCompatActivity)
                }

                this@AdvertisingConfiguration.getInterstitialAd = interstitialAd

                interstitialAd.setOnPaidEventListener {

                }

            }
        })

    }

    private fun bannerAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        when(appCompatActivity) {
            is HomePage -> {

                /* Top Banner */
                val bannerAdViewTop = AdView(appCompatActivity)

                bannerAdViewTop.adUnitId = appCompatActivity.getString(R.string.homePageBannerTop)
                bannerAdViewTop.adSize = bannerAdsSize((appCompatActivity).homePageViewBinding.bannerAdViewTop)

                (appCompatActivity).homePageViewBinding.bannerAdViewTop.addView(bannerAdViewTop)

                (appCompatActivity).homePageViewBinding.bannerAdViewTop.post {
                    bannerAdViewTop.loadAd(adRequest)
                }

                bannerAdViewTop.adListener = object : AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).homePageViewBinding.bannerAdViewTop.visibility = View.VISIBLE

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                        bannerAdViewTop.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                }

                /* Bottom Banner */
                val bannerAdViewBottom = AdView(appCompatActivity)

                bannerAdViewBottom.adUnitId = appCompatActivity.getString(R.string.homePageBannerBottom)
                bannerAdViewBottom.adSize = bannerAdsSize((appCompatActivity).homePageViewBinding.bannerAdViewBottom)

                (appCompatActivity).homePageViewBinding.bannerAdViewBottom.addView(bannerAdViewBottom)

                (appCompatActivity).homePageViewBinding.bannerAdViewBottom.post {
                    bannerAdViewBottom.loadAd(adRequest)
                }

                bannerAdViewBottom.adListener = object : AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).homePageViewBinding.bannerAdViewBottom.visibility = View.VISIBLE

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                        bannerAdViewBottom.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
            is SinglePostView -> {

                val bannerAdView = AdView(appCompatActivity)

                bannerAdView.adUnitId = appCompatActivity.getString(R.string.postViewBanner)
                bannerAdView.adSize = bannerAdsSize((appCompatActivity).postsViewUiBinding.bannerAdView)

                (appCompatActivity).postsViewUiBinding.bannerAdView.addView(bannerAdView)

                (appCompatActivity).postsViewUiBinding.bannerAdView.post {
                    bannerAdView.loadAd(adRequest)
                }

                bannerAdView.adListener = object : AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).postsViewUiBinding.bannerAdView.visibility = View.VISIBLE

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                        bannerAdView.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
            is FavoritesPostsView -> {

                val bannerAdView = AdView(appCompatActivity)

                bannerAdView.adUnitId = appCompatActivity.getString(R.string.postViewBanner)
                bannerAdView.adSize = bannerAdsSize((appCompatActivity).favoritePostsBinding.bannerAdView)

                (appCompatActivity).favoritePostsBinding.bannerAdView.addView(bannerAdView)

                (appCompatActivity).favoritePostsBinding.bannerAdView.post {
                    bannerAdView.loadAd(adRequest)
                }

                bannerAdView.adListener = object : AdListener() {

                    override fun onAdLoaded() {

                        (appCompatActivity).favoritePostsBinding.bannerAdView.visibility = View.VISIBLE

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                        bannerAdView.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdClosed() {

                    }

                }

            }
        }

    }

    private fun bannerAdsSize(adViewContainer: FrameLayout) : AdSize {

        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {

            val displayMetrics = DisplayMetrics()

            appCompatActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            val density = displayMetrics.density

            var adsBannerWidthPixels = adViewContainer.width.toFloat()

            if (adsBannerWidthPixels == 0f) {
                adsBannerWidthPixels = displayMetrics.widthPixels.toFloat()
            }

            val adWidth = (adsBannerWidthPixels / density).toInt()

            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(appCompatActivity, adWidth)

        } else {

            val displayMetrics = DisplayMetrics()

            appCompatActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            val density = displayMetrics.density

            var adsBannerWidthPixels = adViewContainer.width.toFloat()

            if (adsBannerWidthPixels == 0f) {
                adsBannerWidthPixels = displayMetrics.widthPixels.toFloat()
            }

            val adWidth = (adsBannerWidthPixels / density).toInt()

            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(appCompatActivity, adWidth)

        }

    }

}