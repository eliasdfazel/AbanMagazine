/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 10/20/20 9:33 AM
 * Last modified 10/20/20 9:32 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.RemoteQuery.Extensions.showPopupSearches
import com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils.SetupSearchViewUI
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.RemoteSearchViewBinding

class SearchRemoteQuery : AppCompatActivity() {

    lateinit var remoteSearchViewBinding: RemoteSearchViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        remoteSearchViewBinding = RemoteSearchViewBinding.inflate(layoutInflater)
        setContentView(remoteSearchViewBinding.root)

        val overallTheme = OverallTheme(applicationContext)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                remoteSearchViewBinding.searchPopupInclude.searchesBlurView.setOverlayColor(getColor(R.color.default_color_light_transparent))

            }
            ThemeType.ThemeDark -> {

                remoteSearchViewBinding.searchPopupInclude.searchesBlurView.setOverlayColor(getColor(R.color.default_color_dark_transparent))

            }
        }

        val remoteSearchQuery = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)

        remoteSearchViewBinding.rootView.post {

            remoteSearchQuery?.let {

                SetupSearchViewUI(
                    this@SearchRemoteQuery,
                    remoteSearchViewBinding.searchPopupInclude
                )

                showPopupSearches()

            }

        }

    }

    override fun onBackPressed() {

        this@SearchRemoteQuery.finish()

    }

}