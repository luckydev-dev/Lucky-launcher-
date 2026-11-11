package com.movtery.zalithlauncher.ui.minecraft

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import kotlin.math.sin
import kotlin.random.Random

private data class LuckyParticle(
    val initialX: Float,
    val initialY: Float,
    val size: Float,
    val speed: Float,
    val color: Color,
    val swayPhase: Float
)

@Composable
fun MinecraftParticleCanvas(
    modifier: Modifier = Modifier,
    particleCount: Int = 36
) {
    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particle_motion"
    )

    val particles = remember {
        val colors = listOf(
            Color(0xFFFFD700), // Lucky Gold
            Color(0xFFFFE066), // Bright Gold
            Color(0xFF2ECC71), // Lucky Clover Green
            Color(0xFF55FFFF), // Diamond Cyan
            Color(0xFFFFFFFF)  // Star White
        )
        List(particleCount) {
            LuckyParticle(
                initialX = Random.nextFloat(),
                initialY = Random.nextFloat(),
                size = Random.nextInt(4, 9).toFloat(),
                speed = Random.nextFloat() * 0.7f + 0.3f,
                color = colors[Random.nextInt(colors.size)],
                swayPhase = Random.nextFloat() * 6.28f
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        particles.forEach { particle ->
            val yOffset = ((particle.initialY - progress * particle.speed) % 1f + 1f) % 1f
            val xSway = sin(progress * 6.28f + particle.swayPhase) * 16f
            val px = (particle.initialX * canvasWidth + xSway).coerceIn(0f, canvasWidth)
            val py = yOffset * canvasHeight

            val alpha = (sin(yOffset * Math.PI.toFloat())).coerceIn(0.2f, 0.95f)

            // Draw crisp pixel block particle
            drawRect(
                color = particle.color.copy(alpha = alpha),
                topLeft = Offset(px, py),
                size = Size(particle.size, particle.size)
            )

            // Pixel shadow
            drawRect(
                color = Color.Black.copy(alpha = alpha * 0.45f),
                topLeft = Offset(px + 1.5f, py + 1.5f),
                size = Size(particle.size, particle.size)
            )
        }
    }
}
