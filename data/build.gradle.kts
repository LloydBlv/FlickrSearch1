
plugins {
    alias(libs.plugins.flickersearch.android.library)
    alias(libs.plugins.flickersearch.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ir.zinutech.android.flickrsearch.data"
    compileSdk = 34

    defaultConfig { minSdk = 21 }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)


    /*Retrofit*/
    api(libs.retrofit.core)
    implementation(libs.retrofit.moshi)

    /*Moshi*/
    api(libs.moshi.core)
    implementation(libs.misc.moshiLazyAdapters)
    ksp(libs.moshi.kotlin.codegen)

    /*Chucker*/
    releaseImplementation(libs.chucker.noop)
    debugImplementation(libs.chucker.library)
}