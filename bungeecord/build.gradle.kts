import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
}

applyPlatformAndCoreConfiguration()
applyCommonArtifactoryConfig()
applyShadowConfiguration()

repositories {
    maven {
        name = "bungeecord"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/public/")
    }
}

configurations {
    compileClasspath.get().extendsFrom(create("shadeOnly"))
}

dependencies {
    "compileOnly"("net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT")
    "api"(project(":votifierplugin-api"))
    "api"(project(":votifierplugin-common"))
}


tasks.named<Copy>("processResources") {
    val internalVersion = project.ext["internalVersion"]
    inputs.property("internalVersion", internalVersion)
    filesMatching("bungee.yml") {
        expand("internalVersion" to internalVersion)
    }
}

tasks.named<Jar>("jar") {
    val projectVersion = project.version
    inputs.property("projectVersion", projectVersion)
    manifest {
        attributes("Implementation-Version" to projectVersion)
    }
}

tasks.named<ShadowJar>("shadowJar") {
    configurations = listOf(project.configurations["shadeOnly"], project.configurations["runtimeClasspath"])

    dependencies {
        include(dependency(":votifierplugin-api"))
        include(dependency(":votifierplugin-common"))
    }
}

tasks.named("assemble").configure {
    dependsOn("shadowJar")
}
