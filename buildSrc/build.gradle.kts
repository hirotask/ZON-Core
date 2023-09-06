plugins {
    `kotlin-dsl`
}

group = "com.github.hirotask.buildlogic"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.0.0.202111291000-r")
}

gradlePlugin {
    plugins {
        register("daggerPlugin") {
            id = "build-logic.primitive.dagger"
            implementationClass = "com.github.hirotask.primitive.DaggerPlugin"
        }
    }
}