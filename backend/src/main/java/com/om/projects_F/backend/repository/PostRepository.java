package com.om.projects_F.backend.repository;

import com.om.projects_F.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
