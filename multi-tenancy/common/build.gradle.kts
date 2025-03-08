import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.getByName<Jar>("jar") { enabled = true }
tasks.getByName<BootJar>("bootJar") { enabled = false }