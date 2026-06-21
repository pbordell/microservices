package com.pbs.report.client;

import com.pbs.report.dto.CrudDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType; // IMPORTACIÓN CRÍTICA
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import java.util.List;

public class CoreCrudsClient {

  public CoreCrudsClient() {}

  public List<CrudDTO> getCrusAll(String token) {
    Client client = ClientBuilder.newBuilder()
            .register(JacksonJsonProvider.class)
            .build();

    WebTarget target = client.target("http://apigateway:8082/core-api/CrudBasic");
    target = target.path("/cruds/");

    Invocation.Builder builder = target.request().header("Authorization", token);

    // CORRECCIÓN TOTAL: Forzamos a Jackson a tipar la lista de forma exacta
    return builder.get(new GenericType<List<CrudDTO>>() {});
  }
}
