/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/18/20 9:47 AM
 * Last modified 7/18/20 9:47 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WorldWideWebConfigurations.Network.Endpoints

//500 Request for Free
//https://newsapi.org/v2/everything?q=fashion&language=en&sortBy=popularity&apiKey=1be8edb780f84184a661780a44a36bb8

data class WorldWideWebEndpointsFactory(var privateKeyToNewsAPI: String)

class WorldWideWebEndpoints (private val worldWideWebEndpointsFactory: WorldWideWebEndpointsFactory) {

}