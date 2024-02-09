package com.example.mediation_app_ui

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Features(val title:String,
                    @DrawableRes val iconId:Int,
                    val lightColor:Color,
                    val mediumColor:Color,
                    val darkerColor: Color,)
