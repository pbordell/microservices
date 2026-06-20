package com.pbs.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getURI().getPath();

    log.info("[GATEWAY-LOG] Interceptando path: {}", path);

    // 1. Bypass automático para la generación de tokens
    if (path.contains("/oauth/token")) {
      return chain.filter(exchange);
    }

    // 2. Validación de presencia de la cabecera
    if (!request.getHeaders().containsKey("Authorization")) {
      log.error("[GATEWAY-LOG] Denegado: Petición sin cabecera Authorization.");
      return onError(exchange, HttpStatus.UNAUTHORIZED);
    }

    String authHeader = request.getHeaders().getFirst("Authorization");

    // 3. Validación de formato de cabecera
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      log.error("[GATEWAY-LOG] Denegado: Formato de cabecera inválido.");
      return onError(exchange, HttpStatus.UNAUTHORIZED);
    }

    String token = authHeader.substring(7);

    // 4. Filtro de control local: Si contiene un token con texto real, le da paso seguro al Core
    if (token.trim().isEmpty() || token.length() < 5) {
      log.error("[GATEWAY-LOG] Denegado: El token enviado está vacío o es demasiado corto.");
      return onError(exchange, HttpStatus.UNAUTHORIZED);
    }

    log.info("[GATEWAY-LOG] Token verificado en pasarela de desarrollo. Redirigiendo al microservicio...");
    return chain.filter(exchange);
  }

  private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(status);
    return response.setComplete();
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
