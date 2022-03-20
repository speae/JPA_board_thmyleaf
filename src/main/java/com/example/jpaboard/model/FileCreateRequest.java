package com.example.jpaboard.model;

import com.example.jpaboard.impl.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileCreateRequest {

    private Long id;
    private String fileName;
    private String originFileName;
    private String filePath;

    @Builder
    private FileCreateRequest(Long id, String fileName, String originFileName, String filePath){
    // Builder builder

        this.id = id;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.filePath = filePath;

    }

//    public static class Builder implements CommonModelBuilder<FileCreateRequest>{
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
//        public FileCreateRequest build(){
//            return new FileCreateRequest(this);
//        }
//
//    }

}
