package me.brisson.newsfeed.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.newsfeed.R


val libreFranklin = FontFamily(
    Font(R.font.libre_franklin_thin, weight = FontWeight.Thin),
    Font(R.font.libre_franklin_light, weight = FontWeight.Light),
    Font(R.font.libre_franklin_regular),
    Font(R.font.libre_franklin_medium, weight = FontWeight.Medium),
    Font(R.font.libre_franklin_semibold, weight = FontWeight.SemiBold),
    Font(R.font.libre_franklin_bold, weight = FontWeight.Bold)
)

val merriWeather = FontFamily(
    Font(R.font.merriweather_light, weight = FontWeight.Light),
    Font(R.font.merriweather_regular),
    Font(R.font.merriweather_bold, weight = FontWeight.Bold),
    Font(R.font.merriweather_black, weight = FontWeight.Black)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = merriWeather,
        fontSize = 96.sp,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 60.sp,
        fontWeight = FontWeight.Light,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = merriWeather,
        fontSize = 48.sp,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic
    ),
    h4 = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = merriWeather,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = merriWeather,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = merriWeather,
        fontSize = 16.sp,
        letterSpacing = 0.44.sp
    ),
    body2 = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
    ),
    caption = TextStyle(
        fontFamily = merriWeather,
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic
    ),
    overline = TextStyle(
        fontFamily = libreFranklin,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp
    )
)

