package com.example.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// @ApplicationPath("/api/v1")
public class MyApplication extends Application {
    // More Quarkus-ish is to define quarkus.http.root-path in application.properties
}
