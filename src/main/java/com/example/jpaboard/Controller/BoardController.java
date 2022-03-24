package com.example.jpaboard.Controller;

import com.example.jpaboard.entity.JpaBoard;
import com.example.jpaboard.entity.JpaFile;
import com.example.jpaboard.model.BoardCreateRequest;
import com.example.jpaboard.model.FileCreateRequest;
import com.example.jpaboard.service.BoardService;
import com.example.jpaboard.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController // <-- @ResponseBody 포함; 기본적으로 반환값을 JSON으로 리턴
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }


//    @GetMapping("")
//    public ModelAndView viewPage(){
//
//        return new ModelAndView("view");
//    }

    // 게시판 목록
//    @GetMapping("board")
//    public ModelAndView getBoardList(){
//
//        List<Jpaboard> jpaboard = boardService.getBoard();
//        ModelAndView model = new ModelAndView("list");
//        model.addObject("boardlist", jpaboard);
//
//        return model;
//    }

    // 저장된 파일 이름 포함
    @GetMapping("list")
    public ModelAndView getBoardList(){

        List<JpaBoard> jpaboard = boardService.getBoard();
        log.info("jpaboard :: {}", jpaboard.toString());
        ModelAndView model = new ModelAndView("list");
        model.addObject("boardList", jpaboard);
        return model;
    }

    // 처음부터 글쓰기를 누를 때
    @GetMapping("write")
    public ModelAndView insertBoard(){
        return new ModelAndView("write");
    }

    // 작성글에 필요한 내용 작성 후 확인 클릭
    @PostMapping("insert")
    public ModelAndView setBoard(@RequestPart("file") MultipartFile file, @RequestPart String nickname, @RequestPart String subject, @RequestPart String content){
        // String ModelAndView

        log.info("nickname :: {}", nickname);
        log.info("subject :: {}", subject);
        log.info("content :: {}", content);
        log.info("file :: {}", file);
//        return boardCreateRequest.toString();

        try {

            String originFilename = file.getOriginalFilename();
            String filename = new MD5Generator(Objects.requireNonNull(originFilename)).toString();
            String savePath = System.getProperty("user.dir") + "\\연습용 다운로드 파일";
            if (!new File(savePath).exists()){
                try{
                    if(!new File(savePath).mkdir()){
                        throw new IOException("폴더 생성 실패.");
                    }
                }catch (Exception e){
                    e.getStackTrace();
                }
            }

            String filePath = savePath + "\\" + filename;
            file.transferTo(new File(filePath));

            JpaFile jpaFile = fileService.setFile(filename, originFilename, filePath);
            Long fileId = jpaFile.getId();
            log.info("file id :: {}", fileId);


            Long boardNum = boardService.setBoard(nickname, subject, content, jpaFile);

            ModelAndView model = new ModelAndView("redirect:/read/{num}");
            model.addObject("num", boardNum);


            return model;

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 상세화면
    @GetMapping(value = "read/{num}")
    public ModelAndView getBoardListById(@PathVariable Long num){

        log.info("num :: {}", num);

        BoardCreateRequest boardCreateRequest = boardService.setReadCount(num);

        Long fileId = boardCreateRequest.getJpaFile().getId();
        log.info("fileId :: {}",fileId);
        JpaFile file = fileService.getFileId(fileId);

        ModelAndView model = new ModelAndView("view");
        model.addObject("boardListById", boardCreateRequest);
        model.addObject("fileId", file.getId());
        model.addObject("originFileName", file.getOriginFileName());
        model.addObject("num", num);

        return model;
    }

    // 기존 게시글 중 하나의 글제목을 클릭 시 뜨는 화면
    @PostMapping("update/{num}")
    public ModelAndView getBoardWriteById(@PathVariable Long num, @RequestPart String fileId){

        log.info("num :: {}", num);
        log.info("fileId :: {}", fileId);

        //Long numParse = Long.parseLong(numString);
        Long fileIdParse = Long.parseLong(fileId);

        BoardCreateRequest boardCreateRequest = boardService.getBoardById(num);

        ModelAndView model = new ModelAndView("update");

        model.addObject("boardCreateRequest", boardCreateRequest);
        model.addObject("readCount", boardCreateRequest.getReadCount());
        model.addObject("num", num);
        model.addObject("fileId", fileIdParse);

        return model;
    }

    // 상세화면 -> 수정 클릭
    @PutMapping(value = "updater")
    // ModelAndView String
    public ModelAndView updateBoard(@RequestPart MultipartFile fileName, @RequestPart String num, @RequestPart String fileId, @RequestPart String readCount, @RequestPart String nickname, @RequestPart String subject, @RequestPart String content){
//        String jsonString = null;
//
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("num", num);
//        jsonMap.put("id", id);
//        jsonMap.put("readCount", readCount);
//        jsonMap.put("nickname", nickname);
//        jsonMap.put("subject", subject);
//        jsonMap.put("content", content);
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            jsonString = mapper.writeValueAsString(jsonMap);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        log.info("num, id, readCount, nickname, subject, content :: {}, {}, {}, {}, {}, {}", num, id, readCount, nickname, subject, content);
//    return jsonString;

        try {
            String originFilename = fileName.getOriginalFilename();
            String filename = new MD5Generator(Objects.requireNonNull(originFilename)).toString();
            String savePath = System.getProperty("user.dir") + "\\연습용 다운로드 파일";
            if (!new File(savePath).exists()){
                try{
                    if(!new File(savePath).mkdir()){
                        throw new IOException("폴더 생성 실패.");
                    }
                }catch (Exception e){
                    e.getStackTrace();
                }
            }

            String filePath = savePath + "\\" + filename;
            fileName.transferTo(new File(filePath));

            Long idParse = Long.parseLong(fileId);
            JpaFile jpaFile = fileService.updateFile(idParse, filename, originFilename, filePath);
            Long id = jpaFile.getId();
            log.info("file id :: {}", id);

            Long numParse = Long.parseLong(num);
            Long readCountParse = Long.parseLong(readCount);
            Long boardNum = boardService.updateBoard(numParse, readCountParse, nickname, subject, content, jpaFile);

            ModelAndView model = new ModelAndView("redirect:/read/{num}");
            model.addObject("num", boardNum);

            return model;

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 상세화면 -> 삭제 클릭
    @DeleteMapping(value = "delete/{num}")
    // ModelAndView String
    public ModelAndView deleteBoard(@PathVariable String num){

        Long numParse = Long.parseLong(num);
        boardService.deleteBoardById(numParse);

        return new ModelAndView("redirect:/list");
    }
}
