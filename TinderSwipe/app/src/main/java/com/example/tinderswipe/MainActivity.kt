package com.example.tinderswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.tinderswipe.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TinderSwipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel
            .modelStream
            .observe(this, Observer {
                bindCard(it)
                lifecycleScope.launch {
                    delay(3000)
                    binding.motionLayout.transitionToState(R.id.flip)
                }
            })

        binding.motionLayout.setTransitionListener(object : TransitionAdapter() {

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.flipCard -> {
                        //motionLayout.progress = 0f
                        //motionLayout.setTransition(R.id.rest, R.id.rest)
                    }
                    R.id.offScreenPass -> {
                        viewModel.swipe()
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.pass)
                    }
                    R.id.offScreenLike -> {
                        viewModel.swipe()
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                    }
                    R.id.cardBackOffScreenPass -> {
                        motionLayout.progress = 0f
                        viewModel.swipe()
                        motionLayout.setTransition(R.id.rest, R.id.backPass)
                    }
                    R.id.cardBackoffScreenLike -> {
                        motionLayout.progress = 0f
                        viewModel.swipe()
                        motionLayout.setTransition(R.id.rest, R.id.backLike)
                    }
                }
            }

        })

    }

    private fun bindCard(model: TinderSwipeModel) {
        binding.topCard.setCardBackgroundColor(model.top.backgroundColor)
        binding.topCardText.text = model.top.cardFront
        binding.topCardTextBack.text = model.top.cardBack
        binding.bottomCard.setCardBackgroundColor(model.bottom.backgroundColor)
        binding.bottomCardText.text = model.bottom.cardFront
    }
}