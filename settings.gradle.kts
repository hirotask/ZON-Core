pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ZON-Core"
include("core", "mc-1.19.2","infra")
includeBuild("build-logic")