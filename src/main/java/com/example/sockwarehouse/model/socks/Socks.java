package com.example.sockwarehouse.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {
    private SockColor color;
    private SockSize size;
    private int structure;

}
