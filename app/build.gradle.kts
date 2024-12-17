plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        create("quang_config") {
            storeFile = file("C:\\Users\\PC\\test_keystore.jks")
            storePassword = "123456"
            keyAlias = "quang"
            keyPassword = "123456"
        }
    }
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
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
            signingConfig = signingConfigs.getByName("quang_config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    //push ntfc
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}