import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    `java-library`
    id("org.spongepowered.gradle.plugin")
    id("net.kyori.blossom")
}

applyPlatformAndCoreConfiguration()
applyCommonArtifactoryConfig()
applyShadowConfiguration()

blossom {
    replaceToken("@version@", project.ext["internalVersion"])
}

sponge {
    apiVersion("7.2.0")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }

    license("GNU General Public License v3.0")

    plugin("votifierplugin") {
        displayName("VotifierPlugin")
        entrypoint("com.vexsoftware.votifier.sponge.VotifierPlugin")
        description("Safe, smart, and secure Votifier server plugin")
        links {
            source("https://github.com/MinePick/VotifierPlugin")
            issues("https://github.com/MinePick/VotifierPlugin")
        }
        contributor("MinePick.net") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
            version("7.2.0")
        }
    }
}

repositories {
    maven {
        name = "sponge"
        url = uri("https://repo.spongepowered.org/maven/")
    }
}

configurations {
    compileClasspath.get().extendsFrom(create("shadeOnly"))
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:7.2.0")

    "api"(project(":votifierplugin-api"))
    "api"(project(":votifierplugin-common"))
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

    exclude("GradleStart**")
    exclude(".cache")
    exclude("LICENSE*")
    exclude("META-INF/services/**")
    exclude("META-INF/maven/**")
    exclude("META-INF/versions/**")
    exclude("org/intellij/**")
    exclude("org/jetbrains/**")
    exclude("**/module-info.class")
    exclude("*.yml")
}

tasks.named("assemble").configure {
    dependsOn("shadowJar")
}
