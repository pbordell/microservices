package com.pbs.report.client;

import com.pbs.report.dto.CrudDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

import jakarta.ws.rs.client.Entity;

public class NodeNotificationClient {

  public NodeNotificationClient() {}

  public void sendEmailNotification(String reportName, String token) {
    try {
      Client client = ClientBuilder.newClient();

      Map<String, String> payload = new HashMap<>();
      payload.put("to", "xxx");
      payload.put("reportName", reportName);
      payload.put("generatedBy", "Usuario_Sandbox_Struts");

      client
          .target("http://micro-notification-node:3000/notifications/send-email")
          .request()
          .header("Authorization", token)
          .async()
          .post(Entity.entity(payload, MediaType.APPLICATION_JSON));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
