/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/20/20 6:40 PM
 * Last modified 7/20/20 6:37 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.Utils.UI.Theme.toggleLightDarkThemeHomePage
import com.abanabsalan.aban.magazine.databinding.PreferencesPopupUiViewBinding

class PopupPreferencesController (private val context: AppCompatActivity, private val preferencesPopupUiViewBinding: PreferencesPopupUiViewBinding) {

    private val overallTheme: OverallTheme by lazy {
        OverallTheme(context)
    }

    init {

        initialThemeToggleAction()

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

            Handler().postDelayed({

                when (context) {
                    is HomePage -> {

                        toggleLightDarkThemeHomePage(context as HomePage)

                    }
                }

            }, 2468)


        }

    }

}