package com.example.sockwarehouse.model.operation;

import com.example.sockwarehouse.model.socks.SockBatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreOperation {
    private final LocalDateTime dateTime = LocalDateTime.now();
    private OperationType type;
    private SockBatch socksBatch;
}
