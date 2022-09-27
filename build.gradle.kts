plugins {
    java
    `maven-publish`
}

group = "com.github.knk190001.easyhook-java"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.jnr:jnr-ffi:2.2.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.github.knk190001.easyhook-java"
            artifactId = "easyhookjava"
            version = "1.0.2"

            from(components["java"])
        }
    }
}