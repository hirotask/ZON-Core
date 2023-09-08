package com.github.hirotask.buildlogic.primitive

import com.github.hirotask.buildlogic.libs
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
                add("implementation",libs.findLibrary("dagger").get())
                add("annotationProcessor", libs.findLibrary("dagger-compiler").get())
                add("kapt", libs.findLibrary("dagger-compiler").get())
            }
            extensions.configure<KaptExtension> {
                generateStubs = true
            }
        }
    }
}