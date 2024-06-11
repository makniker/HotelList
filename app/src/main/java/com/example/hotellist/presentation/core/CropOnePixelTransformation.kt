package com.example.hotellist.presentation.core

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class CropOnePixelTransformation : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("CropOnePixelTransformation".toByteArray())
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val width = toTransform.width
        val height = toTransform.height

        if (width <= 2 || height <= 2) {
            return toTransform
        }

        return Bitmap.createBitmap(
            toTransform,
            1,
            1,
            width - 2,
            height - 2
        )
    }
}
