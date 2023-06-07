package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.PostCategories;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoriesRepository extends JpaRepository<PostCategories, Long> {
}
