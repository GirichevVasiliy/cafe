package com.girichev.cafe.owner.service;

import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Override
    public String getOwner(Long inn) {
        return "Hello Lis";
    }
}
