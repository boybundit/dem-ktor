val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val spek_version: String by project
val exposed_version: String by project
val h2_version: String by project

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
    // Server
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    // Database
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    // Client
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    // Test
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    // Spek Framework
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")
    // Kluent
    testImplementation("org.amshove.kluent:kluent:1.68")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}
