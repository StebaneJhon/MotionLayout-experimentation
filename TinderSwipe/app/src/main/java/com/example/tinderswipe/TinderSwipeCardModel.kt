package com.example.tinderswipe

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

@Parcelize
data class TinderSwipeCardModel(
    val cardFront: String,
    val cardBack: String,
    @ColorInt val backgroundColor: Int
): Parcelable