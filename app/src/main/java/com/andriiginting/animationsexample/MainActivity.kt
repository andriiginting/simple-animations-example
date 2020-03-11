package com.andriiginting.animationsexample

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.Animation.REVERSE
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
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

        compositionLottie()
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

    private fun playLottieAnimations(){
        ivOjolAnimations.apply {
            setAnimation("ojol_ijo.json")
            repeatCount = INFINITE
            playAnimation()
        }.resolveKeyPath(KeyPath("**")).forEach {
            Log.d("KeyPath-ojol","$it")
        }
    }

    private fun compositionLottie(){
        val composition = LottieCompositionFactory.fromAsset(this, "ojol_ijo.json")
        composition.addListener { result ->
            ivOjolAnimations.apply {
                setComposition(result)
                repeatCount = INFINITE
                addValueCallback(
                    KeyPath("**", "Fill 1"),
                    LottieProperty.COLOR, { Color.CYAN }
                )
                playAnimation()
            }
        }
    }

    private fun updateSingleProperty(){
        ivOjolAnimations.addValueCallback(
            KeyPath("White Solid 2"),
            LottieProperty.COLOR, { Color.CYAN }
        )
    }

    private fun updateWildCardProperty(){
        ivOjolAnimations.addValueCallback(
            KeyPath("*", "Shape Layer 1", "Shape 1", "Fill 1"),
            LottieProperty.COLOR, { Color.CYAN }
        )
    }

    private fun updateGlobstarProperty(){
        ivOjolAnimations.addValueCallback(
            KeyPath("**", "Fill 1"),
            LottieProperty.COLOR, { Color.CYAN }
        )
    }

    private fun applyAnimations(animations: Animation) {
        tvTextAnimations.animation = animations
        tvTextAnimations.animate()
    }
}
