package com.movtery.zalithlauncher.ui.minecraft

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun MinecraftText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    shadowColor: Color = Color(0xFF3F3F3F),
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            color = shadowColor,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            fontFamily = FontFamily.Monospace,
            textAlign = textAlign,
            modifier = Modifier.offset(x = 1.5.dp, y = 1.5.dp)
        )
        Text(
            text = text,
            color = color,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            fontFamily = FontFamily.Monospace,
            textAlign = textAlign
        )
    }
}

@Composable
fun MinecraftButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLuckyGold: Boolean = false,
    enabled: Boolean = true,
    height: Dp = 48.dp,
    content: (@Composable () -> Unit)? = null
) {
    var isPressed by remember { mutableStateOf(false) }

    val baseColor = if (!enabled) Color(0xFF555555) else if (isLuckyGold) Color(0xFFD4AF37) else Color(0xFF6B6B6B)
    val lightBorder = if (!enabled) Color(0xFF777777) else if (isLuckyGold) Color(0xFFFFE066) else Color(0xFF8E8E8E)
    val darkBorder = if (!enabled) Color(0xFF333333) else if (isLuckyGold) Color(0xFF8B6508) else Color(0xFF373737)
    val topBorder = if (isPressed) darkBorder else lightBorder
    val bottomBorder = if (isPressed) lightBorder else darkBorder

    Box(
        modifier = modifier
            .height(height)
            .pointerInput(enabled) {
                if (enabled) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                            onClick()
                        }
                    )
                }
            }
            .drawBehind {
                val borderWidth = 3.dp.toPx()
                val w = size.width
                val h = size.height

                drawRect(Color.Black, Offset.Zero, size)

                drawRect(
                    baseColor,
                    Offset(borderWidth, borderWidth),
                    Size(w - borderWidth * 2, h - borderWidth * 2)
                )

                drawLine(topBorder, Offset(borderWidth, borderWidth), Offset(w - borderWidth, borderWidth), strokeWidth = borderWidth)
                drawLine(topBorder, Offset(borderWidth, borderWidth), Offset(borderWidth, h - borderWidth), strokeWidth = borderWidth)

                drawLine(bottomBorder, Offset(borderWidth, h - borderWidth), Offset(w - borderWidth, h - borderWidth), strokeWidth = borderWidth)
                drawLine(bottomBorder, Offset(w - borderWidth, borderWidth), Offset(w - borderWidth, h - borderWidth), strokeWidth = borderWidth)
            }
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        val contentOffset = if (isPressed) IntOffset(0, 3) else IntOffset.Zero
        Box(modifier = Modifier.offset { contentOffset }) {
            if (content != null) {
                content()
            } else {
                MinecraftText(
                    text = text,
                    color = if (isLuckyGold) Color(0xFFFFFF99) else Color(0xFFE0E0E0),
                    shadowColor = if (isLuckyGold) Color(0xFF554400) else Color(0xFF2B2B2B),
                    fontSize = 15,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MinecraftSlotCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF1E1E1E),
    hasEnchantmentGlint: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glint")
    val glintOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "glint_anim"
    )

    Box(
        modifier = modifier
            .drawBehind {
                val bw = 3.dp.toPx()
                val w = size.width
                val h = size.height

                drawRect(Color(0xFF0F0F0F), Offset.Zero, size)

                drawLine(Color(0xFF0A0A0A), Offset(0f, 0f), Offset(w, 0f), strokeWidth = bw)
                drawLine(Color(0xFF0A0A0A), Offset(0f, 0f), Offset(0f, h), strokeWidth = bw)

                drawLine(Color(0xFF444444), Offset(0f, h), Offset(w, h), strokeWidth = bw)
                drawLine(Color(0xFF444444), Offset(w, 0f), Offset(w, h), strokeWidth = bw)

                drawRect(backgroundColor, Offset(bw, bw), Size(w - bw * 2, h - bw * 2))

                if (hasEnchantmentGlint) {
                    val brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x559933FF),
                            Color(0x88CC66FF),
                            Color(0x559933FF),
                            Color.Transparent
                        ),
                        start = Offset(w * glintOffset, 0f),
                        end = Offset(w * (glintOffset + 0.5f), h)
                    )
                    drawRect(brush, Offset(bw, bw), Size(w - bw * 2, h - bw * 2))
                }
            }
            .padding(8.dp),
        content = content
    )
}

@Composable
fun MinecraftTitleHeader(
    modifier: Modifier = Modifier,
    splashTexts: List<String> = listOf(
        "Now 100% Luckier!",
        "Lucky Blocks Everywhere!",
        "Craft Your Destiny!",
        "Golden Apples Included!",
        "Emerald Tier Activated!",
        "May The Luck Be With You!"
    )
) {
    val infiniteTransition = rememberInfiniteTransition(label = "splash")
    val splashScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.14f,
        animationSpec = infiniteRepeatable(
            animation = tween(450, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "splash_scale"
    )

    val randomSplash = remember {
        splashTexts[Random.nextInt(splashTexts.size)]
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Text(
                    text = "LUCKY LAUNCHER",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFF6B5300),
                    letterSpacing = 2.sp,
                    modifier = Modifier.offset(x = 2.dp, y = 3.dp)
                )
                Text(
                    text = "LUCKY LAUNCHER",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFFFFD700),
                    letterSpacing = 2.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 18.dp, y = 14.dp)
                .rotate(-20f)
                .scale(splashScale)
        ) {
            Text(
                text = randomSplash,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF3F3F00),
                modifier = Modifier.offset(x = 1.5.dp, y = 1.5.dp)
            )
            Text(
                text = randomSplash,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color(0xFFFFFF55)
            )
        }
    }
}

@Composable
fun MinecraftXpBar(
    progress: Float,
    level: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "$level",
                color = Color(0xFF003300),
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.offset(x = 1.dp, y = 1.dp)
            )
            Text(
                text = "$level",
                color = Color(0xFF80FF20),
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .drawBehind {
                    val w = size.width
                    val h = size.height

                    drawRect(Color.Black, Offset.Zero, size)

                    drawRect(Color(0xFF1E3A0C), Offset(1.5f, 1.5f), Size(w - 3f, h - 3f))

                    val fillWidth = ((w - 3f) * progress.coerceIn(0f, 1f))
                    if (fillWidth > 0f) {
                        drawRect(Color(0xFF80FF20), Offset(1.5f, 1.5f), Size(fillWidth, h - 3f))
                        drawRect(Color(0xFFBFFF60), Offset(1.5f, 1.5f), Size(fillWidth, 1.5f))
                    }
                }
        )
    }
}
