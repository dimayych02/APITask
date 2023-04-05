plugins {
    id("java")
    id ("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation ("org.testng:testng:7.3.0")
    testImplementation ("io.rest-assured:rest-assured:5.3.0")
    implementation ("io.qameta.allure:allure-testng:2.20.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation ("com.google.code.gson:gson:2.8.9")
}

tasks.test {
    useTestNG()
}