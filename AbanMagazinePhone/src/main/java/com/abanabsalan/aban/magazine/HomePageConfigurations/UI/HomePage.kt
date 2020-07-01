/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/30/20 4:21 PM
 * Last modified 6/30/20 4:06 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpointsFactory
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.PostsRetrieval
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import org.json.JSONArray
import javax.inject.Inject

class HomePage : AppCompatActivity() {

    private val networkCheckpoint: NetworkCheckpoint by lazy {
        NetworkCheckpoint(applicationContext)
    }

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var homePageViewBinding: HomePageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageViewBinding = HomePageViewBinding.inflate(layoutInflater)
        setContentView(homePageViewBinding.root)

        (application as AbanMagazinePhoneApplication)
            .dependencyGraph
            .subDependencyGraph()
            .create(this@HomePage, homePageViewBinding.root)
            .inject(this@HomePage)

        homePageViewBinding.root.post {

            val homePageLiveData: HomePageLiveData = ViewModelProvider(this@HomePage).get(HomePageLiveData::class.java)

            homePageLiveData.postsLiveItemDataSingle.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {



                }

            })

            if (networkCheckpoint.networkConnection()) {

                val postsRetrieval = PostsRetrieval(applicationContext)

                postsRetrieval.start(
                    PostsEndpointsFactory(
                        numberOfPageInPostsList = 1,
                        amountOfPostsToGet = 3,
                        sortByType = "date",
                        sortBy = "desc"
                    ),
                    object : JsonRequestResponseInterface {

                        override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {

                            homePageLiveData.prepareRawDataToRenderForPosts(rawDataJsonArray)

                        }

                        override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                            Log.d(this@HomePage.javaClass.simpleName, jsonError.toString())

                        }

                    })








            } else {




            }








        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        networkConnectionListener.unregisterDefaultNetworkCallback()

    }

    override fun onBackPressed() {

        startActivity(Intent(Intent.ACTION_MAIN).apply {
            this.addCategory(Intent.CATEGORY_HOME)
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }, ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

    }
}