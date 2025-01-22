package com.bibliotex.catalog;

import org.springframework.boot.SpringApplication;

public class TestCatalogBibliotexApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalogBibliotexApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
