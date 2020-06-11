package pl.akademiaspringa.homework_week7.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspringa.homework_week7.Dao.ArticleDaoImpl;
import pl.akademiaspringa.homework_week7.models.Article;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    private final ArticleDaoImpl articleDao;

    @Autowired
    public ArticleController(ArticleDaoImpl articleDao) {
        this.articleDao = articleDao;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        return articleDao.getAll()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return articleDao.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    @PostMapping
    public ResponseEntity<Article> postArticle(@RequestBody Article article) {
        return articleDao.save(article)
                .map(articleItem -> ResponseEntity.status(201).body(article))
                .orElse(ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleDao.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Article> putArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleDao.update(id, article)
                .map(articleItem -> ResponseEntity.accepted().body(articleItem))
                .orElse(ResponseEntity.notFound().build());
    }
}
