import com.google.protobuf.gradle.*

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("com.google.protobuf") version "0.8.8"
    kotlin("jvm") version "1.3.70"
}

group = "app.ryss"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClassName = "app.ryss.service.LauncherKt"
}

dependencies {

    // Protobuf
    implementation("com.google.protobuf", "protobuf-java", "3.6.1")
    implementation("io.grpc", "grpc-stub", "1.15.1")
    implementation("io.grpc", "grpc-protobuf", "1.15.1")
    protobuf(files("src/main/proto"))

    // Logging
    implementation("org.slf4j", "slf4j-api", "2.0.0-alpha1")
    implementation("ch.qos.logback", "logback-classic", "1.3.0-alpha5")
    implementation("io.github.microutils", "kotlin-logging", "1.7.9")

    // Sentry
    implementation("io.sentry", "sentry", "1.7.30")
    implementation("io.sentry", "sentry-logback", "1.7.30")

    // Util
    implementation("io.github.cdimascio", "java-dotenv", "5.1.4")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Tests
    testImplementation("junit", "junit", "4.12")
}

sourceSets {
    create("proto") {
        proto {
            srcDir("src/main/proto")
        }
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    jar {
        archiveClassifier.value = "original"
    }

    shadowJar {
        archiveFileName.value = "kotlin-service.jar"
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.11.4"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.28.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

/**
 * Represents the mutable value of a [Property].
 */
var <T> Property<T>.value: T?
    get() = orNull
    set(value) = set(value)
