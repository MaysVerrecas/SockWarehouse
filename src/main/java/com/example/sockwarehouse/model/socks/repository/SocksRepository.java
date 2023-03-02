package com.example.sockwarehouse.model.socks.repository;

import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.Socks;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface SocksRepository {
    void save(SockBatch socksBatch);

    int remove(SockBatch socksBatch);

    Map<Socks, Integer> getAll();

    List<SockBatch> getList();

    void replace(List<SockBatch> sockBatchList);
}
