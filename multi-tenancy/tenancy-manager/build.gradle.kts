import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.withType<BootJar> {
    archiveFileName.set("tenancy-manager.jar")
}

tasks.register<Zip>("zip") {
    dependsOn("bootJar")
}