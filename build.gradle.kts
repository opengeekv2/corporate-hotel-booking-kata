import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "us.plp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val cucumberRuntime by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.junit.platform:junit-platform-engine:1.8.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.cucumber:cucumber-java:7.3.3")
    testImplementation("io.cucumber:cucumber-junit:7.3.3")
    testImplementation("org.junit.platform:junit-platform-suite:1.8.2")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.8.2")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.3.3")
    testImplementation("io.cucumber:cucumber-spring:7.3.3")
    testImplementation("io.mockk:mockk:1.12.4")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

task("cucumber") {
    dependsOn(tasks.named("assemble"), tasks.named("testClasses"))
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            args = listOf("--plugin", "pretty", "--glue", "gradle.cucumber", "src/test/resources")
        }
    }
}