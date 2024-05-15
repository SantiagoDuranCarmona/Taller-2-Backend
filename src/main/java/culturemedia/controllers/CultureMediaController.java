package culturemedia.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import culturemedia.exception.CultureMediaException;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.CultureMediaService;
import culturemedia.service.impl.CultureMediaServiceImpl;

@RestController
public class CultureMediaController {
    private final CultureMediaService cultureMediaService;


    public CultureMediaController(CultureMediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    public CultureMediaController() {
        this.cultureMediaService = new CultureMediaServiceImpl(new VideoRepositoryImpl(), new ViewsRepositoryImpl());
    }

    @GetMapping( value = "/videos" )
    public ResponseEntity<List<Video>> findAllVideos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cultureMediaService.findAll());
        } catch (CultureMediaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Erro-message", e.getMessage()).body(Collections.emptyList());
        }
    }

    @PostMapping( value = "/videos" )
    public Video addVideo(@RequestBody Video video){
        return cultureMediaService.add(video);
    }
}