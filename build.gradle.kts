import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.lang.Closure
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.7.10"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.ben-manes.versions") version "0.41.0"
    id("com.palantir.git-version") version "0.12.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jmailen.kotlinter") version "3.8.0"
    kotlin("kapt") version "1.8.22"
    id("org.jetbrains.dokka") version "1.8.20"
}

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

val gitVersion: Closure<String> by extra

val pluginVersion: String by project.ext
val kotlinVersion: String by project.ext

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-wasm:$kotlinVersion")
    compileOnly("org.spigotmc:spigot-api:$pluginVersion-R0.1-SNAPSHOT")
    api("com.github.sya-ri:EasySpigotAPI:2.4.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
    shadowImplementation("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    shadowImplementation("com.google.dagger:dagger:2.46.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
}

configure<BukkitPluginDescription> {
    main = "com.github.hirotask.mc.Main"
    version = gitVersion()
    apiVersion = "1." + pluginVersion.split(".")[1]
    author = "hirotask"
    depend = listOf("kotlin-stdlib", "kotlin-reflect", "EasySpigotAPI")
}

tasks.getByName<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtml") {
    outputDirectory.set(buildDir.resolve("dokkaHtmlOutput"))
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation)
    archiveClassifier.set("")
}

tasks.named("build") {
    dependsOn("shadowJar")
}

kapt {
    generateStubs = true
}

task<SetupTask>("setup")
