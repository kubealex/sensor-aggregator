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
    @Column
    @JsonProperty("sensor_eco_mode")
    protected Boolean ecoMode;
    @Column
    @JsonProperty("sensor_fan_speed")
    protected Integer fanSpeed;
    @Column
    @JsonProperty("sensor_fixed_temperature")
    protected Integer fixedTemperature;
    @Column
    @JsonProperty("sensor_power_consumption")
    protected Integer powerConsumption;
    @JsonProperty("sensor_id")
    @Column(tag = true)
    protected String deviceID;
    @JsonProperty("sensor_timestamp")
    @Column(timestamp = true)
    protected Instant timestamp;

    public SensorData(String location, Double temperature, Double humidity, String deviceID, Boolean ecoMode,
            Integer fanSpeed, Integer fixedTemperature, Integer powerConsumption) {
        this.temperature = temperature;
        this.fixedTemperature = fixedTemperature;
        this.humidity = humidity;
        this.location = location;
        this.deviceID = deviceID;
        this.timestamp = Instant.now();
        this.fanSpeed = fanSpeed;
        this.ecoMode = ecoMode;
        this.powerConsumption = powerConsumption;
    }
}
