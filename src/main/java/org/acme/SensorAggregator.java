package org.acme;

import org.acme.model.SensorData;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
public class SensorAggregator {

    @Incoming("test-in")
    public void consume(JsonObject data){
        Log.info("Received sensor data from controller");
        SensorData sensorData = data.mapTo(SensorData.class);
        System.out.println(data.toString());
    }
}