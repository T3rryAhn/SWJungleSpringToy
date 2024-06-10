package swjungle.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swjungle.springboard.dto.DeleteResponseDto;
import swjungle.springboard.dto.PostResponseDto;
import swjungle.springboard.model.Post;
import swjungle.springboard.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponseDto createPost(Post post) {
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getUserId(),
                savedPost.getCreatedAt(),
                savedPost.getModifiedAt()
        );
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getUserId(),
                        post.getCreatedAt(),
                        post.getModifiedAt()
                )).collect(Collectors.toList());
    }

    public Optional<PostResponseDto> getPostById(Long id) {
        return postRepository.findById(id)
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getUserId(),
                        post.getCreatedAt(),
                        post.getModifiedAt()
                ));
    }

    public PostResponseDto updatePost(Long id, Post updatedPost, String password) {
        return postRepository.findById(id).map(post -> {
            if (post.getPassword().equals(password)) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                Post savedPost = postRepository.save(post);
                return new PostResponseDto(
                        savedPost.getId(),
                        savedPost.getTitle(),
                        savedPost.getContent(),
                        savedPost.getUserId(),
                        savedPost.getCreatedAt(),
                        savedPost.getModifiedAt()
                );
            } else {
                throw new RuntimeException("Invalid password");
            }
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public DeleteResponseDto deletePost(Long id, String password) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (post.getPassword().equals(password)) {
            postRepository.deleteById(id);
            return new DeleteResponseDto("Post deleted successfully");
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}
