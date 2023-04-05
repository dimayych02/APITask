plugins {
    id("java")
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
}

tasks.test {
    useTestNG()
}