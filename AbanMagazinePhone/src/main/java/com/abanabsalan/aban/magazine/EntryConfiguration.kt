/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 12:23 PM
 * Last modified 6/25/20 12:23 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

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

        startActivity(Intent(applicationContext, HomePage::class.java))

    }
}