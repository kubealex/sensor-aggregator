package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.model.SensorData;
import org.acme.service.impl.InfluxDBService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;

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
    public void consume(JsonObject data){

        Log.info("Received sensor data from controller");
        Log.info(data.toString());
        SensorData sensorData = data.mapTo(SensorData.class);
        influxDBService.writeData(sensorData);
        Log.info("Sensor data processed and written on InfluxDB");
    }
}