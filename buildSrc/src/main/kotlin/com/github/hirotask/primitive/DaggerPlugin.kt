package com.github.hirotask.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DaggerPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
            }
            dependencies {
                add("implementation","com.google.dagger:dagger:${Versions.daggerVersion}")
                add("annotationProcessor", "com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
                add("kapt", "com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
            }
        }
    }
}