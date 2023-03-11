package com.example.sockwarehouse.model.socks.repository.impl;

import com.example.sockwarehouse.model.socks.SockBatch;
import com.example.sockwarehouse.model.socks.Socks;
import com.example.sockwarehouse.model.socks.repository.SocksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private final HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SockBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int remove(SockBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);

            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }

    @Override
    public List<SockBatch> getList() {
        List<SockBatch> socksBatchList = new ArrayList<>();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            socksBatchList.add(new SockBatch(socksItem.getKey(), socksItem.getValue()));
        }
        return socksBatchList;
    }


    @Override
    public void replace(List<SockBatch> sockBatchList) {
        socksMap.clear();

        for (SockBatch batch : sockBatchList) {
            save(batch);

        }
    }
}
