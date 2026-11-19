package com.tasgroup.spring_course;

import com.tasgroup.spring_course.controller.model.ProductRequest;
import com.tasgroup.spring_course.repository.ProductMongoRepository;
import com.tasgroup.spring_course.repository.model.ProductDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Map;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestRestTemplate
@ActiveProfiles("mongo")
//@Testcontainers
public class ProductCatalogIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProductMongoRepository productMongoRepository;

    @BeforeEach
    void setUp(){
        productMongoRepository.deleteAll();
    }

    @Test
    void testProductCreate() {
        ProductRequest request = new ProductRequest(
                "Lenovo ThinkPad",
                "Notebook professionale",
                new BigDecimal("1299.99"),
                "NOTEBOOK",
                "Lenovo",
                Map.of(
                        "processor", "Intel i7",
                        "ram", 32,
                        "storage", "1TB SSD",
                        "screenSize", 15.6,
                        "touchscreen",
                        false
                )
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/catalog/products",
                request,
                String.class
        );

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().contains("Lenovo ThinkPad"));

        ProductDocument product = productMongoRepository.findAll()
                .stream()
                .filter(productDocument -> productDocument.getName().equals("Lenovo ThinkPad"))
                .findFirst().orElseThrow();

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals("Lenovo ThinkPad", product.getName());
    }
}
