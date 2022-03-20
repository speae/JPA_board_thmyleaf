package com.example.jpaboard.model;

import com.example.jpaboard.entity.JpaFile;
import com.example.jpaboard.impl.CommonModelBuilder;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequest {

    private Long num;
    private Long readCount;
    private LocalDate regDate;
    private String nickname;
    private String subject;
    private String content;

    private JpaFile jpaFile;

    @Builder
    private BoardCreateRequest(Long num, Long readCount, LocalDate regDate, String nickname, String subject, String content, JpaFile jpaFile){
    // Builder builder

        this.num = num;
        this.readCount = readCount;
        this.regDate = regDate;
        this.nickname = nickname;
        this.subject = subject;
        this.content = content;

        this.jpaFile = jpaFile;

//        this.readCount = builder.readCount;
//        this.regDate = builder.regDate;
//        this.nickname = builder.nickname;
//        this.subject = builder.subject;
//        this.content = builder.content;
//
//        this.jpaFile = builder.jpaFile;
    }

//    public static class Builder implements CommonModelBuilder<BoardCreateRequest>{
//
//        private final Long readCount;
//        private final LocalDate regDate;
//        private final String nickname;
//        private final String subject;
//        private final String content;
//
//        private final JpaFile jpaFile;
//
//        // JpaFile jpaFile
//        public Builder(Long readCount, LocalDate regDate, String nickname, String subject, String content, JpaFile jpaFile){
//
//            this.readCount = readCount;
//            this.regDate = regDate;
//            this.nickname = nickname;
//            this.subject = subject;
//            this.content = content;
//
//            this.jpaFile = jpaFile;
//
//        }
//
//        @Override
//        public BoardCreateRequest build(){
//            return new BoardCreateRequest(this);
//        }
//    }
}
