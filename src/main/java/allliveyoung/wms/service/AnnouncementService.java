package allliveyoung.wms.service;

import allliveyoung.wms.domain.Announcement;
import allliveyoung.wms.web.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementService {

    // 공지사항 작성
    void createAnnouncement(AnnouncementDTO announcementDTO);

    // 공지사항 수정
    void updateAnnouncement(AnnouncementDTO announcementDTO);

    // 공지사항 삭제
    void deleteAnnouncement(Long announcementId, Long managerId);

    // 공지사항 목록 조회
    List<Announcement> getAllAnnouncements();

    // 공지사항 상세 조회
    Announcement getAnnouncementDetails(Long announcementId);

    // 기타 필요한 서비스 메서드...
}
