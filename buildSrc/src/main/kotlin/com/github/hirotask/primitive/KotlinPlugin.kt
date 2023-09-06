package com.github.hirotask.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("org.jmailen.kotlinter")
            }

        }
    }
}