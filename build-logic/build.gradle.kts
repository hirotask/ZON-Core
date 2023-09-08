plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle)
    testImplementation(platform("org.junit:junit-bom:${libs.versions.junit.get()}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.mockk)
}

gradlePlugin {
    plugins {
        register("daggerPlugin") {
            id = "build-logic.primitive.dagger"
            implementationClass = "com.github.hirotask.buildlogic.primitive.DaggerPlugin"
        }
        register("kotlinPlugin") {
            id = "build-logic.primitive.kotlin"
            implementationClass = "com.github.hirotask.buildlogic.primitive.KotlinPlugin"
        }
    }
}