package com.example.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Location {

    String status;
    String country;
    String countryCode;
    String regionName;
    String city;
    double lat;
    double lon;
    String timezone;
}
