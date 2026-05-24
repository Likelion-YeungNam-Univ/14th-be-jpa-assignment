package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Comment;
import com.likelion.likelion_7th.domain.Post;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.Req.CommentReqDto;
import com.likelion.likelion_7th.dto.Res.CommentResDto;
import com.likelion.likelion_7th.repository.CommentRepository;
import com.likelion.likelion_7th.repository.PostRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링이 이 클래스가 서비스 레이어로 인식하도록 설정
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 조회 전용 트랜젝션 사용
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    @Transactional
    public CommentResDto createComment(Long postId, CommentReqDto commentReqDto) {
        Post post = postRepository.findById(postId) // 해당하는 게시글 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
        User user = userRepository.findById(commentReqDto.getUserId()) // 요청받은 유저 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Comment comment = Comment.builder() // 댓글 객체 생성
                .comment_body(commentReqDto.getCommentBody()) // 요청받은 댓글 내용 저장
                .build();

        comment.confirmUser(user); // 댓글과 사용자 연결
        comment.confirmPost(post); // 댓글이 어느 게시글에 달렸는지 연결

        Comment savedComment = commentRepository.save(comment); // 댓글 DB에 저장
        return CommentResDto.from(savedComment); //댓글을 응답 형태로 변환 후 리턴
    }

    //댓글 수정
    @Transactional
    public CommentResDto updateComment(Long commentId, CommentReqDto commentReqDto) {
        Comment comment = commentRepository.findById(commentId) // 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        comment.update(commentReqDto.getCommentBody()); // 수정 적용
        return CommentResDto.from(comment); // 수정된 사항 리턴
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) { // 찾기 
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));
        commentRepository.delete(comment); // 삭제
    }
}
