plugins {
    id("custom.kotlin")
    id("custom.detekt")
    id("custom.dependency.versions")
    id("custom.jacoco")
}

dependencies {
    testImplementation(libs.junit4)
    testRuntimeOnly(libs.kotlin.reflect)
    testImplementation(libs.strikt)
}
