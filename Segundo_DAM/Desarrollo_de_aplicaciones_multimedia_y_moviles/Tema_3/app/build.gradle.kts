import org.gradle.api.tasks.wrapper.Wrapper

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.basesdedatos"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.basesdedatos"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// Registrar una tarea `wrapper` en el proyecto de módulo para que `gradle :app:wrapper` funcione.
// Esto genera los archivos del wrapper en el directorio del módulo si se ejecuta.
// Ajusta `gradleVersion` si necesitas otra versión.
tasks.register<Wrapper>("wrapper") {
    gradleVersion = "9.1.0"
    distributionType = Wrapper.DistributionType.ALL
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}