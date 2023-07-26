@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    // Include 'plugins build' to define convention plugins.
    includeBuild("build-logic")
}

dependencyResolutionManagement{
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.gradle.enterprise") version "3.12.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.5"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}


gitHooks {
    commitMsg { conventionalCommits() }
    createHooks()
}

rootProject.name = "compose-experiments"

include(
    "app",
)
