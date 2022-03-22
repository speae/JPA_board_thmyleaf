package com.example.jpaboard.service;

import com.example.jpaboard.entity.JpaBoard;
import com.example.jpaboard.entity.JpaFile;
import com.example.jpaboard.model.BoardCreateRequest;
import com.example.jpaboard.model.FileCreateRequest;
import com.example.jpaboard.repository.BoardRepository;
import com.example.jpaboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    public List<JpaBoard> getBoard(){

        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "num"));

        //return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "ref").and(Sort.by(Sort.Direction.ASC, "restep")));
    }

    public BoardCreateRequest getBoardById(Long num){

        Optional<JpaBoard> jpaBoard = boardRepository.findById(num);
        if(jpaBoard.isPresent()){
            JpaBoard board = jpaBoard.get();
            return BoardCreateRequest.builder()
                    .num(num)
                    .readCount(board.getReadCount())
                    .regDate(board.getRegDate())
                    .nickname(board.getNickname())
                    .subject(board.getSubject())
                    .content(board.getContent())
                    .jpaFile(board.getJpaFile())
                    .build();
//            return new BoardCreateRequest.Builder(
//
//                    board.getReadCount(),
//                    board.getRegDate(),
//                    board.getNickname(),
//                    board.getSubject(),
//                    board.getContent(),
//
//                    board.getJpaFile()
//            ).build();
        }else {
            throw new NoSuchElementException("데이터가 없습니다.");
        }
    }

    public BoardCreateRequest setReadCount(Long num){

        BoardCreateRequest boardCreateRequest = getBoardById(num);

        Optional<JpaBoard> jpaBoard = boardRepository.findById(num);
        if(jpaBoard.isPresent()){

            BoardCreateRequest updateBoardCreateRequest = BoardCreateRequest.builder()
                    .num(num)
                    .readCount(boardCreateRequest.getReadCount() + 1)
                    .regDate(boardCreateRequest.getRegDate())
                    .nickname(boardCreateRequest.getNickname())
                    .subject(boardCreateRequest.getSubject())
                    .content(boardCreateRequest.getContent())
                    .jpaFile(boardCreateRequest.getJpaFile())
                    .build();

            JpaBoard board = JpaBoard.builder()
                    .num(num)
                    .readCount(updateBoardCreateRequest.getReadCount())
                    .regDate(updateBoardCreateRequest.getRegDate())
                    .nickname(updateBoardCreateRequest.getNickname())
                    .subject(updateBoardCreateRequest.getSubject())
                    .content(updateBoardCreateRequest.getContent())
                    .jpaFile(updateBoardCreateRequest.getJpaFile())
                    .build();

            boardRepository.save(board);

            return updateBoardCreateRequest;
//            return new BoardCreateRequest.Builder(
//
//                    board.getReadCount(),
//                    board.getRegDate(),
//                    board.getNickname(),
//                    board.getSubject(),
//                    board.getContent(),
//
//                    board.getJpaFile()
//            ).build();
        }else {
            throw new NoSuchElementException("데이터가 없습니다.");
        }
    }

    public Long setBoard(String nickname, String subject, String content, JpaFile jpaFile){

        JpaBoard board = JpaBoard.builder()
                .readCount(0L)
                .regDate(LocalDate.now())
                .nickname(nickname)
                .subject(subject)
                .content(content)
                .jpaFile(jpaFile)
                .build();
        JpaBoard jpaBoard = boardRepository.save(board);

        return jpaBoard.getNum();

    }

    @Transactional
    public Long updateBoard(Long num, Long readCount, String nickname, String subject, String content, JpaFile jpaFile){

        getBoardById(num);

        JpaBoard board = JpaBoard.builder()
                .num(num)
                .readCount(readCount)
                .regDate(LocalDate.now())
                .nickname(nickname)
                .subject(subject)
                .content(content)
                .jpaFile(jpaFile)
                .build();

        JpaBoard jpaBoard = boardRepository.save(board);

        return jpaBoard.getNum();
    }

    @Transactional
    public void deleteBoardById(Long num){

        boardRepository.deleteById(num);
    }

}
