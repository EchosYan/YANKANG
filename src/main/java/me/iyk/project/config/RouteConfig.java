package me.iyk.project.config;

import me.iyk.project.handler.SysHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouteConfig {
    @Bean
    public RouterFunction<ServerResponse> routSayHi(SysHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("webflux/sys/healthCheck")
                        .and(RequestPredicates.accept(MediaType.ALL)), handler::healthCheck);
    }
}
