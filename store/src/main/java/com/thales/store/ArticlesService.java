package com.demo.store;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import shared.models.Article;

/**
 * ArticlesService
 */
@Service
public class ArticlesService {

    private static List<Article> articles = List.of(
            new Article(1L, "Originals", "ADIDAS", "SPORTS WEAR", "NA", 50.0, 2),
            new Article(2L, "Air Max", "NIKE", "SPORTS WEAR", "NA", 80.6, 90),
            new Article(2L, "ZoomX", "NIKE", "SPORTS WEAR", "NA", 90.6, 7),
            new Article(3L, "Syntax", "DCSHOE", "SPORTS WEAR", "NA", 110.0, 57));

    public List<Article> getAllArticles() {
        return ArticlesService.articles;
    }

    public List<Article> getArticlesByBrand(String brand) {
        Predicate<Article> articleByBrand = article -> {
            return article.getBrand().matches(".*" + brand.toUpperCase() + ".*");
        };
        return ArticlesService.articles.stream().filter(articleByBrand).collect(Collectors.toList());
    }

}