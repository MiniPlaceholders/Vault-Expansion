plugins {
    java
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.vaultExpansionPaper)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    shadowJar {
        archiveFileName.set("${rootProject.name}-${project.version}.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    build {
        dependsOn(shadowJar)
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
