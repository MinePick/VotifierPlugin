rootProject.name = "votifierplugin"

include("votifierplugin-api")
project(":votifierplugin-api").projectDir = file("api")


include("votifierplugin-common")
project(":votifierplugin-common").projectDir = file("common")

include("votifierplugin-bukkit")
project(":votifierplugin-bukkit").projectDir = file("bukkit")
include("votifierplugin-bungeecord")
project(":votifierplugin-bungeecord").projectDir = file("bungeecord")
include("votifierplugin-sponge")
project(":votifierplugin-sponge").projectDir = file("sponge")
include("votifierplugin-fabric")
project(":votifierplugin-fabric").projectDir = file("fabric")
include("votifierplugin-velocity")
project(":votifierplugin-velocity").projectDir = file("velocity")

include("votifierplugin-universal")
project(":votifierplugin-universal").projectDir = file("universal")