package com.info.sovannarith.controller

import com.info.sovannarith.generated.CourseProto
import com.info.sovannarith.repository.FooService
import io.grpc.stub.StreamObservers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FooController(
    private val fooService: FooService
) {

    @GetMapping("/foo")
    fun fooooo() {
        return fooService.getFoo(CourseProto.GetFooRequest.getDefaultInstance(), null)
    }

}