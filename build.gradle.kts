buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        google()
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
