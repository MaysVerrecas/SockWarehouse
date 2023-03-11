package com.example.sockwarehouse.service.impl;

import com.example.sockwarehouse.exception.ValidationException;
import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.SockColor;
import com.example.sockwarehouse.model.socks.SockSize;
import com.example.sockwarehouse.model.socks.Socks;
import com.example.sockwarehouse.model.socks.repository.SocksRepository;
import com.example.sockwarehouse.service.FileService;
import com.example.sockwarehouse.service.StoreOperationService;
import com.example.sockwarehouse.service.ValidationService;
import com.example.sockwarehouse.service.WarehouseService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private final StoreOperationService operationService;

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public void add(SockBatch socksBatch) {
        try {
            checkForValidate(socksBatch);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Переданые параметры некорректны.");
        }
        operationService.accept(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int edit(SockBatch socksBatch) {
        try {
            checkForValidate(socksBatch);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        operationService.issuance(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(SockColor color, SockSize size, int cottonMin, int cottonMax) throws ValidationException {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksIntegerMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksItem : socksIntegerMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getStructure() >= cottonMin &&
                    socks.getStructure() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(), Path.of(dataFilePath)).toFile();
    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        List<SockBatch> socksBatchList = fileService.uploadFromFile(file, Path.of(dataFilePath), new TypeReference<List<SockBatch>>() {
        });
        socksRepository.replace(socksBatchList);
    }

    private void checkForValidate(SockBatch socksBatch) throws ValidationException {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
