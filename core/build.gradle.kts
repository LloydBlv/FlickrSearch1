plugins {
    alias(libs.plugins.flickersearch.jvm.library)
    alias(libs.plugins.ksp)
}


dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.dagger.runtime)
    ksp(libs.dagger.apt.compiler)
}