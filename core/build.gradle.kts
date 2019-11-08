import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    `maven-publish`
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.native.cocoapods")
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
    val bigNumVersion : String by project

//    iosX64("ios")
    iosArm64("ios64Arm")
    iosArm32("ios32Arm")
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.ionspin.kotlin:bignum:$bigNumVersion")
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
                api("com.ionspin.kotlin:bignum-jvm:$bigNumVersion")
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

//        val iosMain by getting {
//            dependencies {
//                implementation("com.ionspin.kotlin:bignum-iosx64:0.1.0")
//            }
//        }

        val ios64ArmMain by getting {
            dependencies {
                implementation("com.ionspin.kotlin:bignum-ios64arm:$bigNumVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }

        val ios32ArmMain by getting {
            dependencies {
                implementation("com.ionspin.kotlin:bignum-ios32arm:$bigNumVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }

    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
    }
}

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