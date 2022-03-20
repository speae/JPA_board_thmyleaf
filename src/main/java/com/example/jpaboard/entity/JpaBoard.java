package com.example.jpaboard.entity;

import com.example.jpaboard.impl.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class JpaBoard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false)
    private Long readCount;

    @Column(nullable = false, length = 50)
    private LocalDate regDate;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String subject;

    @Column(nullable = false, length = 200)
    private String content;

//    @Column(length = 200)
//    private String fileName;
//
//    @Column(length = 200)
//    private String originFileName;
//
//    @Column(length = 200)
//    private String filePath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private JpaFile jpaFile;

    @Builder
    private JpaBoard(Long num, Long readCount, LocalDate regDate, String nickname, String subject, String content, JpaFile jpaFile){// Builder builder
    // Builder builder

        this.num = num;
        this.readCount = readCount;
        this.regDate = regDate;
        this.nickname = nickname;
        this.subject = subject;
        this.content = content;
        this.jpaFile = jpaFile;

//        this.num = builder.num;
//        this.readCount = builder.readCount;
//        this.regDate = builder.regDate;
//        this.nickname = builder.nickname;
//        this.subject = builder.subject;
//        this.content = builder.content;

//        this.jpaFile = builder.jpaFile;

    }

//    public static class Builder implements CommonModelBuilder<JpaBoard> {
//
//        private final Long num;
//        private final Long readCount;
//        private final LocalDate regDate;
//        private final String nickname;
//        private final String subject;
//        private final String content;
//
//        private final JpaFile jpaFile;
//
//        // JpaFile jpaFile
//        public Builder(Long num, Long readCount, LocalDate regDate, String nickname, String subject, String content, JpaFile jpaFile){
//
//            this.num = num;
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
//        public JpaBoard build() {
//            return new JpaBoard(this);
//        }
//
//    }
}
