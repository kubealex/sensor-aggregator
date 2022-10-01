package org.acme.service;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;

@ApplicationScoped
public class Producer {
    @ConfigProperty(name = "influxdb.url",defaultValue = "http://localhost:8086")
    String influxdbURL;
    @ConfigProperty(name = "influxdb.token")
    String influxdbToken;
    private InfluxDBClient influxDBClient;
    @Produces
    @Named
    public InfluxDBClient getInfluxDB() {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(influxdbURL, influxdbToken.toCharArray());
        return influxDBClient;
    }
}