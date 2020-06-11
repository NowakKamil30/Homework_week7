package pl.akademiaspringa.homework_week7.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.akademiaspringa.homework_week7.models.Article;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${news.url}")
    private String url;
    @Value("${news.key}")
    private String key;


    public List<Article> getArticles() {
        JsonNode jsonNode = restTemplate.getForObject(url + "&apiKey=" + key, JsonNode.class);
        assert jsonNode != null;
        return getArticlesList(jsonNode.get("articles"));
    }

    private List<Article> getArticlesList(JsonNode jsonNode) {
        List<Article> articles = new ArrayList<>();
        for(JsonNode jsonNodeItem : jsonNode) {
            articles.add(new Article(
                    jsonNodeItem.get("author").toString(),
                    jsonNodeItem.get("title").toString(),
                    jsonNodeItem.get("description").toString(),
                    jsonNodeItem.get("url").toString(),
                    jsonNodeItem.get("urlToImage").toString()
            ));
        }
        return articles;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
