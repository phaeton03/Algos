plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":data-structures_3"))
    implementation(project(":common"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
