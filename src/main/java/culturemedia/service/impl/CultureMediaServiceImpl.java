package culturemedia.service.impl;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CultureMediaService;

public class CultureMediaServiceImpl implements CultureMediaService
{
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    public CultureMediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if ( videos.isEmpty() ) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public Video add(Video video) {
        return videoRepository.add( video );
    }

    @Override
    public View add(View view) {
        return viewsRepository.add( view );
    }
}