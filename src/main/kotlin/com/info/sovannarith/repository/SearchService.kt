package com.info.sovannarith.repository

import com.info.sovannarith.generated.CourseProto
import com.info.sovannarith.generated.CourseProto.Course
import com.info.sovannarith.generated.SearchServiceGrpcKt
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.web.client.ResourceAccessException

@GrpcService
class SearchService(var courses: Map<Int, Course>): SearchServiceGrpcKt.SearchServiceCoroutineImplBase() {
    private fun getCourse(id: Int): Course {
        return courses[id] ?: throw ResourceAccessException("$id")
    }

    override suspend fun getCourse(request: CourseProto.GetCourseRequest): Course {
        return getCourse(request.id)
    }
}
