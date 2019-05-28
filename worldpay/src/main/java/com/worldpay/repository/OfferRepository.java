package com.worldpay.repository;

import org.springframework.data.repository.CrudRepository;
import com.worldpay.domain.Offer;

public interface OfferRepository extends CrudRepository<Offer, Integer> {

}
