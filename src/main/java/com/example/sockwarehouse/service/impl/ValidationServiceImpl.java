package com.example.sockwarehouse.service.impl;


import com.example.sockwarehouse.model.operation.OperationType;
import com.example.sockwarehouse.model.operation.StoreOperation;
import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.SockColor;
import com.example.sockwarehouse.model.socks.SockSize;
import com.example.sockwarehouse.service.StoreOperationService;
import com.example.sockwarehouse.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SockBatch socksBatch) {
        return socksBatch.getSocks() != null &&
                socksBatch.getQuantity() > 0 &&
                socksBatch.getSocks().getColor() != null &&
                socksBatch.getSocks().getSize() != null &&
                cottonCheck(socksBatch.getSocks().getStructure(), socksBatch.getSocks().getStructure());
    }

    @Override
    public boolean validate(SockColor color, SockSize size, int cottonMin, int cottonMax) {
        return color != null && size != null && cottonCheck(cottonMin, cottonMax);
    }

    private boolean cottonCheck(int cottonMin, int cottonMax) {
        return cottonMin >= 0 && cottonMax <= 100;
    }

    @Service
    public static class StoreOperationServiceImpl implements StoreOperationService {

        private final List<StoreOperation> operationList = new ArrayList<>();

        @Override
        public void accept(SockBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.ACCEPT, socksBatch));

        }

        @Override
        public void issuance(SockBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.ISSUANCE, socksBatch));
        }

        @Override
        public void reject(SockBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.REJECT, socksBatch));
        }
    }
}
