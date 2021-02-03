package com.smartclean.smartcleanstepcounter.services;

import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class GenerateUniqueId {
    public String generateUniqueId(){
        return Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(), Character.MAX_RADIX);
    }
}
