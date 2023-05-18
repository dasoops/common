import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("maven-publish")
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20"
}

sourceSets {
    main {
        java.srcDirs.add(File("src/main/kotlin"))
    }
}

allprojects {
    group = "com.dasoops"
    version = "4.0.28"

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

    if (project.name != "common-bom") {
        apply {
            //排除common-bom
            plugin("maven-publish")
            plugin("io.spring.dependency-management")
            plugin("org.jetbrains.kotlin.jvm")
            if (project.name.contains("spring")){
                plugin("org.springframework.boot")
                plugin("org.jetbrains.kotlin.plugin.spring")
            }
        }
        val sourceJar = tasks.create("sourceJar", Jar::class) {
            from(sourceSets.main.get().allSource)
            archiveClassifier.set("sources")
        }
        publishing {
            publications {
                create<MavenPublication>("maven") {
                    groupId = "com.dasoops"
                    version = this@allprojects.version.toString()
                    from(components["kotlin"])
                    artifact(sourceJar)
                }
            }
        }
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17

