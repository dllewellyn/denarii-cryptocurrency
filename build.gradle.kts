buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.2")
        classpath("com.squareup.sqldelight:gradle-plugin:1.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.3.50")
        classpath("com.google.cloud.tools:appengine-gradle-plugin:2.0.0-rc4")
    }

}

plugins {
    kotlin("jvm") version "1.3.50"
    id("com.jfrog.bintray") version "1.8.3"
    `maven-publish`

}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    google()
}
