package com.info.sovannarith.repository

import com.info.sovannarith.generated.CourseProto.GetFooRequest
import com.info.sovannarith.generated.CourseProto.GetFooResponse
import com.info.sovannarith.generated.FooServiceGrpcKt
import net.devh.boot.grpc.server.service.GrpcService

//@Service
//class FooService : FooServiceGrpc.FooServiceImplBase() {
//
//    override fun getFoo(request: GetFooRequest, responseObserver: StreamObserver<GetFooResponse>?) {
//        val course = GetFooResponse.newBuilder()
//            .setFooName("This is Fooooooo")
//            .setFooValue(999999)
//            .build()
//        responseObserver?.onNext(course)
//        responseObserver?.onCompleted()
//    }
//}

@GrpcService
class FooService : FooServiceGrpcKt.FooServiceCoroutineImplBase() {

    override suspend fun getFoo(request: GetFooRequest): GetFooResponse {
        return GetFooResponse.newBuilder()
            .setFooName("This is Fooooooo")
            .setFooValue(999999)
            .build()
    }
}