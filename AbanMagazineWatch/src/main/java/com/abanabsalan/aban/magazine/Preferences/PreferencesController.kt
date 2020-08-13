/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 3:11 AM
 * Last modified 8/13/20 2:54 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.PreferencesRootViewWatchBinding

class PreferencesController : AppCompatActivity() {

    lateinit var preferencesRootViewBinding: PreferencesRootViewWatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesRootViewBinding = PreferencesRootViewWatchBinding.inflate(layoutInflater)
        setContentView(preferencesRootViewBinding.root)



    }

    override fun onPause() {
        super.onPause()

        this@PreferencesController.finish()

    }
}