/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 2:16 AM
 * Last modified 8/10/20 2:16 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.SearchPopupUiViewBinding

class SearchResults : AppCompatActivity() {

    lateinit var searchPopupUiViewBinding: SearchPopupUiViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPopupUiViewBinding = SearchPopupUiViewBinding.inflate(layoutInflater)
        setContentView(searchPopupUiViewBinding.root)

        val fullJsonArrayData: String = intent.getStringExtra(Intent.EXTRA_TEXT)

    }

}