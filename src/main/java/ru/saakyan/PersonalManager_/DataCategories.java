package ru.saakyan.PersonalManager_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataCategories {
    private final transient Map<String, String> categories;
    private final transient Map<String, List<Product>> productsForCategory;
    private MaxCategory maxCategory;

    public DataCategories() throws IOException {
        this.categories = loadCategories();
        productsForCategory = new HashMap<>();
    }

    public static Map loadCategories() throws IOException {
        Map categories = new HashMap<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("categories.tsv"))) {
            while (bfr.ready()) {
                String[] line = bfr.readLine().split("\t");
                categories.put(line[0], line[1]);
            }
        }
        return categories;
    }

    public Map<String, List<Product>> getProductsForCategory() {
        return productsForCategory;
    }

    public MaxCategory getMaxCategory() {
        return maxCategory;
    }

    public String determineCategory(Product product) {
        String productName = product.getTitle();
        if (categories.containsKey(productName)) {
            return categories.get(productName);
        }
        return "другое";
    }

    public void accept(Product product) {
        String categoryName = determineCategory(product);
        if (productsForCategory.containsKey(categoryName)) {
            productsForCategory.get(categoryName).add(product);
        } else {
            List<Product> products = new ArrayList<>();
            products.add(product);
            productsForCategory.put(categoryName, products);
        }
    }

    public void processing(Product product) {
        accept(product);
        findMaxCategory();
    }

    public void findMaxCategory() {

        Map<String, Double> categorySum = new HashMap<>();
        double maxSum = 0.0;
        for (String category : productsForCategory.keySet()) {
            double sum = 0.0;
            for (Product element : productsForCategory.get(category)) {
                sum += element.getSum();
            }
            categorySum.put(category, sum);
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        for (String category : categorySum.keySet()) {
            if (categorySum.get(category) == maxSum) {
                maxCategory = new MaxCategory(category, maxSum);
            }
        }
    }

    public class MaxCategory {

        private final String category;
        private final double sum;

        public MaxCategory(String category, double sum) {
            this.category = category;
            this.sum = sum;
        }

        @Override
        public int hashCode() {
            int result = category == null ? 0 : category.hashCode();
            result = 31 * result + (int) sum;
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            MaxCategory maxCategory = (MaxCategory) obj;
            return sum == maxCategory.sum && category.equals(maxCategory.category);
        }
    }
}