package culturemedia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.impl.CultureMediaServiceImpl;

public class CultureMediaServiceTest
{
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;
    private CultureMediaService cultureMediaService;
    private List<Video> videos;

    @BeforeEach
    void setUp() {
        videoRepository = Mockito.mock(VideoRepository.class);
        viewsRepository = Mockito.mock(ViewsRepository.class);
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    
        videos = List.of(
            new Video("01", "title 1", "----", 2.7),
            new Video("02", "title 2", "----", 3.7),
            new Video("03", "title 3", "----", 2.6),
            new Video("04", "clip 1", "----", 1.7),
            new Video("05", "clip 2", "----", 3.9),
            new Video("06", "clip 3", "----", 3.3)
        );
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        when( videoRepository.findAll() ).thenReturn( videos );
        List<Video> result = cultureMediaService.findAll();
        assertEquals(videos, result);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
    }

    @Test
    void when_FindByTitle_existing_title_should_return_matching_videos() throws VideoNotFoundException {
        String title = "title 1";
        when( videoRepository.find( title ) ).thenReturn( List.of(videos.get(0)) );
        List<Video> result = cultureMediaService.find( title );
        assertEquals(1, result.size());
        assertEquals(videos.get(0), result.get(0));
    }

    @Test
    void when_FindByTitle_non_existing_title_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find("non-existent title"));
    }

    @Test
    void when_FindByDuration_existing_range_should_return_matching_videos() throws VideoNotFoundException {
        double minDuration = 2.5;
        double maxDuration = 3.5;
        when( videoRepository.find(minDuration, maxDuration) ).thenReturn(
            List.of(videos.get(0), videos.get(2), videos.get(5)));
        List<Video> result = cultureMediaService.find(minDuration, maxDuration);
        assertEquals(3, result.size());
        assertEquals(videos.get(0), result.get(0));
        assertEquals(videos.get(2), result.get(1));
        assertEquals(videos.get(5), result.get(2));
    }

    @Test
    void when_FindByDuration_non_existing_range_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find(5.0, 10.0));
    }
}