package com.example.sockwarehouse.service;

import com.example.sockwarehouse.model.socks.SockBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StoreOperationService {
    void accept(SockBatch socksBatch);

    void issuance(SockBatch socksBatch);

    void reject(SockBatch socksBatch);

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;
}
