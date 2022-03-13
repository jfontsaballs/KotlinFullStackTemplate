plugins {
    kotlin("js")
}

kotlin {
    js {
        useCommonJs()
        browser {
        }
        binaries.executable()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")	
    implementation(project(":comu"))

	val kotlinxHtmlVersion = "0.7.3"
	val kotlinWrappersSuffix = "pre.320-kotlin-1.6.10"
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.3-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-$kotlinWrappersSuffix")

    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
}

val browserDist by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(browserDist.name, tasks.named("browserDistribution").map { it.outputs.files.files.single() })
}