package com.project.carrental.rest.controller;

import com.project.carrental.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/images/")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping(
        value = "{imageName}",
        produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<byte[]> getImageWithMediaType(
        @PathVariable(name = "imageName") String fileName,
        @RequestParam(required = true, name = "directory") String directory
    ) throws IOException {
        return ResponseEntity.ok(imageService.getImageWithMediaType(fileName, directory));
    }
}
