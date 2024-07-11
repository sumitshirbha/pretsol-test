package com.pretsol.demo.repository;

import com.pretsol.demo.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>, JpaSpecificationExecutor<CommentEntity> {

    Optional<CommentEntity> findById(Long id);

    Optional<CommentEntity> findByBy(String userName);

    @Query("select c from CommentEntity c where c.dateOfComment >= ?1 and c.dateOfComment < ?2")
    List<CommentEntity> findAllWithCreationDateTimeBefore(ZonedDateTime fromDate, ZonedDateTime toDate);
}
