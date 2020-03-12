/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.configurationsw;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2

@Configuration

public class SpringFoxConfig {

    /**

     * Publish a bean to generate swagger2 endpoints

     * @return a swagger configuration bean

     */

    @Bean

    public Docket usersApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(usersApiInfo())

                .select()

                .paths(userPaths())

                .apis(RequestHandlerSelectors.any())

                .build()

                .useDefaultResponseMessages(false);

    }

   private ApiInfo usersApiInfo() {

        return new ApiInfoBuilder()

                .title("Service object")

                .version("1.0")

                .license("Apache License Version 2.0")

                .build();

    }

    private Predicate userPaths() {

        return regex("/object.*");

    }

}