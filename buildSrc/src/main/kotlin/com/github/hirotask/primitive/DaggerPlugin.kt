package com.github.hirotask.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class DaggerPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
            }
            dependencies {
                add("implementation","com.google.dagger:dagger:${Versions.Dependency.daggerVersion}")
                add("annotationProcessor", "com.google.dagger:dagger-compiler:${Versions.Dependency.daggerVersion}")
                add("kapt", "com.google.dagger:dagger-compiler:${Versions.Dependency.daggerVersion}")
            }
            extensions.configure<KaptExtension> {
                generateStubs = true
            }
        }
    }
}