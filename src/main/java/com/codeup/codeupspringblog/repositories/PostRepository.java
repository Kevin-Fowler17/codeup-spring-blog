package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    @Query("from Post p where p.title like %:term% or p.body like %:term%")
    List<Post> findLikeTitleOrBody(@Param("term") String title);

    @Query("from Post p where p.body like %:term%")
    List<Post> findLikeBody(@Param("term") String title);
}
