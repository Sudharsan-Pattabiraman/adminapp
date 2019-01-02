package com.b2s.service.adminapp.orderservice.dao;

import com.b2s.service.adminapp.orderservice.model.Address;

import java.util.List;
import java.util.Optional;

/**
 * Created by spattabiraman on 12/24/2018.
 */
public interface AddressDAO {

    Address save(final Address address);

    Optional<Address> findByPlatformOrderId(final long platformOrderId);

    List<Address> getAllAddress();
}
