package com.info.sovannarith

import com.info.sovannarith.generated.CourseProto
import com.info.sovannarith.generated.FooServiceGrpc
import com.info.sovannarith.generated.SearchServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ServiceClientApplication(
    @GrpcClient("grpc-server")
    private val fooServiceGrpc: FooServiceGrpc.FooServiceBlockingStub? = null,
    @GrpcClient("grpc-server")
    private val searchServiceGrpc: SearchServiceGrpc.SearchServiceBlockingStub? = null
): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        searchServiceGrpc?.getCourse(CourseProto.GetCourseRequest.newBuilder().setId(1).build())
        fooServiceGrpc?.getFoo(CourseProto.GetFooRequest.getDefaultInstance())
        println("Heeeeeeeelllllllllllllllllo")

    }
}

fun main(args: Array<String>) {
    runApplication<ServiceClientApplication>(*args)
}
