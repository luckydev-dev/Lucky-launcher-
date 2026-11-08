plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp.plugin) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    kotlin("plugin.serialization") version "2.2.10" apply false
}