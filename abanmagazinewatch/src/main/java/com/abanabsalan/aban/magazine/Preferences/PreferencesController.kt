/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 7/19/20 11:10 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.PreferencesRootViewBinding

class PreferencesController : AppCompatActivity() {

    lateinit var preferencesRootViewBinding: PreferencesRootViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesRootViewBinding = PreferencesRootViewBinding.inflate(layoutInflater)
        setContentView(preferencesRootViewBinding.root)



    }

    override fun onPause() {
        super.onPause()

        this@PreferencesController.finish()

    }
}