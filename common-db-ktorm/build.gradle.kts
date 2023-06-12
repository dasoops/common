dependencies {
    api(project(":common-core"))
    api(project(":common-json-core"))
    val ktormVersion = "3.6.0"
    api("com.alibaba:druid:1.2.18")
    api("org.ktorm:ktorm-jackson:$ktormVersion")
    api("org.ktorm:ktorm-core:$ktormVersion")
}