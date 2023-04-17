import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("maven-publish")
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

allprojects {
    group = "com.dasoops"
    version = "4.0.1"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("maven-publish")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.dasoops"
                version = this@allprojects.version.toString()
                from(components["java"])
            }
        }
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17

