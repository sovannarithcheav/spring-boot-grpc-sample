//package com.info.sovannarith.repository
//
//import com.info.sovannarith.generated.CourseProto.GetFooRequest
//import com.info.sovannarith.generated.CourseProto.GetFooResponse
//import com.info.sovannarith.generated.FooServiceGrpc
//import io.grpc.stub.StreamObserver
//import org.springframework.stereotype.Service
//
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