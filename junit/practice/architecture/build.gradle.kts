plugins {
    id("jacoco")
}

apply{
    plugin("jacoco")
}

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
    finalizedBy(tasks.jacocoTestReport) // 테스트가 끝난 후 리포트를 생성하도록 설정
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}