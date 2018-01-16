package com.mobymagic.fancycirclemenu

import android.view.View

object ViewUtils {

    fun getCenterX(view: View): Float {
        return view.x + (view.width / 2)
    }

    fun getCenterY(view: View): Float {
        return view.y + (view.height / 2)
    }

}
