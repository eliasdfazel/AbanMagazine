/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 10/20/20 8:59 AM
 * Last modified 10/20/20 8:58 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.SearchConfigurations.RemoteQuery.Extensions.showPopupSearches
import com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils.SetupSearchViewUI
import com.abanabsalan.aban.magazine.databinding.RemoteSearchViewBinding

class SearchRemoteQuery : AppCompatActivity() {

    lateinit var remoteSearchViewBinding: RemoteSearchViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        remoteSearchViewBinding = RemoteSearchViewBinding.inflate(layoutInflater)

        val remoteSearchQuery = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)

        remoteSearchQuery?.let {

            SetupSearchViewUI(
                this@SearchRemoteQuery,
                remoteSearchViewBinding.searchPopupInclude
            )

            showPopupSearches()

        }

    }

    override fun onBackPressed() {

        this@SearchRemoteQuery.finish()

    }

}