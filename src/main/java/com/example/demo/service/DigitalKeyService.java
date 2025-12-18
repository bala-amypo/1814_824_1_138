package com.example.demo.service;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {

    DigitalKey createKey(DigitalKey digitalKey);

    DigitalKey getByKeyValue(String keyValue);

    boolean canShareKey(String keyValue);
}
