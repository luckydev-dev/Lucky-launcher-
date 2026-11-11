package com.movtery.zalithlauncher.ui.minecraft

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Custom Non-Material Minecraft Theme Tokens for Lucky Launcher.
 */
data class MinecraftColors(
    val dirtBackground: Color = Color(0xFF2C1E14),
    val stoneBase: Color = Color(0xFF4A4A4A),
    val stoneHover: Color = Color(0xFF636363),
    val stonePressed: Color = Color(0xFF383838),
    val stoneHighlight: Color = Color(0xFF8B8B8B),
    val stoneShadow: Color = Color(0xFF1E1E1E),
    val slotBackground: Color = Color(0xFF222222),
    val slotHighlight: Color = Color(0xFF3A3A3A),
    val slotBorderDark: Color = Color(0xFF141414),
    val slotBorderLight: Color = Color(0xFF555555),
    val luckyGold: Color = Color(0xFFFFD700),
    val luckyGoldDark: Color = Color(0xFFB8860B),
    val luckyCloverGreen: Color = Color(0xFF2ECC71),
    val emerald: Color = Color(0xFF00FF7F),
    val diamondCyan: Color = Color(0xFF4DEEEA),
    val redstoneRed: Color = Color(0xFFFF3333),
    val obsidianDark: Color = Color(0xFF120E1E),
    val xpBarGreen: Color = Color(0xFF76DE19),
    val xpBarBackground: Color = Color(0xFF1A3305),
    val splashYellow: Color = Color(0xFFFFFF55),
    val textPrimary: Color = Color(0xFFFFFFFF),
    val textSecondary: Color = Color(0xFFC0C0C0),
    val textShadow: Color = Color(0xFF333333)
)

val LocalMinecraftColors = staticCompositionLocalOf { MinecraftColors() }

object MinecraftTheme {
    val colors: MinecraftColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMinecraftColors.current
}

@Composable
fun MinecraftThemeProvider(
    colors: MinecraftColors = MinecraftColors(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMinecraftColors provides colors,
        content = content
    )
}
