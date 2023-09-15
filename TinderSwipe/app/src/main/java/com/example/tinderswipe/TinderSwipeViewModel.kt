package com.example.tinderswipe

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TinderSwipeViewModel: ViewModel() {

    private val stream = MutableLiveData<TinderSwipeModel>()

    val modelStream: LiveData<TinderSwipeModel>
        get() = stream

    private val data = listOf(
        TinderSwipeCardModel("Salut", "Hej", Color.parseColor("#E91E63")),
        TinderSwipeCardModel("Comment Cave", "Hur är det", Color.parseColor("#2196F3")),
        TinderSwipeCardModel("Bien", "Bra", Color.parseColor("#F44336")),
        TinderSwipeCardModel("Et toi?", "Du då", Color.parseColor("#9E9E9E"))
    )
    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]
    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    init {
        updateStream()
    }

    fun swipe() {
        currentIndex += 1
        updateStream()
    }

    private fun updateStream() {
        stream.value = TinderSwipeModel(
            top = topCard,
            bottom = bottomCard
        )
    }

}