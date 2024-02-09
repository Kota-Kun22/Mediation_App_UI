package com.example.mediation_app_ui
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadFromTo(from: Offset,to:Offset)//passing the offset that we have marked in homeScreen
{
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x)/2,//making a smooth curve between these point
        abs(from.y + to.y)/2)
}

