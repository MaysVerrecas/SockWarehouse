package com.example.sockwarehouse.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SockBatch {
    private Socks socks;
    private int quantity; //количество носков на складе
}
