/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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