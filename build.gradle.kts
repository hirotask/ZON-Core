import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.primitive.kotlin")
    id("org.jetbrains.dokka") version "1.9.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}




