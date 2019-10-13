import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation(project(":core-coinbase-pro"))
    implementation(project(":core-coinbase-nonpro"))
    implementation(project(":datastore"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}