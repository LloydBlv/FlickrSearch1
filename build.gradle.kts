import com.autonomousapps.DependencyAnalysisExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask


plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.sgp.root)
    alias(libs.plugins.sgp.base)
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.doctor) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.cacheFixPlugin) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.retry) apply false
    alias(libs.plugins.sortDependencies) apply false
    alias(libs.plugins.dependencyAnalysis) apply false
    alias(libs.plugins.versions) apply false
    alias(libs.plugins.hilt) apply false
}
buildscript {
    repositories {
        gradlePluginPortal()
        google()
    }
}

allprojects {
    apply(plugin = "com.github.ben-manes.versions")
    fun isNonStable(version: String): Boolean {
        val stableKeyword =
            listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
    tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
        rejectVersionIf {
            isNonStable(candidate.version)
        }
        // optional parameters
        checkForGradleUpdate = true
        outputFormatter = "html"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

apply(plugin = libs.plugins.dependencyAnalysis.get().pluginId)
configure<DependencyAnalysisExtension> {
    structure {
        bundle("compose-ui") {
            primary("androidx.compose.ui:ui")
            includeGroup("androidx.compose.ui")
        }
    }
}
