/*
*
*   MIT License
*
*   Copyright (c) 2023 Stoyan Vuchev
*
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*
*/

package com.stoyanvuchev.kodaschool.recipeapp.core.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

/** Utilities for working with a [Bitmap]. */
object BitmapUtils {

    /**
     * Returns a nullable [ByteArray] from an image url.
     * This operation is exhaustive and it's NOT recommended.
     * The only reason for using it is because the image urls from the API
     * are valid for a short period of time, and the cached urls will expire.
     * @param imageUrl The URL address of the image.
     **/
    fun getByteArrayFromImageUrl(imageUrl: String?): ByteArray? = try {

        // If [imageUrl] is not null, do the following, otherwise return null.
        imageUrl?.let { url ->

            // Open [URL] connection from the [url].
            val connection = (URL(url).openConnection() as HttpURLConnection)
                .apply {
                    this.doInput = true
                    this.connect()
                }

            // Decode a [Bitmap] and convert it into a [ByteArray].
            val byteArray = connection.inputStream.use { inputStream ->
                BitmapFactory.decodeStream(inputStream).toByteArray()
            }

            // Close the [URL] connection.
            connection.disconnect()

            // Return the [byteArray].
            byteArray

        }

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /** Returns a nullable [ByteArray] from a [Bitmap]. */
    fun Bitmap?.toByteArray(): ByteArray? = try {

        // If the [Bitmap] is not null, do the following, otherwise return null.
        this?.let { bmp ->

            // Open a [ByteArrayOutputStream] to decode the [Bitmap] and return a [ByteArray].
            ByteArrayOutputStream().use { byteArrayOutputStream ->
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                byteArrayOutputStream.toByteArray()
            }

        }

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


}