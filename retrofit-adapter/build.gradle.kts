import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.jfrog.bintray")
    `maven-publish`
}

val publicationName = "coinbase-api-retrofit-adapter"
val g: String by project
val v: String by project

group = g
version = v


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
fun findProperty(s: String) = project.findProperty(s) as String?

publishing {
    publications {
        register(publicationName, MavenPublication::class) {
            artifactId = publicationName
            from(components["java"])
        }
    }
}

bintray {
    user = findProperty("bintrayUser")
    key = findProperty("bintrayApiKey")
    publish = true
    setPublications(publicationName)

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "coinbase-api-kt"
        name = "coinbase-api-retrofit-adapter"
        userOrg = "dllewellyn"
        vcsUrl = "https://github.com/dllewellyn/coinbaseAPI"
        setLicenses("Apache-2.0")
        with(version) {
            name = v
        }
    })
}
