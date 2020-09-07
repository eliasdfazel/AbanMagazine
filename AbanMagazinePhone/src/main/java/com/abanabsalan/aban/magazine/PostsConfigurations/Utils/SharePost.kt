/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/7/20 4:38 AM
 * Last modified 9/7/20 4:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.Utils.BlogContent.LanguageUtils

/**
 * Use \n To Start New Line
 **/
class SharePost (private val appCompatActivity: AppCompatActivity) {

    object Compaigns {
        const val Hashtags = "#AbanAbsalan" + "\n" + "#آبان_آبسالان"
        const val SocialMention = "@AbanAbsalan"
    }

    /**
     * Use \n To Start New Line
     **/
    fun invoke(sharePostTitle: String, sharePostExcerpt: String, sharePostLink: String) {

        val shareText: String = "${sharePostTitle}" +
                "\n" + "\n" +
                "${sharePostExcerpt}" +
                "\n" + "\n" +
                SharePost.Compaigns.Hashtags +
                "\n" +
                "${createSocialMediaHashTagsCompaign(sharePostTitle)}" +
                "\n" +
                SharePost.Compaigns.SocialMention +
                "\n" + "\n" + "\n" +
                "${sharePostLink}"

        val shareIntent: Intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        appCompatActivity.startActivity(shareIntent)

    }

    private fun createSocialMediaHashTagsCompaign(postTitle: String) : String {

        val language = LanguageUtils()

        val allWords = postTitle.split(" ")

        val hashtagsText: StringBuilder = StringBuilder()

        allWords.forEach {

            if (language.checkRtl(it)) {

                hashtagsText.append("#${it} ")

            } else {

                hashtagsText.append(" ${it}#")

            }
        }

        return hashtagsText.toString()
    }

}