package swjungle.springboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swjungle.springboard.dto.post.DeleteResponseDto;
import swjungle.springboard.dto.post.PostResponseDto;
import swjungle.springboard.dto.post.PostRequestDto;
import swjungle.springboard.model.Post;
import swjungle.springboard.model.Role;
import swjungle.springboard.model.User;
import swjungle.springboard.service.PostService;
import swjungle.springboard.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto postRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);

        Post post = new Post(user ,postRequestDto.getTitle(), postRequestDto.getContent());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(id, postRequestDto, userName, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deletePost(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id, userName, role));
    }

    private Role getRoleFromAuthentication(Authentication authentication) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().startsWith("ROLE_")) {
                return Role.valueOf(grantedAuthority.getAuthority().substring(5));
            }
        }
        throw new IllegalStateException("User has no role");
    }
}