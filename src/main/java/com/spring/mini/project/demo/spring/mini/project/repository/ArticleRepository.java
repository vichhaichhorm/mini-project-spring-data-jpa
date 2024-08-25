package com.spring.mini.project.demo.spring.mini.project.repository;

import com.spring.mini.project.demo.spring.mini.project.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {


}
