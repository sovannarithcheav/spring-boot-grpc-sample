package com.info.sovannarith

import com.info.sovannarith.generated.CourseProto
import com.info.sovannarith.generated.CourseProto.GetFooResponse
import com.info.sovannarith.generated.FooServiceGrpc
import io.grpc.StatusRuntimeException
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class GrpcClientService {
    @GrpcClient("grpc-server")
    private val simpleStub: FooServiceGrpc.FooServiceBlockingStub? = null
    fun sendMessage(name: String?): String {
        return try {
            val response: GetFooResponse = simpleStub?.getFoo(CourseProto.GetFooRequest.getDefaultInstance()) ?: throw Exception("")
            response.fooName
        } catch (e: StatusRuntimeException) {
            "FAILED with " + e.status.code.name
        }
    }
}