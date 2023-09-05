import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.lang.Closure
import kr.entree.spigradle.data.Load
import kr.entree.spigradle.kotlin.*

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.jmailen.kotlinter")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("kr.entree.spigradle") version "2.4.3"
}
val gitVersion: Closure<String> by extra

group = "com.github.hirotask.mc"
version = gitVersion()
base.archivesName.set("ZON-Kills")

val shadowApi: Configuration by configurations.creating
configurations["api"].extendsFrom(shadowApi)

val pluginVersion: String by project.ext

repositories {
    mavenCentral()
    protocolLib()
    jitpack()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    shadowApi(project(":core"))
    implementation(kotlin("stdlib-jdk8"))
    annotationProcessor("com.google.dagger:dagger-compiler:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    compileOnly(spigot(version = pluginVersion))
    api("com.github.sya-ri:EasySpigotAPI:2.4.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}

spigot {
    version = gitVersion()
    apiVersion = "1." + pluginVersion.split(".")[1]
    authors = listOf("hirotask")
    depends = listOf("kotlin-stdlib", "kotlin-reflect", "EasySpigotAPI")
    load = Load.STARTUP
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowApi)
    archiveClassifier.set("")
}

tasks.named("build") {
    dependsOn("shadowJar")
}

kapt {
    generateStubs = true
}