plugins {
    kotlin("plugin.serialization") version "1.8.20"
}

dependencies {
    api(project(":common-core"))
    api(project(":common-json-core"))
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}