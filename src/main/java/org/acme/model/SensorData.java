package org.acme.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
@Measurement(name = "SensorData")
public class SensorData {
    @JsonProperty("sensor_location")
    @Column(tag = true)
    protected String location;
    @Column
    @JsonProperty("sensor_temperature_data")
    protected Double temperature;
    @Column
    @JsonProperty("sensor_humidity_data")
    protected Double humidity;
    @JsonProperty("sensor_id")
    @Column(tag = true)
    protected String deviceID;
    @JsonProperty("sensor_timestamp")
    @Column(timestamp = true)
    protected Instant timestamp;

 public SensorData(String location, Double temperature, Double humidity, String deviceID) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.location = location;
        this.deviceID = deviceID;
        this.timestamp = Instant.now();
    }
}
