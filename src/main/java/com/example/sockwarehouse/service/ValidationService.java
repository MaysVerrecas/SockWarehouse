package com.example.sockwarehouse.service;

import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.SockColor;
import com.example.sockwarehouse.model.socks.SockSize;

public interface ValidationService {
    boolean validate(SockBatch socksBatch);

    boolean validate(SockColor color, SockSize size, int cottonMin, int cottonMax);
}
