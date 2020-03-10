package com.andriiginting.animationsexample

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.Animation.REVERSE
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //rotation animations, call doRotation()
        //change color animations, call changeViewColor()
        //change color animations, call changeColorObjectAnimator()

        changeColorObjectAnimator()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //view animation
    private fun doRotation(repeatDurations: Long) {
        RotateAnimation(
            0f,
            360f,
            RELATIVE_TO_SELF, 0.5F,
            RELATIVE_TO_SELF, 0.5F
        ).apply {
            repeatCount = INFINITE
            repeatMode = REVERSE
            duration = repeatDurations
        }.let(::applyAnimations)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun changeViewColor() {
        ValueAnimator.ofArgb(Color.BLACK, Color.YELLOW, Color.GREEN)
            .apply {
                duration = 2000
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
                addUpdateListener { colors ->
                    tvTextAnimations.setTextColor(colors.animatedValue as Int)
                }
                start()
            }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun changeColorObjectAnimator(){
        ObjectAnimator.ofArgb(
            tvTextAnimations,
            "andriiginting",
            Color.BLACK, Color.YELLOW, Color.GREEN
        ).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun applyAnimations(animations: Animation) {
        tvTextAnimations.animation = animations
        tvTextAnimations.animate()
    }
}
