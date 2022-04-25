/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

import android.content.Context
import java.io.File
import java.nio.charset.Charset

class FileIO (private val context: Context) {


    fun readFileLinesAsArray(fileName: String) : Array<String>? {
        val file: File? = context.getFileStreamPath(fileName)

        return if (file != null) {
            if (file.exists() && file.isFile) {
                file.readLines(Charset.defaultCharset()).toTypedArray()
            } else {
                null
            }
        } else {
            null
        }
    }

    fun readFileLinesAsList(fileName: String) : ArrayList<String>? {
        val file: File? = context.getFileStreamPath(fileName)

        return (if (file != null) {
            if (file.exists() && file.isFile) {
                file.readLines(Charset.defaultCharset())
            } else {
                null
            }
        } else {
            null
        }) as ArrayList<String>?
    }

    fun fileLinesCounter(fileName: String) : Int {
        val file: File? = context.getFileStreamPath(fileName)

        return if (file != null) {
            if (file.exists() && file.isFile) {
                file.readLines(Charset.defaultCharset()).size
            } else {
                0
            }
        } else {
            0
        }
    }

    fun readFile(fileName: String) : String? {
        val file: File? = context.getFileStreamPath(fileName)

        return if (file != null) {
            if (file.exists() && file.isFile) {
                file.readText(Charset.defaultCharset())
            } else {
                null
            }
        } else {
            null
        }
    }

    fun saveFile(fileName: String, content: String) {
        try {

            val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(content.toByteArray())

            fileOutputStream.flush()
            fileOutputStream.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}