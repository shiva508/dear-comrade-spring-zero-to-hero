package com.comrade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.WildcardQuery;
import com.comrade.entity.Product;
import com.comrade.model.CommonResponse;
import com.comrade.repository.ProductRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {

	private static final String PRODUCT_INDEX = "productindex";

	@Autowired
	private ProductRepository productRepository;

	/*
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	*/
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Override
	public CommonResponse createProductIndexBulk(List<Product> products) {
		productRepository.saveAll(products);
		return new CommonResponse();
	}

	@Override
	public CommonResponse createProductIndex(Product product) {
		productRepository.save(product);
		return new CommonResponse();
	}

	@Override
	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		return productRepository.findByNameContaining(name);
	}

	@Override
	public List<Product> findByManufacturerAndCategory(String manufacturer, String category) {
		return productRepository.findByManufacturerAndCategory(manufacturer, category);
	}

	@Override
	public CommonResponse createProductIndexBulkIndexing(List<Product> products) {

		List<IndexQuery> queries = products.stream()
				.map(product -> new IndexQueryBuilder().withId(product.getId().toString()).withObject(product).build())
				.collect(Collectors.toList());
		elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(PRODUCT_INDEX));
		return new CommonResponse();
	}

	@Override
	public String createProductIndexIndexing(Product product) {

		IndexQuery indexQuery = new IndexQueryBuilder().withId(product.getId().toString()).withObject(product).build();
		String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(PRODUCT_INDEX));
		return documentId;
	}

	@Override
	public CommonResponse findProductsByBrandSearchingNativeQuery(String brandName) {

		/*
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("manufacturer", brandName);

		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<Product> productHits = elasticsearchOperations.search(searchQuery, Product.class,IndexCoordinates.of(PRODUCT_INDEX));
		List<Product> indexedProduct = productHits.get().map(SearchHit::getContent).collect(Collectors.toList());
		return new CommonResponse().setProducts(indexedProduct);
		*/

		MatchQuery matchQuery = new MatchQuery.Builder().field("manufacturer").query(brandName).build();
		NativeQuery nativeQuery = NativeQuery.builder().withQuery(builder -> builder.match(matchQuery)).build();
		SearchHits<Product> productHits = elasticsearchOperations.search(nativeQuery, Product.class, IndexCoordinates.of(PRODUCT_INDEX));
		List<Product> indexedProduct = productHits.get().map(SearchHit::getContent).collect(Collectors.toList());
		return new CommonResponse().setProducts(indexedProduct);
	}

	@Override
	public CommonResponse findByProductNameSearchingStringQuery(String productName) {
		Query searchQuery = new StringQuery("{\"match\":{\"name\":{\"query\":\"" + productName + "\"}}}\"");

		SearchHits<Product> products = elasticsearchOperations.search(searchQuery, Product.class,IndexCoordinates.of(PRODUCT_INDEX));
		List<Product> indexedProduct = products.get().map(SearchHit::getContent).collect(Collectors.toList());
		return new CommonResponse().setProducts(indexedProduct);
	}

	@Override
	public CommonResponse findByProductPriceSearchingCriteriaQuery(String productPrice) {
		Criteria criteria = new Criteria("price").greaterThan(10.0).lessThan(100.0);

		Query searchQuery = new CriteriaQuery(criteria);

		SearchHits<Product> products = elasticsearchOperations.search(searchQuery, Product.class,IndexCoordinates.of(PRODUCT_INDEX));
		List<Product> indexedProducts = products.get().map(SearchHit::getContent).collect(Collectors.toList());
		return new CommonResponse().setProducts(indexedProducts);
	}

	@Override
	public List<Product> processSearchMultiFieldFuzzySearch(String query) {
		/*
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query, "name", "description").fuzziness(Fuzziness.AUTO);

		Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();

		// 2. Execute search
		SearchHits<Product> productHits = elasticsearchOperations.search(searchQuery, Product.class, IndexCoordinates.of(PRODUCT_INDEX));

		// 3. Map searchHits to product list
		List<Product> productMatches = new ArrayList<Product>();
		productMatches = productHits.get().map(SearchHit::getContent).collect(Collectors.toList());

		return productMatches;*/

		// 1. Create query on multiple fields enabling fuzzy search
		MultiMatchQuery multiMatchQuery = new MultiMatchQuery.Builder().fields("name", "description").query(query).fuzziness(Fuzziness.AUTO.asString()).build();
		NativeQuery nativeQuery = NativeQuery.builder().withQuery(builder -> builder.multiMatch(multiMatchQuery)).build();

		// 2. Execute search
		SearchHits<Product> productHits = elasticsearchOperations.search(nativeQuery, Product.class, IndexCoordinates.of(PRODUCT_INDEX));

		// 3. Map searchHits to product list
		List<Product> productMatches = new ArrayList<Product>();
		productMatches = productHits.get().map(SearchHit::getContent).collect(Collectors.toList());

		return productMatches;
	}

	@Override
	public List<String> fetchSuggestionsWildcardSearch(String query) {
		/*
		QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", query + "*");

		Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).withPageable(PageRequest.of(0, 5)).build();

		SearchHits<Product> searchSuggestions = elasticsearchOperations.search(searchQuery, Product.class, IndexCoordinates.of(PRODUCT_INDEX));

		List<String> suggestions = new ArrayList<String>();
		suggestions = searchSuggestions.getSearchHits().stream().map(data -> data.getContent().getName()).collect(Collectors.toList());

		return suggestions;
		*/
		WildcardQuery wildcardQuery = new WildcardQuery.Builder().field("name").wildcard(query + "*").build();
		NativeQuery nativeQuery = NativeQuery.builder().withQuery(builder -> builder.wildcard(wildcardQuery)).withPageable(PageRequest.of(0, 5)).build();
		SearchHits<Product> searchSuggestions = elasticsearchOperations.search(nativeQuery, Product.class, IndexCoordinates.of(PRODUCT_INDEX));
		List<String> suggestions = new ArrayList<String>();
		suggestions = searchSuggestions.getSearchHits().stream().map(data -> data.getContent().getName()).collect(Collectors.toList());
		return suggestions;
	}


}
