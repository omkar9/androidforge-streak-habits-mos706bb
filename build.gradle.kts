plugins {
    // Core plugins
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false // For pure Kotlin modules like 'domain'
    alias(libs.plugins.ksp) apply false // Kotlin Symbol Processing for Room and Hilt
    
    // Hilt plugin
    alias(libs.plugins.hilt.android) apply false
}

// Define common versions for dependencies using 'extra' properties
ext {
    set("androidGradlePlugin", "8.2.0")
    set("kotlin", "1.9.0")
    set("ksp", "1.9.0-1.0.13")
    set("hilt", "2.48")
    set("hiltCompiler", "1.1.0")
    set("room", "2.6.0")
    set("composeBom", "2023.08.00")
    set("composeCompiler", "1.5.1")
    set("activityCompose", "1.8.1")
    set("lifecycle", "2.6.2")
    set("navigationCompose", "2.7.5")
    set("workManager", "2.9.0")
    set("playServicesAds", "22.6.0")
    set("coreKtx", "1.12.0")
    set("materialIconsExtended", "1.5.4")
    set("junit", "4.13.2")
    set("androidxJunit", "1.1.5")
    set("espressoCore", "3.5.1")
    set("uiTooling", "1.5.4")
    set("uiTestManifest", "1.5.4")
    set("datastorePreferencesCore", "1.0.0")
}

// Define common repositories for all modules
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Define the modules included in the project
rootProject.name = "StreakHabits"
include(":app")
include(":core")
include(":data")
include(":domain")
include(":presentation")