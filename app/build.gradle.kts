plugins {
    alias(libs.plugins.flickersearch.android.application)
    alias(libs.plugins.flickersearch.hilt)
    alias(libs.plugins.sgp.base)
    alias(libs.plugins.kotlin.kapt)
}

android {

    namespace = "ir.zinutech.android.flickrsearch"

    defaultConfig {
        applicationId = "ir.zinutech.android.flickrsearch"

        versionCode = 1
        versionName = "1.0"

        configure<BasePluginExtension> { archivesName.set("flickrSearch") }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "FLICKR_API_KEY", "\"5a2cc90782760b3a6b3eca570dfaf5c3\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(projects.domain)
    runtimeOnly(projects.data)
    implementation(projects.core)

    /*Kotlin*/
    implementation(libs.kotlin.coroutines)

    /*AndroidX*/
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.material)

    /*Misc*/
    implementation(libs.misc.timber)
    implementation(libs.viewbindingpropertydelegate)
    implementation(libs.coil.default)
}