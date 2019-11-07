import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
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
            dependsOn(commonMain)
            dependencies {
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
        register(publicationName, org.gradle.api.publish.maven.MavenPublication::class) {
            artifactId = publicationName
           from(components["kotlin"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dllewellyn/denarii-cryptocurrency")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_KEY")
            }
        }
    }
}

fun findProperty(s: String) = project.findProperty(s) as String?