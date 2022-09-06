package peaksoft.dao;

import peaksoft.entity.Video;

import java.util.List;

public interface VideoDao {

    void saveVideo(Long lessonId, Video video);

    void updateVideo(Long id,String videoName,String link);

    Video getVideoById(Long id);

    List<Video> getAllVideos(Long id);

    void deleteVideoById(Long id);
}
