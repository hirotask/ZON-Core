import groovy.lang.Closure
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("build-logic.primitive.kotlin")
    id("build-logic.primitive.dagger")
}

group = "com.github.hirotask.infra"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    api("org.mariadb.jdbc:mariadb-java-client:2.4.4")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.PASSED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}