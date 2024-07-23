package com.comrade.repository;

import java.util.List;

import com.comrade.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
	List<Product> findByName(String name);

	List<Product> findByNameContaining(String name);

	List<Product> findByManufacturerAndCategory(String manufacturer, String category);
}
