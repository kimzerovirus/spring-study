tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val kotestVersion = "5.9.1"

dependencies {
    // kotest - junit platform 의존성을 가지고 있긴 하다.
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}
