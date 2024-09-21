// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.android.library) apply false
}*/
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    // todo use lib.version.toml
    extra["hilt_version"] = "2.52"
    extra["retrofitVersion"] = "2.11.0"
    extra["okHttpVersion"] = "4.9.3"



    dependencies {
        classpath(libs.kotlin.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins {
    id("com.android.application") version "8.6.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version  "2.52" apply false
    id("com.android.library") version "8.6.1" apply false
}