import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {

    namespace = "com.stoyanvuchev.kodaschool.recipeapp"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.stoyanvuchev.kodaschool.recipeapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

    }

    buildTypes {

        val remoteDataSourceApiBaseUrl: String = gradleLocalProperties(rootDir)
            .getProperty("REMOTE_DATA_SOURCE_API_BASE_URL")

        val remoteDataSourceApiKey: String = gradleLocalProperties(rootDir)
            .getProperty("REMOTE_DATA_SOURCE_API_KEY")

        val remoteDataSourceApiAppId: String = gradleLocalProperties(rootDir)
            .getProperty("REMOTE_DATA_SOURCE_API_APP_ID")

        debug {

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_BASE_URL",
                remoteDataSourceApiBaseUrl
            )

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_KEY",
                remoteDataSourceApiKey
            )

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_APP_ID",
                remoteDataSourceApiAppId
            )

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }

        release {

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_BASE_URL",
                remoteDataSourceApiBaseUrl
            )

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_KEY",
                remoteDataSourceApiKey
            )

            buildConfigField(
                "String",
                "REMOTE_DATA_SOURCE_API_APP_ID",
                remoteDataSourceApiAppId
            )

            isMinifyEnabled = true
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

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    // Core

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.google.android.material)
    implementation(libs.activity.compose)

    // Jetpack Compose

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Squircle Shape & Responsive Scaffold

    implementation(libs.stoyan.vuchev.squircle.shape)
    implementation(libs.stoyan.vuchev.responsive.scaffold)

    // Google Accompanist

    implementation(libs.google.accompanist.systemUiController)

    // Image loading

    implementation(libs.io.coil.compose)

    // Local Storage

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // DI

    implementation(libs.dagger.hilt.android)
    testImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    implementation(libs.androidx.hilt.navigation.compose)
    androidTestImplementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.dagger.hilt.compiler)
    kaptTest(libs.dagger.hilt.compiler)
    kaptAndroidTest(libs.dagger.hilt.compiler)

    // Serialization

    implementation(libs.google.gson)

    // Retrofit

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Testing

    testImplementation(libs.junit)
    testImplementation(libs.assertk)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.app.cash.turbine)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.app.cash.turbine)
    androidTestImplementation(libs.androidx.navigation.testing)

}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}