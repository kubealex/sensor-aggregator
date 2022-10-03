package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.model.SensorData;
import org.acme.service.InfluxDBServiceException;
import org.acme.service.impl.InfluxDBService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class SensorAggregator {
    @Inject
    InfluxDBService influxDBService;

    /**
     * @param data
     */
    @Incoming("test-in")
    public void consume(JsonObject data) throws InfluxDBServiceException{

        Log.info("Received sensor data from controller");
        Log.info(data.toString());
        SensorData sensorData = data.mapTo(SensorData.class);
        influxDBService.writeData(sensorData);
        Log.info("Sensor data processed and written on InfluxDB");
    }
}