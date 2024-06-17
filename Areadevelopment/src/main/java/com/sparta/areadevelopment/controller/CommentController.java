package com.sparta.areadevelopment.controller;


import org.springframework.security.access.AccessDeniedException;
import com.sparta.areadevelopment.dto.CommentRequestDto;
import com.sparta.areadevelopment.dto.CommentResponseDto;
import com.sparta.areadevelopment.service.CommentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 등록
    @PostMapping("/{boardId}/comments")
    public CommentResponseDto addComment(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long boardId, @RequestBody CommentRequestDto requestDto) {
        if (userDetails == null) {
            throw new AccessDeniedException("사용자 인가에 실패했습니다.");
        }
        return commentService.addComment(userDetails.getUsername(), boardId, requestDto);
    }


    @GetMapping("/{boardId}/comments")
    public ResponseEntity<?> getAllComments(@PathVariable Long boardId) {
        List<CommentResponseDto> comments = commentService.getAllComments(boardId);
        if (comments.isEmpty()) {
            return ResponseEntity.ok("먼저 작성하여 댓글을 남겨보세요!");
        } else {
            return ResponseEntity.ok(comments);
        }
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        if (userDetails == null) {
            throw new AccessDeniedException("사용자 인가에 실패했습니다.");
        }
        return commentService.updateComment(userDetails.getUsername(), commentId, requestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId) {
        if (userDetails == null) {
            throw new AccessDeniedException("사용자 인가에 실패했습니다.");
        }
        return ResponseEntity.ok(
                commentService.deleteComment(userDetails.getUsername(), commentId));
    }
}
