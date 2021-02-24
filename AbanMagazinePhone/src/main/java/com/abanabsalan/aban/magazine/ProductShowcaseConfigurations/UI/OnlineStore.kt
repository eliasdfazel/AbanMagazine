/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/24/21 9:53 AM
 * Last modified 2/24/21 9:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions.setupOnlineStoreUserInterface
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.OnlineStoreLayoutBinding

class OnlineStore : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    lateinit var onlineStoreLayoutBinding: OnlineStoreLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onlineStoreLayoutBinding = OnlineStoreLayoutBinding.inflate(layoutInflater)
        setContentView(onlineStoreLayoutBinding.root)

        setupOnlineStoreUserInterface()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        overridePendingTransition(R.anim.fade_in, 0)

    }

    override fun onBackPressed() {

        overridePendingTransition(R.anim.fade_in, 0)
        this@OnlineStore.finish()

    }

}