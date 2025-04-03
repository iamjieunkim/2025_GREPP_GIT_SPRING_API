package io.jieun.greppapi.controller;

import io.jieun.greppapi.dto.SaveRequest;
import io.jieun.greppapi.dto.UpdateRequest;
import io.jieun.greppapi.entity.Post;
import io.jieun.greppapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
/*@Controller
@ResponseBody 이 두개 의 어노테이션을 합친게 레스트 컨트롤러다*/
@RestController //그래서 뷰가 아닌 api를 사용하고자 할때는 반드시 RestController를 사용해야됨
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    //이제 컨트롤러에서 뷰를 넘거주는게 아니라, 데이터를 넘겨줄 수 있도록 구현을 하겠다.

    //api로 만들겠다.
    @GetMapping("/{postId}") //이제 부터 뷰말고 값을 받아 넣겠다.
    //@ResponseStatus(HttpStatus.OK) //ResponseEntity사용하면 더이상 왼쪽이 필요없음
    public ResponseEntity<Post> findById(@PathVariable Long postId ){
        log.info("PostController.findById()");
        Post findPost = postRepository.findById(postId);
        //return findPost;
        //return ResponseEntity.status(HttpStatus.OK).body(findPost); //응답본문에 findPost게 들어간것
        //200번에 응답본문인 findPost이 들거간것
        return ResponseEntity.ok(findPost);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<Long> save(@RequestBody SaveRequest request) {
        Post post = Post.of(request);
        Post savedPost = postRepository.save(post);
        //return savedPost.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost.getId());
        //return ResponseEntity.created(postRepository.save(Post.of(request)).getId());
    }

    //PATCH
    @PatchMapping("/{postId}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody UpdateRequest request) { //@RequestBody로 얘가 본문이야 요청해야되 라고 알려줘야됨
        Post updatedPost = postRepository.update(postId, request);
        //return updatedPost;
        //return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
        return ResponseEntity.ok(updatedPost);
    }

    //DELETE
    @DeleteMapping("/{postId}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remove(@PathVariable Long postId) {
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //build는 바디없이 그냥 넘어감
        return ResponseEntity.noContent().build();
    }

}
