import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.github.hirotask.buildlogic"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("org.jmailen.gradle:kotlinter-gradle:3.10.0")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.0.0.202111291000-r")
}

gradlePlugin {
    plugins {
        register("daggerPlugin") {
            id = "build-logic.primitive.dagger"
            implementationClass = "com.github.hirotask.primitive.DaggerPlugin"
        }
        register("kotlinPlugin") {
            id = "build-logic.primitive.kotlin"
            implementationClass = "com.github.hirotask.primitive.KotlinPlugin"
        }
    }
}