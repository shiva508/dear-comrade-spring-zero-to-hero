package com.comrade.repository;

import com.comrade.domine.AzureFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AzureFileRepository extends JpaRepository<AzureFileEntity, Long> {
}
