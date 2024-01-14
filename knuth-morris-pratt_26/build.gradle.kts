plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":data-structures_3"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
