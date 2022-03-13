plugins {
    id("java")
    kotlin("jvm") 
    kotlin("plugin.serialization") 
    id("application")
    id("distribution")
}

val browserDist: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
	val ktorVersion = "1.6.7"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(project(":comu"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    browserDist(
        project(
            mapOf(
                "path" to ":client",
                "configuration" to "browserDist"
            )
        )
    )
}

application {
    mainClass.set("com.test.MainKt")
}

tasks.withType<Copy>().named("processResources") {
    from(browserDist)
}