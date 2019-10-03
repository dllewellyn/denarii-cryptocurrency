import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    id("com.jfrog.bintray")
    `maven-publish`
}

val publicationName = "coinbase-api-coroutines-adapter"
val g: String by project
val v: String by project

group = g
version = v

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":retrofit-adapter"))
    implementation(project(":core-coinbase-pro"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
    testImplementation("junit:junit:4.12")
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
        name = "coinbase-api-coroutines-adapter"
        userOrg = "dllewellyn"
        vcsUrl = "https://github.com/dllewellyn/coinbaseAPI"
        setLicenses("Apache-2.0")
        with(version) {
            name = v
        }
    })
}
