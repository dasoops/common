dependencies {
    api(project(":common-core-spring"))
    val ktormVersion = "3.6.0"
    api("org.ktorm:ktorm-jackson:$ktormVersion")
    api("org.ktorm:ktorm-core:$ktormVersion")
}