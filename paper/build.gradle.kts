repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(libs.miniplaceholders)
    compileOnly(libs.vault) {
        isTransitive = false
    }
}

tasks {
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}
