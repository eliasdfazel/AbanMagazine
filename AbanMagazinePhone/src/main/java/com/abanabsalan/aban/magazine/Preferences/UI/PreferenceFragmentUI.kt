/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 10:54 PM
 * Last modified 7/19/20 10:51 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Preferences.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abanabsalan.aban.magazine.databinding.PreferencesFragmentUiViewBinding

class PreferenceFragmentUI : Fragment() {

    lateinit var preferencesFragmentUiViewBinding: PreferencesFragmentUiViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(layoutInflater, viewGroup, savedInstanceState)
        preferencesFragmentUiViewBinding = PreferencesFragmentUiViewBinding.inflate(layoutInflater)

        return preferencesFragmentUiViewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}