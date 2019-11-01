import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.Companion.attribute

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = "com.dllewellyn.denarii"
val publicationName = "core"
val v : String by project
version = v

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.ionspin.kotlin:bignum:0.1.1")
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
}