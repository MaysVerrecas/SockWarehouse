package com.example.sockwarehouse.model.socks;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Socks {
    private SockColor color;
    private SockSize size;
    private int structure;


    public Socks(SockColor color, SockSize size, int structure) {
        this.color = color;
        this.size = size;
        if (structure >= 0 && structure <= 100) {
            this.structure = structure;
        } else {
            throw new IllegalArgumentException("Необходимо ввести содержание хлопка в % целым числом");
        }
    }
}
