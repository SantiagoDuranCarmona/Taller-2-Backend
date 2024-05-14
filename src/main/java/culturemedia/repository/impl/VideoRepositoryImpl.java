package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

public class VideoRepositoryImpl implements VideoRepository
{
    private final List<Video> videos;

	public VideoRepositoryImpl() {
		videos = new ArrayList<>();
	}

	@Override
	public List<Video> findAll() {
		return videos;
	}

	@Override
	public Video add(Video video) {
		this.videos.add( video );
		return video;
	}

	@Override
	public List<Video> find(String title) {
		return videos.stream().filter(video -> video.title().contains( title )).collect( Collectors.toList() );
	}

	@Override
	public List<Video> find(Double fromDuration, Double toDuration) {
		return videos.stream().filter(video -> video.duration() >= fromDuration && video.duration() <= toDuration)
							  .collect( Collectors.toList() );
	}
}