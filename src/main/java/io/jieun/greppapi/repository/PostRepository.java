package io.jieun.greppapi.repository;

import io.jieun.greppapi.dto.UpdateRequest;
import io.jieun.greppapi.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {

    //Spring Bean
    //어떠한 값을 기억하는 속성

    //이런거 안하는게 좋은데 예제보려고 보는거임
    private Map<Long, Post> posts = new HashMap<>();

    @Getter
    private Long sequence = 0L;

    public Post save(Post post) {
        sequence++;

        post.setId(sequence);
        posts.put(post.getId(), post);

        return post;
    }

    public Post findById(Long id) {
        return posts.get(id);
    }

    public void remove(Long id){
        posts.remove(id);
    }

    public Post update(Long id, UpdateRequest request) {
        Post findPost = posts.get(id);
        findPost.update(request);
        posts.replace(findPost.getId(), findPost);

        return findPost;
    }



}
