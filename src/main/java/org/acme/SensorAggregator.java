package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.acme.model.SensorData;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
@ApplicationScoped
public class SensorAggregator {

    @ConfigProperty(name = "influxdb.url",defaultValue = "http://localhost:8086")
    String influxdbURL;
    @ConfigProperty(name = "influxdb.token")
    String influxdbToken;
    @ConfigProperty(name = "influxdb.bucket")
    String influxdbBucket;
    @ConfigProperty(name = "influxdb.organization")
    String influxdbOrg;

    @Incoming("test-in")
    public void consume(JsonObject data){
        InfluxDBClient client = InfluxDBClientFactory.create(influxdbURL, influxdbToken.toCharArray());

        Log.info("Received sensor data from controller");
        SensorData sensorData = data.mapTo(SensorData.class);
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(influxdbBucket, influxdbOrg, WritePrecision.NS, sensorData);
        Log.info("Sensor data processed and written on InfluxDB");
    }
}