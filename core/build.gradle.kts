import groovy.lang.Closure
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("build-logic.primitive.kotlin")
    id("build-logic.primitive.dagger")
}

group = "com.github.hirotask.core"

repositories {
    mavenCentral()
}

dependencies {
    testApi(platform("org.junit:junit-bom:${libs.versions.junit.get()}"))
    testApi("org.junit.jupiter:junit-jupiter")
    testApi(libs.mockk)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.PASSED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}