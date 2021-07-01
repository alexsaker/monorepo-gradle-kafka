package com.demo.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shared.models.Article;
import shared.models.ArticleDto;
import shared.models.ArticleEvent;

/**
 * ArticlesController
 */

@RestController
@RequestMapping("articles")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    private KafkaTemplate<String, Article> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, ArticleEvent> kafkaArticleEventTemplate;

    @Value("${store.kafka-topic}")
    private String kafkaTopic;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(@RequestParam(required = false) String brand) {
        List<Article> articles;
        try {
            if (brand != null) {
                articles = this.articlesService.getArticlesByBrand(brand);
                this.kafkaTemplate.send(this.kafkaTopic, articles.get(0));
            } else {
                articles = this.articlesService.getAllArticles();
                this.kafkaTemplate.send(this.kafkaTopic, articles.get(0));
            }
            return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
        } catch (RuntimeException e) {
            articles = List.of();
            return new ResponseEntity<List<Article>>(articles, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDto article) {
        Article art = new Article(11L, article.getLabel(), article.getBrand(), article.getType(),
                article.getDescription(), article.getPrice(), article.getInternalStock());
        this.kafkaArticleEventTemplate.send(this.kafkaTopic,
                new ArticleEvent(art.getId(), art.getLabel(), ArticleEvent.Action.SUB, 1));
        return new ResponseEntity<Article>(art, HttpStatus.OK);
    }
}

