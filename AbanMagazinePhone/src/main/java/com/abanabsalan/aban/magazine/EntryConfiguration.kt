/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/27/20 11:26 AM
 * Last modified 6/27/20 10:45 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.databinding.EntryConfigurationViewBinding


class EntryConfiguration : AppCompatActivity() {

    private lateinit var entryConfigurationViewBinding: EntryConfigurationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryConfigurationViewBinding = EntryConfigurationViewBinding.inflate(layoutInflater)
        setContentView(entryConfigurationViewBinding.root)

        startActivity(Intent(applicationContext, HomePage::class.java),
            ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

    }
}