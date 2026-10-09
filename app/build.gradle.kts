plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.livepicture.ar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.qabeman.frame2"
        minSdk = 24
        targetSdk = 35
        versionCode = 5
        versionName = "3.0"
        ndk {
            // پردازندهٔ همهٔ گوشی‌های امروزی؛ حجم APK را کم نگه می‌دارد
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    // کلید امضای ثابت: بدون آن هر بار ساخت در GitHub کلید تازه می‌سازد
    // و نسخهٔ جدید روی نسخهٔ قبلی نصب نمی‌شود («برنامه نصب نشد»)
    signingConfigs {
        create("qabeman") {
            storeFile = file("qabeman.keystore")
            storePassword = "qabeman123"
            keyAlias = "qabeman"
            keyPassword = "qabeman123"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("qabeman")
        }
        release {
            signingConfig = signingConfigs.getByName("qabeman")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // بدون ARCore: OpenCV (تشخیص و ردیابی عکس) + CameraX (دوربین)
    implementation("org.opencv:opencv:4.10.0")
    implementation("androidx.camera:camera-core:1.4.1")
    implementation("androidx.camera:camera-camera2:1.4.1")
    implementation("androidx.camera:camera-lifecycle:1.4.1")
    implementation("androidx.camera:camera-view:1.4.1")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("com.google.android.material:material:1.12.0")
}
