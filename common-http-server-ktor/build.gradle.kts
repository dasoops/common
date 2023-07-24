dependencies {
    api(project(":common-core"))
    api(project(":common-json-core"))

    //ktor
    val ktorVersion = "2.3.2"
    api("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
    api("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    api("io.ktor:ktor-server-core-jvm:$ktorVersion")
    api("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    api("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    api("io.ktor:ktor-server-netty-jvm:$ktorVersion")
}