plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.5.21"
    id("kotlin-parcelize")
}

android {
    namespace = "com.pruebita.mydailyfisiapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.pruebita.mydailyfisiapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.dagger:hilt-android:2.44")

    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.google.android.gms:play-services-mlkit-face-detection:17.1.0")

    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //New dependencies
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    //Map dependencies
    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Camera
    implementation ("androidx.camera:camera-camera2:1.2.2")
    implementation ("androidx.camera:camera-core:1.2.2")
    implementation ("androidx.camera:camera-lifecycle:1.2.2")
    implementation ("androidx.camera:camera-view:1.2.2")
    implementation ("com.google.accompanist:accompanist-permissions:0.29.1-alpha")

    implementation ("androidx.compose.animation:animation-core:1.4.0-beta01")


    //Google dependencies
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation ("androidx.compose.foundation:foundation:1.0.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")


    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation ("commons-codec:commons-codec:1.15")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")


    implementation ("com.google.mlkit:face-detection:16.1.5")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")



}
kapt {
    correctErrorTypes = true
}
