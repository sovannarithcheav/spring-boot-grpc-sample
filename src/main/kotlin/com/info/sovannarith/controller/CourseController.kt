package com.info.sovannarith.controller

import com.info.sovannarith.generated.CourseProto.Course
import org.springframework.web.bind.annotation.RestController
import com.info.sovannarith.repository.SearchService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
class CourseController(
    private val courseRepo: SearchService
) {
    @RequestMapping("/courses")
    fun courses(): List<Course> {
        return courseRepo.getCourses()
    }

    @RequestMapping("/courses/{id}")
    fun getCourse(@PathVariable id: Int): Course {
        return courseRepo.getCourse(id)
    }
}