package com.example.demo.controller;

import com.example.demo.entity.AuthUser;
import com.example.demo.response.CommunityResponse;
import com.example.demo.service.CommunityService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/community")
    public ResponseEntity<String> createBoard(@RequestBody CreateBoardRequest request, AuthUser authUser) {
        log.info("request={}", request);
        Boolean ok = communityService.createBoard(authUser.getUserId(), request);

        if (ok) {
            return new ResponseEntity<>("게시글이 등록되었습니다.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("게시글 작성에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/community")
    public List<CommunityResponse> getCommunityList(@RequestParam(defaultValue = "id") String sort) {
        List<CommunityResponse> communityList = communityService.getCommunityList(sort);
        return communityList;
    }

    @GetMapping("/community/{boardId}")
    public BoardResponse getBoard(@PathVariable Integer boardId) {
        return communityService.getBoard(boardId);
    }

    @GetMapping("/community/getWriter")
    public String getWriter(AuthUser authUser) {
        return communityService.getWriter(authUser.getUserId());
    }

    @Data
    public static class CreateBoardRequest {
        private String title;
        private String content;
    }

    @Data
    public static class BoardResponse {
        private String title;
        private String content;
        private String writer;
        private String inserted;

        @Builder
        public BoardResponse(String title, String content, String writer, String inserted) {
            this.title = title;
            this.content = content;
            this.writer = writer;
            this.inserted = inserted;
        }
    }
}
