package com.example.jpaboard.service;

import com.example.jpaboard.entity.JpaFile;
import com.example.jpaboard.model.FileCreateRequest;
import com.example.jpaboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    // jpafile 엔티티에서 사용차의 요청에 맞는 데이터를 줌
    @Transactional(rollbackFor = Exception.class)
    public JpaFile getFileId(Long id){
        Optional<JpaFile> jpaFile = fileRepository.findById(id);
        if(jpaFile.isPresent()){
            return jpaFile.get();
        }else{
            throw new NoSuchElementException("데이터가 없습니다.");
        }
    }

    // 사용자가 원하는 데이터를 요구에 맞추어 jpafile 엔티티에 저장
    // FileCreateRequest fileCreateRequest
    public JpaFile setFile(String fileName, String originFileName, String filePath){

//        FileCreateRequest fileCreateRequest = FileCreateRequest.builder()
//                .id(jpaFile.getId())
//                .fileName(jpaFile.getFileName())
//                .originFileName(jpaFile.getOriginFileName())
//                .filePath(jpaFile.getFilePath())
//                .build();

        return fileRepository.save(JpaFile.builder()
                        .fileName(fileName)
                        .originFileName(originFileName)
                        .filePath(filePath)
                        .build());

    }

    public JpaFile updateFile(Long id, String fileName, String originFileName, String filePath){

        getFileId(id);

        return fileRepository.save(JpaFile.builder()
                .id(id)
                .fileName(fileName)
                .originFileName(originFileName)
                .filePath(filePath)
                .build());
    }

}
