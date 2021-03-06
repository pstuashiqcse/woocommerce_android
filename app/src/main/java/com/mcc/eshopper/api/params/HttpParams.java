package com.mcc.eshopper.api.params;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ashiq on 8/13/2016.
 */
public class HttpParams {

    /*private static final String BASE_URL = "https://testing-e-commerce.000webhostapp.com/wp-json/wc/";

    private static final String API_WC_V1 = "v1";
    private static final String API_WC_V2 = "v2";

    private static final String API_WC_PRODUCTS = "products";
    public static String getRegistrationUrl() {
        return BASE_URL + API_REGISTRATION;
    }*/

    /*https://testing-e-commerce.000webhostapp.com/wc-api/v1/products
    https://testing-e-commerce.000webhostapp.com/wc-api/v1/orders

    https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products
    https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products/41/reviews
    https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products/categories
    https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products/categories/6

    https://testing-e-commerce.000webhostapp.com/wp-json/wp/v2/posts
    https://testing-e-commerce.000webhostapp.com/wp-json/wp/v2/pages*/

    public static String categoriesApi() {
        return "http://mprod.mcc.com.bd/wordpress?rest_route=/wc/v2products/categories?consumer_key=ck_c8ea9ceadd3c5ac59aad9d8184432f6a0677f35e&consumer_secret=cs_c6b015274aa1e548c62e422e24e00dbd449b283c";
    }
//    public static String categoriesApi() {
//        return "https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products/categories?consumer_key=ck_efd5ff803ea0468123ed4725cc76069a6f35faf7&consumer_secret=cs_74ed2623251ed5b6e793f65547639fd3b59eb0c1";
//    }

    public static String categoryItemApi() {
        return "https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products?consumer_key=ck_efd5ff803ea0468123ed4725cc76069a6f35faf7&consumer_secret=cs_74ed2623251ed5b6e793f65547639fd3b59eb0c1";
    }

    public static String productDetailsApi(int id){
        return "https://testing-e-commerce.000webhostapp.com/wp-json/wc/v1/products/" + id + "?consumer_key=ck_efd5ff803ea0468123ed4725cc76069a6f35faf7&consumer_secret=cs_74ed2623251ed5b6e793f65547639fd3b59eb0c1";
    }
}