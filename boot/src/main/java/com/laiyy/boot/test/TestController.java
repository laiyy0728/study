package com.laiyy.boot.test;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Callable;

/**
 * @author laiyy
 * @date 2018/12/7 16:46
 * @description
 */
@RestController
public class TestController {


    @GetMapping
    public ResponseEntity<Resource> get() throws MalformedURLException, UnsupportedEncodingException {

        Resource resource = new UrlResource(new URL("https://t1file-beta.dahe.cn/4500000001/1/common.js"));
        new FileSystemResource(new File("d:/test.js"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("zhangsan.js", "UTF-8"));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).headers(headers).body(resource);

//        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/").build().toUri();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("success");
//        return ResponseEntity.created(uri).body("success");
//        return ResponseEntity.ok("success");
    }

}
