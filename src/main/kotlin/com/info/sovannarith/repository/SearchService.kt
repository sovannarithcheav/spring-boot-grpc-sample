package com.info.sovannarith.repository

import com.info.sovannarith.generated.CourseProto
import com.info.sovannarith.generated.CourseProto.Course
import com.info.sovannarith.generated.SearchServiceGrpc
import io.grpc.stub.StreamObserver
import org.springframework.web.client.ResourceAccessException

class SearchService(var courses: Map<Int, Course>): SearchServiceGrpc.SearchServiceImplBase() {
    fun getCourse(id: Int): Course {
        return courses[id] ?: throw ResourceAccessException("$id")
    }

    fun getCourses() = courses.map { it.value }

    override fun getCourse(request: CourseProto.GetCourseRequest, responseObserver: StreamObserver<Course>) {
        val course = getCourse(request.id)
        responseObserver.onNext(course)
        responseObserver.onCompleted()
    }
}