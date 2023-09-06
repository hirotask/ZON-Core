plugins {
    id("build-logic.primitive.kotlin")
    id("com.palantir.git-version") version "0.12.3" apply false
    id("org.jetbrains.dokka") version "1.8.20"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "com.palantir.git-version")
    apply(plugin = "org.jetbrains.dokka")
}

tasks.getByName<org.jetbrains.dokka.gradle.DokkaMultiModuleTask>("dokkaHtmlMultiModule") {
    outputDirectory.set(buildDir.resolve("dokkaHtmlMultiModuleOutput"))
}