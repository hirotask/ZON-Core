import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.lang.Closure
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.jmailen.kotlinter")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}
val gitVersion: Closure<String> by extra

group = "com.github.hirotask.mc"
version = gitVersion()

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

val pluginVersion: String by project.ext

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    shadowImplementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-wasm:1.7.10")
    compileOnly("org.spigotmc:spigot-api:$pluginVersion-R0.1-SNAPSHOT")
    api("com.github.sya-ri:EasySpigotAPI:2.4.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}

configure<BukkitPluginDescription> {
    main = "com.github.hirotask.mc.Main"
    version = gitVersion()
    apiVersion = "1." + pluginVersion.split(".")[1]
    author = "hirotask"
    depend = listOf("kotlin-stdlib", "kotlin-reflect", "EasySpigotAPI")
}


tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation)
    archiveClassifier.set("")
}

tasks.named("build") {
    dependsOn("shadowJar")
}

tasks.test {
    useJUnitPlatform()
}