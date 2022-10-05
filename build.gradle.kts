plugins {
    `maven-publish`
    java
}

val currentVersion = "1.0.4"

group = "com.github.knk190001.easyhook-java"
version = currentVersion

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
            version = currentVersion

            from(components["java"])
        }
    }
}

task("runJar", JavaExec::class){
    this.dependsOn.add(tasks.getByName("jar"))
    classpath = files("build/libs/EasyHookJava-$currentVersion.jar")
    classpath += sourceSets.main.get().runtimeClasspath
    mainClass.set("com.github.knk190001.easyhook_java.Main")
}