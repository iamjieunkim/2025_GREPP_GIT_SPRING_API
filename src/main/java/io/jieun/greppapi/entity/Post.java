package io.jieun.greppapi.entity;

import io.jieun.greppapi.dto.SaveRequest;
import io.jieun.greppapi.dto.UpdateRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data //getter,setter다 들어감
public class Post {

    private Long id;

    private String title;
    private String contents;
    private String author;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


    public static Post of(SaveRequest request) {
        Post post = new Post();
        //클래스 내부에 있는 메소드기 때문에 접근이 가능하다.
        post.title = request.getTitle();
        post.contents = request.getContents();
        post.author = request.getAuthor();

        return post;
    }

    //setter메소드의 역할을 함
    public void update(UpdateRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
        this.updatedAt = LocalDateTime.now();
    }



}
