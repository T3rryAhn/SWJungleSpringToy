package swjungle.springboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swjungle.springboard.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
