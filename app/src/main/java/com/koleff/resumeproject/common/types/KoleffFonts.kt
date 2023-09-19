package com.koleff.resumeproject.common.types

import android.graphics.Typeface
import com.koleff.resumeproject.KoleffApp

class KoleffFonts {

    companion object{
        val interBoldFont: Typeface = Typeface.createFromAsset(KoleffApp.getAppContext().assets, "font/inter_bold.ttf")
    }
}