plugins {
    kotlin("multiplatform")
    `maven-publish`
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "com.dllewellyn.denarii"
val publicationName = "core"
val v: String by project
version = v

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    val serializationVersion = "0.13.0"

    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.ionspin.kotlin:bignum:0.1.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                api("com.ionspin.kotlin:bignum-jvm:0.1.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")


            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("io.mockk:mockk:1.9.3.kotlin12")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
                implementation("junit:junit:4.12")
            }
        }
    }
}

publishing {
    publications {
        register(publicationName, org.gradle.api.publish.maven.MavenPublication::class) {
            artifactId = publicationName
            artifact("$buildDir/libs/$publicationName-jvm-$v.jar")
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