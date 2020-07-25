/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 2:56 AM
 * Last modified 7/25/20 2:56 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
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

        initialThemeToggleAction()

        socialMediaAction()

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

    private fun socialMediaAction() {

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

}