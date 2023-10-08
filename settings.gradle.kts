rootProject.name = "algos"

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

include("common")
include("lucky-tickets_1")
include("algebraic-algorithms_2")
include("data-structures_3")
include("simple-sorts-6")

