package com.info.sovannarith

import io.grpc.ServerInterceptor
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import com.info.sovannarith.LogGrpcInterceptor
import io.grpc.Metadata
import org.slf4j.LoggerFactory

/**
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/12/6
 */
class LogGrpcInterceptor : ServerInterceptor {
    override fun <ReqT, RespT> interceptCall(
        serverCall: ServerCall<ReqT, RespT>, metadata: Metadata,
        serverCallHandler: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        log.info(serverCall.methodDescriptor.fullMethodName)
        return serverCallHandler.startCall(serverCall, metadata)
    }

    companion object {
        private val log = LoggerFactory.getLogger(LogGrpcInterceptor::class.java)
    }
}