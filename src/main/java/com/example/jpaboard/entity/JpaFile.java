package com.example.jpaboard.entity;

import com.example.jpaboard.impl.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class JpaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    private JpaFile(Long id, String fileName, String originFileName, String filePath){
    // Builder builder

        this.id = id;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.filePath = filePath;

    }

//    public static class Builder implements CommonModelBuilder<JpaFile>{
//
//        private final String fileName;
//        private final String originFileName;
//        private final String filePath;
//
//        public Builder(String fileName, String originFileName, String filePath){
//
//            this.fileName = fileName;
//            this.originFileName = originFileName;
//            this.filePath = filePath;
//
//        }
//
//        @Override
//        public JpaFile build(){
//            return new JpaFile(this);
//        }
//
//    }



}
