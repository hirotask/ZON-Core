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
    api("org.mariadb.jdbc:mariadb-java-client:${Versions.Dependency.mariaDBClientVersion}")
    testImplementation(platform("org.junit:junit-bom:${Versions.Dependency.junitVersion}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:${Versions.Dependency.mockkVersion}")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.PASSED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}