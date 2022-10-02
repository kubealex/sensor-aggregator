package org.acme.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name = "SensorData")
public class SensorData {
    @JsonProperty("sensor_location")
    @Column(tag = true)
    private String location;
    @Column
    @JsonProperty("sensor_data")
    private Double temperature;
    @Column(tag = true)
    @JsonProperty("sensor_id")
    private String deviceID;
    @Column(timestamp = true)
    @JsonProperty("sensor_timestamp")
    private Instant timestamp;
    
    public SensorData(String location, Double temperature, String deviceID, Instant timestamp) {
        this.location = location;
        this.temperature = temperature;
        this.deviceID = deviceID;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public String getDeviceID() {
        return deviceID;
    }
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
