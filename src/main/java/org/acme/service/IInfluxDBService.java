package org.acme.service;

import org.acme.model.SensorData;

public interface IInfluxDBService {
    void writeData(SensorData sensorData) throws InfluxDBServiceException;
}
