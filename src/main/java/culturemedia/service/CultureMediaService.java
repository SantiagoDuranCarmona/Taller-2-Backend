package culturemedia.service;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;

public interface CultureMediaService
{
    List<Video> findAll() throws VideoNotFoundException;
    Video add(Video video);
    View add(View view);
}