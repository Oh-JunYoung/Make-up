package ICTPrj.server.controller;


import ICTPrj.server.dto.PostDto;
import ICTPrj.server.dto.PostPathDto;
import ICTPrj.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public Long writePost(@RequestHeader(value = "Authorization") String userToken,
                          @RequestBody PostPathDto postPathDto) {
        return postService.writePost(userToken, postPathDto);
    }

    @PutMapping("/post/{id}")
    public Long modifyPost(@RequestHeader(value = "Authorization") String userToken,
                           @RequestBody PostPathDto postPathDto,
                           @PathVariable(value = "id") Long postId) {
        return postService.modifyPost(userToken, postId, postPathDto);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@RequestHeader(value = "Authorization") String userToken,
                           @PathVariable(value = "id") Long postId) {
        postService.deletePost(userToken, postId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "게시글 삭제 성공");
    }

}
