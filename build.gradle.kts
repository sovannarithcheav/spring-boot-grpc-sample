import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val protobufVersion: String by project
val protobufPluginVersion: String by project
val grpcVersion: String by project
val grpcKotlinVersion: String by project
val netDevhGrpcStarterVersion: String by project

plugins {
    id("org.springframework.boot").version("2.3.0.RELEASE")
    id("io.spring.dependency-management").version("1.0.9.RELEASE")
    id("com.google.protobuf").version("0.8.18")
    java
    idea
    kotlin("jvm").version("1.5.0")
    kotlin("plugin.spring").version("1.5.0")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("io.grpc:grpc-netty-shaded:${grpcVersion}")
//    runtimeOnly("io.grpc:grpc-netty:${grpcVersion}")
    implementation("io.netty:netty-tcnative:2.0.50.Final")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.grpc:protoc-gen-grpc-java:${grpcVersion}")
    implementation("io.grpc:grpc-kotlin-stub:${grpcKotlinVersion}")
    implementation("net.devh:grpc-server-spring-boot-starter:${netDevhGrpcStarterVersion}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all-compatibility")
        jvmTarget = JvmTarget.JVM_11.description
    }
    dependsOn("generateProto")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${grpcKotlinVersion}:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

sourceSets {
    main {
        java {
            srcDirs(
                "build/generated/source/proto/main/grpc",
                "build/generated/source/proto/main/kotlin"
            )
        }
    }
}