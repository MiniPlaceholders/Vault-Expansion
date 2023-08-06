plugins {
    java
}

repositories {
    maven("https://jitpack.io") {
        mavenContent {
            includeGroup("com.github.MilkBowl")
        }
    }
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(libs.miniplaceholders)
    compileOnly(libs.vault) {
        isTransitive = false
    }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
