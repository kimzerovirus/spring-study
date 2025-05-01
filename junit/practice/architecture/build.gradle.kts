dependencies {
    // JUnit5에서 JUnit4 api를 호환하여 사용하기 위해 추가한다.
    implementation("org.junit.vintage:junit-vintage-engine") {
        exclude(group = "org.hamcrest", module = "hamcrest-core")
    }

    // JUnit 4 런타임
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}