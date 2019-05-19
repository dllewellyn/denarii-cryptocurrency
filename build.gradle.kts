import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    id("com.jfrog.bintray") version "1.8.3"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "2.0.2"
}

val publicationName = "CoinbaseAPI"
val g = "com.dllewellyn.coinbaseAPI"
val v ="1.1"
group = g
version = v

val ktor_version = "1.2.0-rc2"
var scarlet_version = "0.1.7"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.8")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.5.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:converter-gson:2.4.0")

    testImplementation("junit:junit:4.12")
}


val shadowJar: ShadowJar by tasks
shadowJar.apply {
    baseName = publicationName
}

publishing {
    publications {
        register(publicationName, MavenPublication::class) {
            artifactId = publicationName
            artifact(shadowJar)
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
        name = "coinbase-api-kt"
        userOrg = "dllewellyn"
        vcsUrl = "https://github.com/dllewellyn/coinbaseAPI"
        setLicenses("Apache-2.0")
        with (version) {
            name = v
        }
    })
}

tasks {
    withType(GradleBuild::class.java) {
        dependsOn(shadowJar)
    }

    withType<GenerateMavenPom> {
        destination = file("$buildDir/libs/${shadowJar.archiveName}.pom")
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}