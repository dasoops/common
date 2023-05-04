plugins {
    `java-platform`
    `maven-publish`
}

rootProject.subprojects
    .filter { it.path != project.path }
    .forEach { project.evaluationDependsOn(it.path) }

dependencies {
    constraints {
        rootProject.subprojects
            .filter { it.path != project.path }
            .filter { it.extensions.findByName("publishing") != null }
            .forEach { subProject ->
                subProject.publishing.publications
                    .withType<MavenPublication>()
                    .forEach {
                        this@constraints.api("${it.groupId}:${it.artifactId}:${it.version}")
                    }
            }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.dasoops"
            version = this.version.toString()
            from(components["javaPlatform"])
        }
    }
}

//configurePublishing(
//    "mirai-bom",
//    addProjectComponents = false,
//)
//publishing.publications.getByName<MavenPublication>("maven") {
//    components.forEach {
//        println(it.name)
//    }
//    from(components["javaPlatform"])
//}