/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/7/20 3:42 PM
 * Last modified 7/7/20 3:29 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.blurry.effect.view;

import android.content.Context;
import android.graphics.Bitmap;

interface BlurImpl {

	boolean prepare(Context context, Bitmap buffer, float radius);

	void release();

	void blur(Bitmap input, Bitmap output);

}
