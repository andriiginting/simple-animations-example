package com.andriiginting.animationsexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.Animation.REVERSE
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //rotation animations, call doRotation()

        doRotation(2000)
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

    private fun applyAnimations(animations: Animation) {
        tvTextAnimations.animation = animations
        tvTextAnimations.animate()
    }
}
