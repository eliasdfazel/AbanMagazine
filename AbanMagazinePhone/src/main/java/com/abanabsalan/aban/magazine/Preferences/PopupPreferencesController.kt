/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/27/20 10:21 PM
 * Last modified 7/27/20 10:21 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.content.Intent
import android.net.Uri
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.SharePost
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.PreferencesPopupUiViewBinding

class PopupPreferencesController (private val context: AppCompatActivity,
                                  private val preferencesPopupUiViewBinding: PreferencesPopupUiViewBinding) {

    private val overallTheme: OverallTheme by lazy {
        OverallTheme(context)
    }

    init {

        when (context) {
            is HomePage -> {

                initialThemeToggleAction()

                socialMediaActionHomePage()

            }
            is PostView -> {

                initialThemeToggleAction()

                socialMediaActionPostView()

            }
        }


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
                is PostView -> {
                    (context as PostView).postsLiveData.toggleTheme.postValue(overallTheme.checkThemeLightDark())
                }
            }

        }

        preferencesPopupUiViewBinding.root.setOnClickListener {

            when(context) {
                is HomePage -> {
                    (context as HomePage).hidePopupPreferences()
                }
                is PostView -> {
                    (context as PostView).hidePopupPreferences()
                }
            }

        }

    }

    private fun socialMediaActionHomePage() {

        val instagramViewLayoutParams = preferencesPopupUiViewBinding.instagramView.layoutParams as ConstraintLayout.LayoutParams
        instagramViewLayoutParams.bottomMargin = navigationBarHeight(context)
        preferencesPopupUiViewBinding.instagramView.layoutParams = instagramViewLayoutParams

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

        preferencesPopupUiViewBinding.rateView.setOnClickListener {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.playStoreLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

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

    private fun socialMediaActionPostView() {

        val instagramViewLayoutParams = preferencesPopupUiViewBinding.instagramView.layoutParams as ConstraintLayout.LayoutParams
        instagramViewLayoutParams.bottomMargin = navigationBarHeight(context)
        preferencesPopupUiViewBinding.instagramView.layoutParams = instagramViewLayoutParams

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

        preferencesPopupUiViewBinding.rateView.setOnClickListener {

            /*
            * Change Icon To Save As Favorite
            * */

        }

        preferencesPopupUiViewBinding.shareView.setOnClickListener {

            SharePost(context).invoke(
                sharePostTitle = Html.fromHtml((context as PostView).postTitle?:context.getString(R.string.applicationName)).toString(),
                sharePostExcerpt = Html.fromHtml((context as PostView).postExcerpt.toString()).toString(),
                sharePostLink = (context as PostView).postLink?:context.getString(R.string.playStoreLink)
            )

        }

    }

}