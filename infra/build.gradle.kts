import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("build-logic.primitive.kotlin")
    id("build-logic.primitive.dagger")
}

group = "com.github.hirotask.infra"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    api("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:1.13.7")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.PASSED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}