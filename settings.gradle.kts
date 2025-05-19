enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Vault-Expansion"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

arrayOf("paper").forEach {
    include("vault-expansion-$it")

    project(":vault-expansion-$it").projectDir = file(it)
}

