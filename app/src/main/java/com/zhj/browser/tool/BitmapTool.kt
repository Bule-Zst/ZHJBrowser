package com.zhj.browser.tool

import android.graphics.Bitmap
import com.zhj.browser.App
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


object BitmapTool {
    private val IN_PATH = "/icon/"

    /**
     * 随机生产文件名
     *
     * @return
     */
    private fun generateFileName(): String {
        return UUID.randomUUID().toString()
    }

    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    fun saveBitmap(mBitmap: Bitmap): String? {
        var savePath: String = App.instance.filesDir.absolutePath + IN_PATH
        var filePic: File
        try {
            filePic = File(savePath + generateFileName() + ".jpg")
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs()
                filePic.createNewFile()
            }
            val fos = FileOutputStream(filePic)
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            return null
        }

        return filePic.absolutePath
    }
}