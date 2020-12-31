/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/31/20 10:37 AM
 * Last modified 12/31/20 10:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.databinding.PinchZoomLayoutBinding

class GesturePinchImageView : Fragment() {

    lateinit var pinchZoomLayoutBinding: PinchZoomLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pinchZoomLayoutBinding = PinchZoomLayoutBinding.inflate(layoutInflater)

        return pinchZoomLayoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extractedColor = arguments?.getInt("ExtractedColor")
        val imageByteArray = arguments?.getByteArray("ImageByteArray")

        imageByteArray?.let { byteArray ->

            pinchZoomLayoutBinding.closeImageView.backgroundTintList = extractedColor?.let {
                ColorStateList.valueOf(
                    it
                )
            }.let {
                ColorStateList.valueOf(
                    Color.WHITE
                )
            }

            pinchZoomLayoutBinding.gesturePinchImageView.setImageBitmap(BitmapFactory.decodeByteArray(byteArray,0, byteArray.size))

            pinchZoomLayoutBinding.closeImageView.setOnClickListener {

                try {

                    (requireActivity() as SinglePostView).postsViewUiBinding.postMenuButton.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))
                    (requireActivity() as SinglePostView).postsViewUiBinding.postMenuButton.visibility = View.VISIBLE

                    (requireActivity() as SinglePostView).postsViewUiBinding.postMenuIcon.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))
                    (requireActivity() as SinglePostView).postsViewUiBinding.postMenuIcon.visibility = View.VISIBLE

                    this@GesturePinchImageView.requireActivity().supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_out_right, 0)
                        .remove(this@GesturePinchImageView)
                        .commit()

                    (requireActivity() as SinglePostView).postsViewUiBinding.gesturePinchImageViewContainer.visibility = View.INVISIBLE

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()


    }

}