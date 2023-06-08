package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CommunityResponse {

    private Integer id;
    private String title;
    private String writer;
    private Integer viewCount;
    private Integer likeCount;
    private String inserted;

    @Builder
    public CommunityResponse(Integer id, String title, String writer, Integer viewCount, Integer likeCount, String inserted) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.inserted = inserted;
    }
}
