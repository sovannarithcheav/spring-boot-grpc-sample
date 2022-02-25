package com.info.sovannarith

//import com.info.sovannarith.generated.CourseProto.Course
//import com.info.sovannarith.generated.CourseProto.Student
//import com.info.sovannarith.generated.CourseProto.Student.PhoneNumber
//import com.info.sovannarith.generated.CourseProto.Student.PhoneType
//import com.info.sovannarith.repository.FooService
//import com.info.sovannarith.repository.SearchService
//import io.grpc.Server
//import io.grpc.ServerBuilder
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter


@SpringBootApplication
class SpringBootGrpcApplication(
//    private val fooService: FooService
): ApplicationRunner {
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }

//
//    @Bean
//    fun createTestCourses(): SearchService{
//        val courses: MutableMap<Int, Course> = HashMap()
//        val course1 = Course.newBuilder()
//            .setId(1)
//            .setCourseName("REST with Spring")
//            .addAllStudent(createTestStudents())
//            .build()
//        val course2 = Course.newBuilder()
//            .setId(2)
//            .setCourseName("Learn Spring Security")
//            .addAllStudent(ArrayList())
//            .build()
//        courses[course1.id] = course1
//        courses[course2.id] = course2
//        return SearchService(courses)
//    }
//
//    private fun createTestStudents(): MutableIterable<Student> {
//        val phoneNumber = PhoneNumber.newBuilder().setNumber("+85595551155").setTypeValue(PhoneType.MOBILE.number).build()
//        return mutableListOf(
//            Student.getDefaultInstance(),
//            Student.newBuilder()
//                .setId(5)
//                .setEmail("student1@email.com")
//                .setFirstName("Student")
//                .setLastName("1")
//                .build()
//        )
//    }

    override fun run(args: ApplicationArguments?) {
//
//        // build gRPC server
//        val server: Server = ServerBuilder.forPort(80)
//            .addService(createTestCourses())
//            .addService(fooService)
//            .build()
//
//        // start
//        server.start()
//
//        // shutdown hook
//        Runtime.getRuntime().addShutdownHook(Thread {
//            println("gRPC server is shutting down!")
//            server.shutdown()
//        })
//
//        server.awaitTermination()
    }

}

fun main(args: Array<String>) {
    runApplication<SpringBootGrpcApplication>(*args)
}
