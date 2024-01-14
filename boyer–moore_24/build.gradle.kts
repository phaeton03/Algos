plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":data-structures_3"))
    implementation(project(":minimal_skeleton_20"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
