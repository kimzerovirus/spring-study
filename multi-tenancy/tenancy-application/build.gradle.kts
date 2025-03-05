import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.flywaydb.flyway") version "8.5.13"
}

apply {
    plugin("org.flywaydb.flyway")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.postgresql:postgresql")
    implementation ("org.flywaydb:flyway-core")
}

tasks.withType<BootJar> {
    archiveFileName.set("tenancy-application.jar")
}

tasks.register<Zip>("zip") {
    dependsOn("bootJar")
}