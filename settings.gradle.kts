include(":core", ":app", ":domain", "data")
rootProject.name = "FlickrSearch"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


plugins {
    id("com.gradle.enterprise") version "3.13"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":platform")

inline fun configureIncludedBuild(key: String, body: (path: String) -> Unit) {
    System.getProperty("slack.include-build.$key")?.let(body)
}
configureIncludedBuild("sgp") { path ->
    includeBuild(path) {
        dependencySubstitution {
            substitute(module("com.slack.gradle:sgp")).using(project(":slack-plugin"))
            substitute(module("com.slack.gradle:sgp-agp-handler-api"))
                .using(project(":agp-handlers:agp-handler-api"))
            substitute(module("com.slack.gradle:sgp-agp-handler-82"))
                .using(project(":agp-handlers:agp-handler-82"))
            substitute(module("com.slack.gradle:sgp-agp-handler-83"))
                .using(project(":agp-handlers:agp-handler-83"))
        }
    }
}

// See comments on systemProp.slack.include-build.dagp property in gradle.properties
configureIncludedBuild("dagp") { path ->
    includeBuild(path) {
        dependencySubstitution {
            substitute(module("com.autonomousapps:dependency-analysis-gradle-plugin")).using(project(":"))
        }
    }
}
include(":platform")
