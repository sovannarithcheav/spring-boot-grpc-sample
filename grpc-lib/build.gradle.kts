import com.google.protobuf.gradle.*

val protobufVersion by extra("3.19.4")
val protobufPluginVersion by extra("0.8.18")
val grpcVersion by extra("1.44.1")
val grpcKotlinVersion by extra("1.2.1")
val jakartaApiVersion by extra("1.3.5")

plugins {
    id("com.google.protobuf")
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    api("io.grpc:grpc-netty-shaded:${grpcVersion}")
    api("io.grpc:grpc-protobuf:${grpcVersion}")
    api("io.grpc:grpc-stub:${grpcVersion}")
    api("io.grpc:protoc-gen-grpc-java:${grpcVersion}")
    api("io.grpc:grpc-kotlin-stub:${grpcKotlinVersion}")
    if (JavaVersion.current().isJava9Compatible) {
        implementation("jakarta.annotation:jakarta.annotation-api:${jakartaApiVersion}")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all-compatibility")
        jvmTarget = "11"
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
                "build/generated/source/proto/main/grpc", "build/generated/source/proto/main/java", "build/generated/source/proto/main/kotlin"
            )
        }
    }
}