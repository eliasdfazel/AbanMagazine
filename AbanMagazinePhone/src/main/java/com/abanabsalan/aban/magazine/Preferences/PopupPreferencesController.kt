/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/8/20 5:02 AM
 * Last modified 8/8/20 4:41 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils.FavoriteInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils.FavoriteIt
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.SharePost
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.InApplicationReview.InApplicationReviewProcess
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.PreferencesPopupUiViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.coroutines.Job

class PopupPreferencesController (private val context: AppCompatActivity,
                                  private val preferencesPopupUiViewBinding: PreferencesPopupUiViewBinding) {

    private val overallTheme: OverallTheme by lazy {
        OverallTheme(context)
    }

    /* Home */
    fun initializeForHomePage() {

        initialThemeToggleAction()

        socialMediaActionHomePage()

    }

    private fun initialThemeToggleAction() {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {
                preferencesPopupUiViewBinding.toggleTheme.apply {
                    speed = 3.33f
                    setMinAndMaxFrame(0, 7)
                }.playAnimation()
            }
            ThemeType.ThemeDark -> {
                preferencesPopupUiViewBinding.toggleTheme.apply {
                    speed = -3.33f
                    setMinAndMaxFrame(90, 99)
                }.playAnimation()
            }
        }

        toggleLightDarkTheme()

    }

    private fun toggleLightDarkTheme() {

        preferencesPopupUiViewBinding.toggleTheme.setOnClickListener { view ->

            preferencesPopupUiViewBinding.toggleTheme.also {

                when (overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {

                        it.speed = 1.130f
                        it.setMinAndMaxFrame(7, 90)

                        if (!it.isAnimating) {
                            it.playAnimation()
                        }

                        overallTheme.saveThemeLightDark(ThemeType.ThemeDark)

                    }
                    ThemeType.ThemeDark -> {

                        it.speed = -1.130f
                        it.setMinAndMaxFrame(7, 90)

                        if (!it.isAnimating) {
                            it.playAnimation()
                        }

                        overallTheme.saveThemeLightDark(ThemeType.ThemeLight)

                    }
                }

            }

            when(context) {
                is HomePage -> {
                    (context as HomePage).homePageLiveData.toggleTheme.postValue(true)
                }
                is SinglePostView -> {
                    (context as SinglePostView).postsLiveData.toggleTheme.postValue(overallTheme.checkThemeLightDark())
                }
            }

        }

        preferencesPopupUiViewBinding.root.setOnClickListener {

            when(context) {
                is HomePage -> {
                    (context as HomePage).hidePopupPreferences()
                }
                is SinglePostView -> {
                    (context as SinglePostView).hidePopupPreferences()
                }
            }

        }

    }

    private fun socialMediaActionHomePage() {

        val instagramViewLayoutParams = preferencesPopupUiViewBinding.instagramView.layoutParams as ConstraintLayout.LayoutParams
        instagramViewLayoutParams.bottomMargin = navigationBarHeight(context)
        preferencesPopupUiViewBinding.instagramView.layoutParams = instagramViewLayoutParams

        preferencesPopupUiViewBinding.rateFavoriteView.setImageDrawable(context.getDrawable(R.drawable.rate_icon))
        preferencesPopupUiViewBinding.shareView.setImageDrawable(context.getDrawable(R.drawable.share_icon))

        preferencesPopupUiViewBinding.instagramView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.instagramLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.twitterView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.twitterLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.pinterestView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.pinterestLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.youtubeView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.youtubeLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.rateFavoriteView.setOnClickListener {

            InApplicationReviewProcess(context).start()

        }

        preferencesPopupUiViewBinding.shareView.setOnClickListener {

            val shareText: String = "مجله آبان | بوستان مد و استایل" +
                    "\n" +
                    "مجله اینترنتی تخصصی مد و استایل" +
                    "\n" + "\n" +
                    "نصب برنامه" +
                    "\n" +
                    "${context.getString(R.string.playStoreLink)}" +
                    "\n" + "\n" +
                    "https://www.AbanAbsalan.com" +
                    "\n" +
                    "#AbanAbsalan" +
                    "\n" +
                    "#آبان_آبسالان" +
                    ""

            val shareIntent: Intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(shareIntent)

        }

    }

    /* Posts */
    fun initializeForPostView(postId: String?) {

        initialThemeToggleAction()

        socialMediaActionPostView(postId)

    }

    private fun socialMediaActionPostView(postId: String?) {

        val favoriteIt: FavoriteIt =
            FavoriteIt(
                context
            )

        Handler().postDelayed({

            Glide.with(context)
                .asGif()
                .load(R.raw.share_animation)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(CenterInside(), RoundedCorners(23))
                .into(preferencesPopupUiViewBinding.shareView)

        }, 531)

        postId?.let {

            if (favoriteIt.isFavorited(postId)) {

                preferencesPopupUiViewBinding.rateFavoriteView.setAnimation(R.raw.favorite_it_animation)
                preferencesPopupUiViewBinding.rateFavoriteView.setMinAndMaxFrame(0, 21)
                preferencesPopupUiViewBinding.rateFavoriteView.playAnimation()

            } else {

                preferencesPopupUiViewBinding.rateFavoriteView.setImageDrawable(context.getDrawable(R.drawable.unfavorite_it_icon))

            }

        }

        preferencesPopupUiViewBinding.instagramView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.instagramLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.twitterView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.twitterLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.pinterestView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.pinterestLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.youtubeView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.youtubeLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }

        preferencesPopupUiViewBinding.rateFavoriteView.setOnClickListener {

            favoriteIt.favoriteInterface =  object :
                FavoriteInterface {

                override fun favoritedIt() : Job {
                    Log.d(FavoriteIt.PreferenceName, "${postId} Favorited")

                    preferencesPopupUiViewBinding.rateFavoriteView.setAnimation(R.raw.favorite_it_animation)
                    preferencesPopupUiViewBinding.rateFavoriteView.setMinAndMaxFrame(0, 21)
                    preferencesPopupUiViewBinding.rateFavoriteView.playAnimation()

                    //Start Room Database Process

                    return super.favoritedIt()
                }

                override fun unfavoritedIt() : Job {
                    Log.d(FavoriteIt.PreferenceName, "${postId} Unfavorited")

                    preferencesPopupUiViewBinding.rateFavoriteView.setImageDrawable(context.getDrawable(R.drawable.unfavorite_it_icon))

                    //Start Room Database Process

                    return super.unfavoritedIt()
                }

            }

            postId?.let {

                FavoriteIt.FavoriteDataChanged = true

                if (favoriteIt.isFavorited(postId)) {

                    favoriteIt.deleteFavorite(it)

                } else {

                    favoriteIt.saveAsFavorite(it)

                }

            }

        }

        preferencesPopupUiViewBinding.shareView.setOnClickListener {

            SharePost(context).invoke(
                sharePostTitle = Html.fromHtml((context as SinglePostView).postTitle?:context.getString(R.string.applicationName)).toString(),
                sharePostExcerpt = Html.fromHtml((context as SinglePostView).postExcerpt.toString()).toString(),
                sharePostLink = (context as SinglePostView).postLink?:context.getString(R.string.playStoreLink)
            )

        }

    }

}