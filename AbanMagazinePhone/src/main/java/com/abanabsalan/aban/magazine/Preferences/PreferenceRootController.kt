/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 10:54 PM
 * Last modified 7/19/20 10:40 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.Preferences.UI.PreferenceFragmentUI
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.databinding.PreferencesRootViewBinding

class PreferenceRootController : AppCompatActivity() {

    lateinit var preferencesRootViewBinding: PreferencesRootViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesRootViewBinding = PreferencesRootViewBinding.inflate(layoutInflater)
        setContentView(preferencesRootViewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentPlaceholder, PreferenceFragmentUI(), "PreferencesFragment")
            .commit()

    }

    override fun onPause() {
        super.onPause()

        this@PreferenceRootController.finish()

    }
}