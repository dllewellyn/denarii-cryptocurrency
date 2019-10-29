import com.jfrog.bintray.gradle.BintrayExtension

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.2.0")
    }
}

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.jfrog.bintray")
    `maven-publish`
    id("com.squareup.sqldelight")
}

repositories {
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/ktor")
    mavenCentral()
    jcenter()
    google()
}

sqldelight {
    database("CryptoCurrencyDb") {
        packageName = "com.dllewellyn.coinbaseapi"
    }
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation("com.squareup.sqldelight:sqlite-driver:1.2.0")
            }
        }
    }
}


val publicationName = "datastore"
val g: String by project
val v: String by project

group = g
version = v

publishing {
    publications {
        register(publicationName, MavenPublication::class) {
            artifactId = publicationName
            artifact("$buildDir/libs/$publicationName-jvm-$v.jar")
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
        name = publicationName
        userOrg = "dllewellyn"
        vcsUrl = "https://github.com/dllewellyn/coinbaseAPI"
        setLicenses("Apache-2.0")
        with(version) {
            name = v
        }
    })
}
