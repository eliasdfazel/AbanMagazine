/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/9/20 5:56 AM
 * Last modified 8/9/20 5:44 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.Endpoints

class StoryHighlightsEndpoint {

    /**
     * Add All Your Instagram Story Highlights In A Wordpress Page Inside <p><a>...</a></p>
     * Then Replace This Link.
     **/
    val getInstagramStoryHighlightsEndpoint = "https://abanabsalan.com/wp-json/wp/v2/pages/2712"

    /**
     *  Append Id Of Instagram Highlights & Change AbanAbsalan Domain Name To Your Domain.
     **/
    companion object {
        const val InstagramStoryHighlightsCoverImageBaseLink: String = "https://bit.ly/AbanAbsalanInstagramStoryHighlights"
    }

}