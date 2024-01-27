plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Plugin KSP para el módulo. La versión ya está indica en el Gradle a nivel de proyecto
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.mpd.pmdm.dicerollerconstraintlayout"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mpd.pmdm.dicerollerconstraintlayout"
        minSdk = 21
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    val activityVersion = "1.8.2"
    val fragmentVersion = "1.6.2"
    val roomVersion = "2.6.1"
    // Para instanciar ViewModels en clases Activity
    implementation("androidx.activity:activity-ktx:$activityVersion")
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    //Dependencias para Room
    implementation("androidx.room:room-runtime:$roomVersion")

    // To use Kotlin Symbol Processing (KSP).
    //Esta línea se debe incluir después de haber incluido el plugin ksp en la parte superior de este arcghivo y haber sincronizado
    ksp("androidx.room:room-compiler:$roomVersion")
    //Usaremos coroutines con Room
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}