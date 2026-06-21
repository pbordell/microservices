package com.pbs.report.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import java.util.HashMap;
import java.util.Map;

public class NodeNotificationClient {

  public NodeNotificationClient() {}

  public void sendEmailNotification(String reportName, String token) {
    try {
      Client client = ClientBuilder.newBuilder()
              .register(JacksonJsonProvider.class)
              .build();

      Map<String, String> payload = new HashMap<>();
      payload.put("to", "xxx");
      payload.put("reportName", reportName);
      payload.put("generatedBy", "Usuario_Sandbox_Struts");

      client
              .target("http://apigateway:8082/notifications/send-email")
              .request()
              .header("Authorization", token)
              .async()
              .post(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
