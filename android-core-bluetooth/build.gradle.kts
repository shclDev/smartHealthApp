plugins{
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "jp.co.ohq.bluetooth.core.android"
    compileSdk = 34

    defaultConfig {
        minSdk = 27
        targetSdk = 34
        //versionCode = 1
        //versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    buildTypes {
        release {
            isMinifyEnabled =false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    /*
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0", {
        exclude group: "com.android.support", module: "support-annotations"
    })*/
    implementation("androidx.annotation:annotation:1.1.0")
    testImplementation("junit:junit:4.13.1")
    implementation(project(":utility"))
}
