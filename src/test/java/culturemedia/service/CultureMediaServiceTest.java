package culturemedia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.impl.CultureMediaServiceImpl;

class CultureMediaServiceTest
{
    private CultureMediaService cultureMediaService;
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    @BeforeEach
    void setUp() {
        videoRepository = new VideoRepositoryImpl();
        viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    private void createVideos() {
        List<Video> videos = List.of(
            new Video("01", "title 1", "----", 2.7),
			new Video("02", "title 2", "----", 3.7),
			new Video("03", "title 3", "----", 2.6),
			new Video("04", "clip 1", "----", 1.7),
			new Video("05", "clip 2", "----", 3.9),
			new Video("06", "clip 3", "----", 3.3)
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        createVideos();
        List<Video> videos = cultureMediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
    }
}