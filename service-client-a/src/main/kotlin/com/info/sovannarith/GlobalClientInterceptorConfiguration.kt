package com.info.sovannarith

import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
class GlobalClientInterceptorConfiguration {
    @GrpcGlobalClientInterceptor
    fun logClientInterceptor(): LogGrpcInterceptor {
        return LogGrpcInterceptor()
    }
}