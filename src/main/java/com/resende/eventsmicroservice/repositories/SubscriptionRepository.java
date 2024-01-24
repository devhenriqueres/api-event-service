package com.resende.eventsmicroservice.repositories;

import com.resende.eventsmicroservice.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {



}
