import groovy.lang.Closure

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("org.jmailen.kotlinter")
}

val gitVersion: Closure<String> by extra

group = "com.github.hirotask.core"
version = gitVersion()

repositories {
    mavenCentral()
}

dependencies {
    api("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    api("com.google.dagger:dagger:2.46.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
}
