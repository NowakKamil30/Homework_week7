package pl.akademiaspringa.homework_week7.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.akademiaspringa.homework_week7.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArticleDaoImpl implements Dao<Article, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM articles WHERE articles.id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Article> save(Article article) {
        String sql = "INSERT INTO articles (" +
                "author," +
                " title," +
                " description," +
                " url," +
                "url_to_image) Values (?,?,?,?,?)";
        jdbcTemplate.update(sql,
                article.getAuthor(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage());
        return Optional.of(article);
    }

    @Override
    public Optional<List<Article>> getAll() {
        String sql = "SELECT * FROM articles";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<Article> articleList = new ArrayList<>();
        for (Map<String, Object> article : mapList) {
            articleList.add(getArticleFromMap(article));
        }
        return Optional.of(articleList);
    }

    @Override
    public Optional<Article> getOne(Long id) {
        String sql = "SELECT * FROM articles WHERE articles.id=?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, id);
        List<Article> articleList = new ArrayList<>();
        for (Map<String, Object> article : mapList) {
            articleList.add(getArticleFromMap(article));
        }
        return Optional.of(articleList.get(0));
    }

    @Override
    public Optional<Article> update(Long id, Article article) {
        String sql = "UPDATE articles SET " +
                "articles.author=?," +
                "articles.title=?," +
                "articles.description=?," +
                "articles.url=?," +
                "articles.url_to_image=?" +
                "Where articles.id=? ";
        jdbcTemplate.update(sql,
                article.getAuthor(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                id);
        return Optional.of(article);
    }

    private Article getArticleFromMap(Map<String, Object> article) {
        return new Article(
                Long.parseLong(String.valueOf(article.get("id"))),
                (String) article.get("author"),
                (String) article.get("title"),
                (String) article.get("description"),
                (String) article.get("url"),
                (String) article.get("url_to_image")
        );
    }
}
