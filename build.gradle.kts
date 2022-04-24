val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val spek_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.21"
}

group = "net.bundit"
version = "0.0.1"
application {
    mainClass.set("net.bundit.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    //testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    //testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Spek Framework
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

    // Kluent
    testImplementation("org.amshove.kluent:kluent:1.68")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}
