import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    id("com.jfrog.bintray")
    id("org.jetbrains.kotlin.plugin.serialization")
    `maven-publish`
}

val publicationName = "coinbase-api-coinbase-pro-noncore"
val g: String by project
val v: String by project

group = g
version = v

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    val ktorVersion = "1.2.3"
    val kotlinVersion = "1.3.50"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.9.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("io.ktor:ktor-client-logging-jvm:1.2.3")

    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-json-jvm:$ktorVersion")

    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation("com.google.code.gson:gson:2.8.5")
    testImplementation("junit:junit:4.12")
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
}


publishing {
    publications {
        register(publicationName, MavenPublication::class) {
            artifactId = publicationName
            from(components["java"])
        }
    }
}

fun findProperty(s: String) = project.findProperty(s) as String?


bintray {
    user = findProperty("bintrayUser")
    key = findProperty("bintrayApiKey")
    publish = true
    setPublications(publicationName)

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "coinbase-api-kt"
        name = "coinbase-api-coinbase-nonpro"
        userOrg = "dllewellyn"
        vcsUrl = "https://github.com/dllewellyn/coinbaseAPI"
        setLicenses("Apache-2.0")
        with(version) {
            name = v
        }
    })
}
