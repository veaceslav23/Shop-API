package com.project.carrental.service;

import com.project.carrental.persistence.model.ImageEntity;
import com.project.carrental.persistence.repository.ImageRepository;

import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ImageService {
    public final String storageDirectoryPath = "C:\\SELF\\CarRentalProject\\images-storage\\";
    private final ImageRepository imageRepository;

    public ImageEntity getImageById(String id) {
        return imageRepository.findById(UUID.fromString(id))
            .orElse(null);
    }

    public ImageEntity uploadToLocalFileSystem(MultipartFile file, String directory) {
        /* we will extract the file name (with extension) from the given file to store it in our local machine for now
        and later in virtual machine when we'll deploy the project
         */
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        /* The Path in which we will store our image . we could change it later
        based on the OS of the virtual machine in which we will deploy the project.
        In my case i'm using windows 10 .
         */
        Path storageDirectory = Paths.get(storageDirectoryPath.concat(directory));
        /*
         * we'll do just a simple verification to check if the folder in which we will store our images exists or not
         * */
        if (!Files.exists(storageDirectory)) { // if the folder does not exist
            try {
                Files.createDirectories(storageDirectory); // we create the directory in the given storage directory
                // path
            } catch (Exception e) {
                e.printStackTrace();// print the exception
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
            );// we are Copying all bytes from an input stream to a file
        } catch (IOException e) {
            e.printStackTrace();
        }
        // the response will be the download URL of the image
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("api/images/getImage/")
//            .path(fileName)
//            .toUriString();

        var imageEntity = ImageEntity.builder().code(fileName).build();
        // return the download image url as a response entity
        return imageRepository.save(imageEntity);
    }

    public byte[] getImageWithMediaType(String imageName, String directory) throws IOException {
        Path destination = Paths.get(storageDirectoryPath + directory + "\\" + imageName);// retrieve the image by its name

        return IOUtils.toByteArray(destination.toUri());
    }
}
