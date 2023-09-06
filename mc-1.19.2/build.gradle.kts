import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.lang.Closure
import kr.entree.spigradle.data.Load
import kr.entree.spigradle.kotlin.*

plugins {
    kotlin("jvm")
    id("org.jmailen.kotlinter")
    id("build-logic.primitive.dagger")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("kr.entree.spigradle") version "2.4.3"
}
val gitVersion: Closure<String> by extra

group = "com.github.hirotask.mc"
version = gitVersion()
base.archivesName.set("ZON-Kills")

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

val pluginVersion: String by project.ext

repositories {
    mavenCentral()
    protocolLib()
    jitpack()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    shadowImplementation(project(":core"))
    shadowImplementation(project(":infra"))
    implementation(kotlin("stdlib-jdk8"))
    compileOnly(spigot(version = pluginVersion))
    shadowImplementation("com.github.sya-ri:EasySpigotAPI:2.4.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}

spigot {
    name = "ZON-Kills"
    version = gitVersion()
    apiVersion = "1." + pluginVersion.split(".")[1]
//    depends = listOf("EasySpigotAPI")
    authors = listOf("hirotask")
    excludeLibraries = listOf("com.github.hirotask.core:core:1.0.0", "com.github.sya-ri:EasySpigotAPI:2.4.0")
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