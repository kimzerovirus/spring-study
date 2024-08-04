import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

allprojects {
    apply {
        plugin("java")
    }

    group = "me.kzv"
    version = "0.0.1-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":tenancy-manager") {
    dependencies {
        runtimeOnly("com.mysql:mysql-connector-j")
    }

    tasks.withType<BootJar> {
        archiveFileName.set("tenancy-manager.jar")
    }

    tasks.register<Zip>("zip") {
        dependsOn("bootJar")
    }
}

project(":tenancy-application") {
    dependencies {
        runtimeOnly("com.mysql:mysql-connector-j")
        runtimeOnly("org.postgresql:postgresql")
    }

    tasks.withType<BootJar> {
        archiveFileName.set("tenancy-application.jar")
    }

    tasks.register<Zip>("zip") {
        dependsOn("bootJar")
    }
}


