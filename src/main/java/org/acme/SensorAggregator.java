package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.acme.model.SensorData;
import org.acme.service.InfluxDBService;
import org.acme.service.InfluxDBServiceException;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class SensorAggregator {
    @Inject
    InfluxDBService influxDBService;

    @Incoming("sensor-data-in")
    public void consume(byte[] data) throws InfluxDBServiceException {

        Log.info("Received sensor data from controller");
        String payload = new String(data);
        JsonObject messageJson = new JsonObject(payload);
        Log.info(messageJson);
        SensorData sensorData = messageJson.mapTo(SensorData.class);
        Log.info(sensorData);

        influxDBService.writeData(sensorData);
        Log.info("Sensor data processed and written on InfluxDB");
    }
}