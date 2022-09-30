package org.acme;

import org.acme.model.SensorData;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
public class SensorAggregator {

    @Incoming("test-in")
    public void consume(JsonObject data){
        SensorData test = data.mapTo(SensorData.class);
        System.out.println(test);
        System.out.println(data.toString());
    }
}