package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    void insertAnnouncement(Announcement announcement);

    void updateAnnouncement(Announcement announcement);

    void deleteAnnouncement(@Param("announcementId") Long announcementId);

    Announcement selectAnnouncementById(@Param("announcementId") Long announcementId);

    List<Announcement> selectAllAnnouncements();
}
