/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 2:44 PM
 * Last modified 6/28/20 2:40 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.nio.charset.Charset
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

            if (networkCheckpoint.networkConnection()) {

                CoroutineScope(Dispatchers.IO).launch {

                    val link = URL("https://abanabsalan.com/wp-json/wp/v2/posts?page=4&per_page=1&orderby=date&order=asc")
                    val downloadedData = link.readText(Charset.defaultCharset())

                    val fullJsonData: JSONArray = JSONArray(downloadedData)

                    val postTitle = JSONObject(fullJsonData[0].toString()).getJSONObject("title").getString("rendered")
                    val postFeatureImageLink = JSONObject(fullJsonData[0].toString()).getString("jetpack_featured_media_url")

                    val rawPostContent = JSONObject(fullJsonData[0].toString()).getJSONObject("content").get("rendered").toString()

                    startActivity(Intent(applicationContext, PostView::class.java).apply {
                        putExtra("PostTitle", postTitle)
                        putExtra("PostFeatureImageLink", postFeatureImageLink)
                        putExtra("RawPostContent", rawPostContent)
                    }, ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

                }

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