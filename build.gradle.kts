// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("activityVersion", "1.4.0")
        set("appCompatVersion", "1.4.0")
        set("constraintLayoutVersion", "2.1.2")
        set("coreTestingVersion", "2.1.0")
        set("coroutines", "1.5.2")
        set("lifecycleVersion", "2.4.0")
        set("materialVersion", "1.4.0")
        set("roomVersion", "2.3.0")
        // testing
        set("junitVersion", "4.13.2")
        set("espressoVersion", "3.4.0")
        set("androidxJunitVersion", "1.1.3")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
}

// The extra line that was here should be gone.
