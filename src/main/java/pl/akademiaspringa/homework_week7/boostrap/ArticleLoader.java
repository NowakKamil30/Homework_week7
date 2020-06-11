package pl.akademiaspringa.homework_week7.boostrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.akademiaspringa.homework_week7.Dao.ArticleDaoImpl;
import pl.akademiaspringa.homework_week7.services.ArticlesService;

@Component
public class ArticleLoader implements CommandLineRunner {
    private final ArticlesService articlesService;
    private final ArticleDaoImpl articleDao;

    @Autowired
    public ArticleLoader(ArticlesService articlesService, ArticleDaoImpl articleDao) {
        this.articlesService = articlesService;
        this.articleDao = articleDao;
    }

    @Override
    public void run(String... args) throws Exception {
        articleDao.getAll().ifPresent(list -> {
            if(list.size() == 0) {
                articlesService.getArticles()
                        .forEach(articleDao::save);
            }
        });

    }
}
