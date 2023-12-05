package com.ansar.batch.config;

import com.ansar.batch.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        int discountPrice = Integer.parseInt(item.getDiscount());
        double originalPrice = Double.parseDouble(item.getPrice());
        double discount = (discountPrice / 100) * originalPrice;
        double finalPrice = originalPrice - discount;
        item.setDiscounted_price(String.valueOf(finalPrice));
        return item;
    }
}
