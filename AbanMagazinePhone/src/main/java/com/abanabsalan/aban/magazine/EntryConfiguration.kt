/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/23/20 11:43 AM
 * Last modified 6/23/20 11:42 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.EntryConfigurationViewBinding

class EntryConfiguration : AppCompatActivity() {

    private lateinit var entryConfigurationViewBinding: EntryConfigurationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryConfigurationViewBinding = EntryConfigurationViewBinding.inflate(layoutInflater)
        setContentView(entryConfigurationViewBinding.root)
    }
}