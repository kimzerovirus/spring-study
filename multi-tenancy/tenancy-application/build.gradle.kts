import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<BootJar> {
    archiveFileName.set("tenancy-application.jar")
}

tasks.register<Zip>("zip") {
    dependsOn("bootJar")
}