package com.info.sovannarith

import io.grpc.*
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
import io.grpc.Metadata
import org.slf4j.LoggerFactory

/**
 * Example [ClientInterceptor] that logs all called methods in INFO log level, also request and response messages,
 * headers, trailers and interaction status in DEBUG log level. In this example it is added to Spring's application
 * context via [GlobalClientInterceptorConfiguration], but is also possible to directly annotate this class with
 * [GrpcGlobalClientInterceptor].
 *
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/12/8
 */
class LogGrpcInterceptor : ClientInterceptor {
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        log.info("Received call to {}", method.fullMethodName)
        return object : SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            override fun sendMessage(message: ReqT) {
                log.debug("Request message: {}", message)
                super.sendMessage(message)
            }

            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                super.start(
                    object : SimpleForwardingClientCallListener<RespT>(responseListener) {
                        override fun onMessage(message: RespT) {
                            log.debug("Response message: {}", message)
                            super.onMessage(message)
                        }

                        override fun onHeaders(headers: Metadata) {
                            log.debug("gRPC headers: {}", headers)
                            super.onHeaders(headers)
                        }

                        override fun onClose(status: Status, trailers: Metadata) {
                            log.info("Interaction ends with status: {}", status)
                            log.info("Trailers: {}", trailers)
                            super.onClose(status, trailers)
                        }
                    }, headers
                )
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LogGrpcInterceptor::class.java)
    }
}