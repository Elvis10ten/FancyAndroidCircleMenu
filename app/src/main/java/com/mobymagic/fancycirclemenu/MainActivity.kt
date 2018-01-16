package com.mobymagic.fancycirclemenu

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import com.nineoldandroids.animation.Animator
import io.codetail.animation.arcanimator.ArcAnimator
import io.codetail.animation.arcanimator.Side
import kotlinx.android.synthetic.main.activity_main.*

const val ANIM_DURATION = 1200L

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            animateTopLeft()
        }, ANIM_DURATION)
    }

    private fun animateTopLeft() {
        val centerX = ViewUtils.getCenterX(top_left_placeholder)
        val centerY = ViewUtils.getCenterY(top_left_placeholder)

        showQuickPlayBtn(quick_play, centerX, centerY, Side.LEFT, OvershootInterpolator(),
                ANIM_DURATION, object: SimpleAnimatorListener() {

            override fun onAnimationEnd(animation: Animator) {
                animateTopRight()
            }

        })
    }

    private fun animateTopRight() {
        val centerX = ViewUtils.getCenterX(top_right_placeholder)
        val centerY = ViewUtils.getCenterY(top_right_placeholder)

        showQuickPlayBtn(invite_friend, centerX, centerY, Side.RIGHT, OvershootInterpolator(),
                ANIM_DURATION, object: SimpleAnimatorListener() {

            override fun onAnimationEnd(animation: Animator) {
                animateBottomLeft()
            }

        })
    }

    private fun animateBottomLeft() {
        val centerX = ViewUtils.getCenterX(bottom_left_placeholder)
        val centerY = ViewUtils.getCenterY(bottom_left_placeholder)

        showQuickPlayBtn(invitation_inbox, centerX, centerY, Side.LEFT, OvershootInterpolator(),
                ANIM_DURATION, object: SimpleAnimatorListener() {

            override fun onAnimationEnd(animation: Animator) {
                animateBottomRight()
            }

        })
    }

    private fun animateBottomRight() {
        val centerX = ViewUtils.getCenterX(bottom_right_placeholder)
        val centerY = ViewUtils.getCenterY(bottom_right_placeholder)

        showQuickPlayBtn(quit, centerX, centerY, Side.RIGHT, OvershootInterpolator(),
                ANIM_DURATION, object: SimpleAnimatorListener() {

            override fun onAnimationEnd(animation: Animator) {
                controller.setImageResource(R.drawable.ic_flash_circle_white_24dp)
                showController(controller, 2000, OvershootInterpolator())
            }

        })
    }

    private fun showQuickPlayBtn(view: View, centerX: Float, centerY: Float, side: Side,
                                 animInterpolator: Interpolator, animDuration: Long,
                                 animListener: SimpleAnimatorListener) {
        val anim = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f)
        val anim2 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f)

        val animSet = AnimatorSet()
        animSet.playTogether(anim, anim2)
        animSet.interpolator = animInterpolator
        animSet.duration = animDuration

        val arcAnimator = ArcAnimator.createArcAnimator(view, centerX, centerY, 90F, side)
                .setDuration(animDuration)
        arcAnimator.setInterpolator(animInterpolator)
        arcAnimator.addListener(animListener)

        view.visibility = View.VISIBLE
        arcAnimator.start()
        animSet.start()
    }

    private fun showController(view: View, animDuration: Long, animInterpolator: Interpolator) {
        val anim = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f)
        val anim2 = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f)
        val animSet = AnimatorSet()

        animSet.playTogether(anim, anim2)
        animSet.interpolator = animInterpolator
        animSet.duration = animDuration

        view.visibility = View.VISIBLE
        animSet.start()
    }

}
