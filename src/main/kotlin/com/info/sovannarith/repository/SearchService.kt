//package com.info.sovannarith.repository
//
//import com.info.sovannarith.generated.CourseProto
//import com.info.sovannarith.generated.CourseProto.Course
//import com.info.sovannarith.generated.SearchServiceGrpc
//import io.grpc.stub.StreamObserver
//import net.devh.boot.grpc.server.service.GrpcService
//import org.springframework.web.client.ResourceAccessException
//
//@GrpcService
//class SearchService(var courses: Map<Int, Course>): SearchServiceGrpc.SearchServiceImplBase() {
//    private fun getCourse(id: Int): Course {
//        return courses[id] ?: throw ResourceAccessException("$id")
//    }
//
//
//    override fun getCourse(request: CourseProto.GetCourseRequest, responseObserver: StreamObserver<Course>) {
//        val course = getCourse(request.id)
//        responseObserver.onNext(course)
//        responseObserver.onCompleted()
//    }
//}