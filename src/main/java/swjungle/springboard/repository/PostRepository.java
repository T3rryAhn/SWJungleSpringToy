package swjungle.springboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swjungle.springboard.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 작성 날짜 기준 내림차순으로 정렬하여 모든 게시글 조회
    List<Post> findAllByOrderByCreatedAtDesc();
}
