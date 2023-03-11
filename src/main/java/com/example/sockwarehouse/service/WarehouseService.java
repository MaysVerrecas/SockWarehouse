package com.example.sockwarehouse.service;

import com.example.sockwarehouse.exception.ValidationException;
import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.SockColor;
import com.example.sockwarehouse.model.socks.SockSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface WarehouseService {
    void add(SockBatch socksBatch);

    int edit(SockBatch socksBatch);

    int getCount(SockColor color, SockSize size, int cottonMin, int cottonMax) throws ValidationException;

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;
}
