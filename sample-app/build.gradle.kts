import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

version = "unspecified"

repositories {
    mavenCentral()
    jcenter()
}


dependencies {
    val kotlinVersion = "1.3.50"
    val ktorVersion = "1.2.3"

    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation(project(":datastore"))
    implementation(project(":core-coinbase-pro"))
    implementation(project(":core-coinbase-nonpro"))
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-json-jvm:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.9.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register<Jar>("uberJar") {
    manifest {
        attributes(
            "Main-Class" to "com.dllewellyn.coinbaseapi.SampleKt",
            "Implementation-Title" to "Gradle",
            "Implementation-Version" to archiveVersion
        )
    }
}