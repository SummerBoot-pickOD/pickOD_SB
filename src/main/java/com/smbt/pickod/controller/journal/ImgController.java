package com.smbt.pickod.controller.journal;

import com.smbt.pickod.dto.journal.JnlImgsDTO;
import com.smbt.pickod.service.journal.ImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImgController {
    private final ImgService imgService;

    @Value("${img.upload-journal-dir}")
    private String jnlImgDir;

    //특정 jnlNum를 기반으로 파일리스트를 반환
    @GetMapping("/v1/journals/{jnlNum}/files")
    public List<JnlImgsDTO> fileList(@PathVariable("jnlNum") Long jnlNum) {
        //URL에서 boardId 추출하기 위해 @PathdVariable 어노테이션 사용
        return imgService.findList(jnlNum);
    }

    //파일을 표시하는 메소드(특정 이름을 기반으로 파일을 바이트 배열로 반환하는 메소드)
    @GetMapping("/v1/files")
    public byte[] display(@RequestParam("fileName") String fileName) throws IOException {
        //요청 파라미터에서 fileName을 추출(@RequestParam 어노테이션 사용)
        File file = new File(jnlImgDir + fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
        //HttpServletResponse와 동일하게 ResponseEntityu 객체는 응답을 나타내는 객체이다
        //스프링에서 지원하는 응답객체이며 기존의 응답 객체보다 간편하게 설정할 수 있다는 장점이 있다

        //Resource객체는 말 그대로 자원을 나타내는 객체로 스프링에서 지원하는 타입이다
        //이미지 파일이라는 리소스를 다운로드 처리하기 위해 사용하며 File 객체보다 많은 종류의 리소스를 다룰 수 있고
        //스프링과의 호환성이 좋다
        //Resource는 인터페이스이므로 객체화 할 때는 자식 클래스를 사용한다
        Resource resource = new FileSystemResource(jnlImgDir + fileName);
        HttpHeaders headers = new HttpHeaders();

        String name = resource.getFilename();

        name = name.substring(name.indexOf("_") + 1);

        //Content-Disposition 헤더로 설정하여 클라이언트 브라우저가 첨부파일이라는 것을 알게 함
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


}


