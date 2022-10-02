package org.acme.service;

import org.acme.model.SensorData;

public interface IInfluxDBService {
    /**
     * @param sensorData
     */
    void writeData(SensorData sensorData);
}
