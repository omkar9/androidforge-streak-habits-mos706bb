plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.androidforge.streakhappits"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.androidforge.streakhappits"
        minSdk = 24
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // AdMob App ID for release (replace with actual ID)
            buildConfigField("String", "ADMOB_APP_ID", "\"ca-app-pub-3940256099942544~3347511713\"") // Test ID
        }
        debug {
            isMinifyEnabled = false
            // AdMob App ID for debug (test ID)
            buildConfigField("String", "ADMOB_APP_ID", "\"ca-app-pub-3940256099942544~3347511713\"") // Test ID
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
        buildConfig = true // Enable BuildConfig for AdMob App ID
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["composeCompiler"] as String
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Access versions from rootProject.extra
    val coreKtx: String by rootProject.extra
    val lifecycle: String by rootProject.extra
    val activityCompose: String by rootProject.extra
    val composeBom: String by rootProject.extra
    val materialIconsExtended: String by rootProject.extra
    val navigationCompose: String by rootProject.extra
    val hilt: String by rootProject.extra
    val hiltCompiler: String by rootProject.extra
    val workManager: String by rootProject.extra
    val playServicesAds: String by rootProject.extra
    val room: String by rootProject.extra
    val junit: String by rootProject.extra
    val androidxJunit: String by rootProject.extra
    val espressoCore: String by rootProject.extra
    val datastorePreferencesCore: String by rootProject.extra

    // Core Android dependencies
    implementation("androidx.core:core-ktx:$coreKtx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")
    implementation("androidx.activity:activity-compose:$activityCompose")

    // Compose BOM (Bill of Materials)
    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended:$materialIconsExtended")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:$navigationCompose")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltCompiler")
    ksp("androidx.hilt:hilt-compiler:$hiltCompiler") // For Hilt ViewModel integration

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:$workManager")
    ksp("androidx.work:work-runtime-ktx-compiler:$workManager") // For WorkManager Hilt integration

    // AdMob
    implementation("com.google.android.gms:play-services-ads:$playServicesAds")

    // Room Database - only runtime dependencies here. Annotation processor in data module
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences-core:$datastorePreferencesCore")

    // Project modules
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    // Debugging and Testing
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("junit:junit:$junit")
    androidTestImplementation("androidx.test.ext:junit:$androidxJunit")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBom"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}

// KSP configuration for Hilt
hilt {
    enableAggregatingTask = true
}