package swjungle.springboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swjungle.springboard.dto.DeleteRequestDto;
import swjungle.springboard.dto.DeleteResponseDto;
import swjungle.springboard.dto.PostResponseDto;
import swjungle.springboard.dto.PostRequestDto;
import swjungle.springboard.model.Post;
import swjungle.springboard.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getAuthor(), postRequestDto.getPassword());
        PostResponseDto createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable("id") Long id, @RequestBody @Valid PostRequestDto postRequestDto) {
        Post updatedPost = new Post(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getAuthor(), postRequestDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(id, updatedPost, postRequestDto.getPassword()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deletePost(@PathVariable("id") Long id, @RequestBody DeleteRequestDto deleteRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id, deleteRequestDto.password()));
    }
}