/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/3/20 9:31 AM
 * Last modified 7/3/20 9:08 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.PostViewAdapter
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import com.google.android.material.appbar.AppBarLayout
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureConstants
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureListenerInterface

class PostView : AppCompatActivity(), GestureListenerInterface {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    lateinit var postsViewUiBinding: PostsViewUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        val featureImageLink = intent.getStringExtra("PostFeatureImageLink")
        val postTitle = intent.getStringExtra("PostTitle")

        val rawPostContent = intent.getStringExtra("RawPostContent")

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        postsViewUiBinding.postRecyclerView.layoutManager = linearLayoutManager

        val postViewAdapter: PostViewAdapter = PostViewAdapter(this@PostView)

        setupUserInterface(postTitle, featureImageLink)

        val postsLiveData = ViewModelProvider(this@PostView).get(PostsLiveData::class.java)

        postsLiveData.singleSinglePostLiveItemData.observe(this@PostView, Observer {

            if (it.isNotEmpty()) {

                postViewAdapter.singlePostItemsData.clear()

                postViewAdapter.singlePostItemsData.addAll(it)
                postsViewUiBinding.postRecyclerView.adapter = postViewAdapter

            }

        })

        postsLiveData.prepareRawDataToRenderForSinglePost(rawPostContent)

    }

    override fun onResume() {
        super.onResume()

        postsViewUiBinding.postTopBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

        })
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSwipeGesture(gestureConstants: GestureConstants, downMotionEvent: MotionEvent, moveMotionEvent: MotionEvent, initVelocityX: Float, initVelocityY: Float) {
        super.onSwipeGesture(gestureConstants, downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)


    }
}