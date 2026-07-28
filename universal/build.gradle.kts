import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
}

applyPlatformAndCoreConfiguration()
applyShadowConfiguration()

configurations {
    compileClasspath.get().extendsFrom(create("shadeOnly"))
}

dependencies {
    "implementation"(project(":votifierplugin-api"))
    "implementation"(project(":votifierplugin-common"))
    "implementation"(project(":votifierplugin-bukkit"))
    "implementation"(project(":votifierplugin-bungeecord"))
    "implementation"(project(":votifierplugin-sponge"))
    "implementation"(project(":votifierplugin-fabric"))
    "implementation"(project(":votifierplugin-velocity"))
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
        include(dependency(":votifierplugin-bukkit"))
        include(dependency(":votifierplugin-bungeecord"))
        include(dependency(":votifierplugin-sponge"))
        include(dependency(":votifierplugin-fabric"))
        include(dependency(":votifierplugin-velocity"))
    }

    exclude("GradleStart**")
    exclude(".cache")
    exclude("LICENSE*")
    exclude("META-INF/services/**")
    exclude("META-INF/maven/**")
    exclude("META-INF/versions/**")
    exclude("org/intellij/**")
    exclude("org/jetbrains/**")
    exclude("**/module-info.class")
}

tasks.named("assemble").configure {
    dependsOn("shadowJar")
}