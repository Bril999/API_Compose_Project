
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "org.dc.brildmitry.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.dc.brildmitry.myapplication"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Для загрузки изображений
    implementation ("com.squareup.picasso:picasso:2.71828")

    // Для работы с Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.4.0") // Compose UI
    implementation ("androidx.compose.material3:material3:1.1.0") // Compose Material3
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.0") // Compose Preview
    implementation ("androidx.activity:activity-compose:1.7.0") // Для работы с Compose в Activity
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1") // Для работы с жизненным циклом
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1") // ViewModel Compose
    implementation ("io.coil-kt:coil-compose:2.0.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
}