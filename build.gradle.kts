import org.ajoberstar.grgit.Grgit

plugins {
    id("net.kyori.blossom") version "1.2.0" apply false
}

applyRootArtifactoryConfig()

if (!project.hasProperty("gitCommitHash")) {
    apply(plugin = "org.ajoberstar.grgit")
    ext["gitCommitHash"] = try {
        Grgit.open(mapOf("currentDir" to project.rootDir))?.head()?.abbreviatedId
    } catch (e: Exception) {
        logger.warn("Error getting commit hash", e)

        "no.git.id"
    }
}