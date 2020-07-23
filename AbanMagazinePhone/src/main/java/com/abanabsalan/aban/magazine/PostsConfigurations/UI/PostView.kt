/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/22/20 10:45 PM
 * Last modified 7/22/20 10:45 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.PostViewAdapter
import com.abanabsalan.aban.magazine.Preferences.PopupPreferencesController
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.Utils.UI.Theme.toggleLightDarkThemePostView
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import com.google.android.material.appbar.AppBarLayout
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureConstants
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureListenerInterface

class PostView : AppCompatActivity(), GestureListenerInterface {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val postsLiveData: PostsLiveData by lazy {
        ViewModelProvider(this@PostView).get(PostsLiveData::class.java)
    }

    lateinit var postsViewUiBinding: PostsViewUiBinding

    companion object {

        fun show(context: AppCompatActivity,
                 featuredImageSharedElement: AppCompatImageView,
                 postFeaturedImage: String, postTitle: String, postContent: String) {

            Intent(context, PostView::class.java).apply {
                putExtra(PostsDataParameters.PostParameters.PostFeaturedImage, postFeaturedImage)

                putExtra(PostsDataParameters.PostParameters.PostTitle, postTitle)
                putExtra(PostsDataParameters.PostParameters.PostContent, postContent)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this@apply,
                    ActivityOptions.makeSceneTransitionAnimation(context,
                        featuredImageSharedElement,
                        context.getString(R.string.featuredImageTransitionName))
                        .toBundle())
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        PopupPreferencesController(this@PostView, postsViewUiBinding.preferencePopupInclude)

        val featureImageLink = intent.getStringExtra(PostsDataParameters.PostParameters.PostFeaturedImage)
        val postTitle = intent.getStringExtra(PostsDataParameters.PostParameters.PostTitle)

        val rawPostContent = intent.getStringExtra(PostsDataParameters.PostParameters.PostContent)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        postsViewUiBinding.postRecyclerView.layoutManager = linearLayoutManager

        val postViewAdapter: PostViewAdapter = PostViewAdapter(this@PostView)

        setupUserInterface(postTitle, featureImageLink)

        postsLiveData.singleSinglePostLiveItemData.observe(this@PostView, Observer {

            if (it.isNotEmpty()) {

                postViewAdapter.singlePostItemsData.clear()

                postViewAdapter.singlePostItemsData.addAll(it)
                postsViewUiBinding.postRecyclerView.adapter = postViewAdapter

            }

        })

        /*Change Theme*/
        postsLiveData.toggleTheme.observe(this@PostView, Observer {

            var delayTheme: Long = 3333

            when(it) {
                ThemeType.ThemeLight -> {
                    delayTheme = 3000
                }
                ThemeType.ThemeDark -> {
                    delayTheme = 1133
                }
            }

            Handler().postDelayed({

                postViewAdapter.notifyItemRangeChanged(0, postViewAdapter.itemCount, null)


                toggleLightDarkThemePostView(this@PostView)

            }, delayTheme)

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

    override fun onBackPressed() {

        this@PostView.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }

    override fun onSwipeGesture(gestureConstants: GestureConstants, downMotionEvent: MotionEvent, moveMotionEvent: MotionEvent, initVelocityX: Float, initVelocityY: Float) {
        super.onSwipeGesture(gestureConstants, downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)


    }
}