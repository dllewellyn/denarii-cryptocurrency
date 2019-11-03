import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    id("com.jfrog.bintray")
    `maven-publish`
}

val publicationName = "coinbase-api-coinbase-pro-core"
val g: String by project
val v: String by project

group = g
version = v

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":core", configuration = "jvmDefault"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.8.5")
    testImplementation("junit:junit:4.12")
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
}


publishing {
    publications {
        register(publicationName, org.gradle.api.publish.maven.MavenPublication::class) {
            artifactId = publicationName
            from(components["java"])
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
