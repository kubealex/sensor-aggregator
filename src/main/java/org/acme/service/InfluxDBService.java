package org.acme.service;

import javax.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import org.acme.model.SensorData;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.exceptions.InfluxException;

@ApplicationScoped
public class InfluxDBService {
    @ConfigProperty(name = "influxdb.url", defaultValue = "http://localhost:8086")
    String influxdbURL;
    @ConfigProperty(name = "influxdb.token")
    @Nonnull
    String influxdbToken;
    @ConfigProperty(name = "influxdb.bucket")
    String influxdbBucket;
    @ConfigProperty(name = "influxdb.organization")
    String influxdbOrg;
    private InfluxDBClient influxDBClient;

    @PostConstruct
    private void initializeInfluxDBClient() {
        this.influxDBClient = InfluxDBClientFactory.create(influxdbURL, influxdbToken.toCharArray());
    }

    public void writeData(SensorData sensorData) throws InfluxDBServiceException {
        try {
            WriteApi writeApi = influxDBClient.makeWriteApi();
            writeApi.writeMeasurement(influxdbBucket, influxdbOrg, WritePrecision.NS, sensorData);
            writeApi.close();
        } catch (InfluxException ie) {
            throw new InfluxDBServiceException("An error occurred while writing to InfluxDB: " + ie.getMessage());
        }
    }
}
