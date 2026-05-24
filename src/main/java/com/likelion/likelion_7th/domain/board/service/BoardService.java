package com.likelion.likelion_7th.domain.board.service;

import com.likelion.likelion_7th.domain.board.dto.request.BoardCreateRequest;
import com.likelion.likelion_7th.domain.board.dto.request.BoardUpdateRequest;
import com.likelion.likelion_7th.domain.board.dto.response.BoardDetailResponse;
import com.likelion.likelion_7th.domain.board.dto.response.BoardResponse;
import com.likelion.likelion_7th.domain.board.entity.Board;
import com.likelion.likelion_7th.domain.board.repository.BoardRepository;
import com.likelion.likelion_7th.domain.users.entity.User;
import com.likelion.likelion_7th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponse createBoard(BoardCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
        return BoardResponse.from(boardRepository.save(board));
    }

    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    public BoardDetailResponse getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        return BoardDetailResponse.from(board);
    }

    @Transactional
    public BoardResponse updateBoard(Long boardId, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        board.update(request.getTitle(), request.getContent());
        return BoardResponse.from(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        boardRepository.delete(board);
    }
}
