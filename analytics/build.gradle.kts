plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.analytics"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
val networkDependencies = mapOf(
    "retrofit" to "com.squareup.retrofit2:retrofit:${rootProject.ext.get("retrofitVersion")}",
    "retrofitGsonConverter" to "com.squareup.retrofit2:converter-gson:${rootProject.ext.get("retrofitVersion")}",
    "retrofitRxJavaAdapter" to "com.squareup.retrofit2:adapter-rxjava2:${rootProject.ext.get("retrofitVersion")}",
    "okHttp3" to "com.squareup.okhttp3:okhttp:${rootProject.ext.get("okHttpVersion")}",
    "okHttp3Logging" to "com.squareup.okhttp3:logging-interceptor:${rootProject.ext.get("okHttpVersion")}"
)

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.ext.get("hilt_version")}")
    testImplementation (libs.hilt.android.testing)
    kaptTest ("com.google.dagger:hilt-compiler:${rootProject.ext.get("hilt_version")}")


    implementation(libs.javax.inject)

    implementation(project(":network-data"))

}
apply(plugin = "dagger.hilt.android.plugin")