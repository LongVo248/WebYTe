package mtt.webyte.repository;

import mtt.webyte.model.News;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	Page<News> findByTitleContaining(String title, Pageable pageable);
}
