plugins {
    kotlin("jvm") version "1.7.10" apply false
    kotlin("kapt") version "1.8.20" apply false
    id("org.jmailen.kotlinter") version "3.8.0" apply false
    id("com.palantir.git-version") version "0.12.3" apply false
    id("org.jetbrains.dokka") version "1.8.20" apply true
}

subprojects {
    apply(plugin = "com.palantir.git-version")
}

tasks.getByName<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtml") {
    outputDirectory.set(buildDir.resolve("dokkaHtmlOutput"))
}