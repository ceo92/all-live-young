package allliveyoung.wms.service;

import allliveyoung.wms.domain.Announcement;
import allliveyoung.wms.exception.AnnouncementNotFoundException;
import allliveyoung.wms.mapper.AnnouncementMapper;
import allliveyoung.wms.web.dto.AnnouncementDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementMapper announcementMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper announcementMapper, ModelMapper modelMapper) {
        this.announcementMapper = announcementMapper;
        this.modelMapper = modelMapper;
    }

    // 공지사항 작성
    @Override
    @Transactional
    public void createAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = modelMapper.map(announcementDTO, Announcement.class);
        announcement.setWriteDate(LocalDateTime.now());
        announcementMapper.insertAnnouncement(announcement);
    }

    // 공지사항 수정
    @Override
    @Transactional
    public void updateAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement existingAnnouncement = announcementMapper.selectAnnouncementById(announcementDTO.getAnnouncementId());
        if (existingAnnouncement != null && existingAnnouncement.getManagerId().equals(announcementDTO.getManagerId())) {
            existingAnnouncement.setContent(announcementDTO.getContent());
            existingAnnouncement.setHighlight(announcementDTO.getHighlight());
            announcementMapper.updateAnnouncement(existingAnnouncement);
        } else {
            throw new AnnouncementNotFoundException("공지사항을 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 공지사항 삭제
    @Override
    @Transactional
    public void deleteAnnouncement(Long announcementId, Long managerId) {
        Announcement existingAnnouncement = announcementMapper.selectAnnouncementById(announcementId);
        if (existingAnnouncement != null && existingAnnouncement.getManagerId().equals(managerId)) {
            announcementMapper.deleteAnnouncement(announcementId);
        } else {
            throw new AnnouncementNotFoundException("공지사항을 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 공지사항 목록 조회
    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.selectAllAnnouncements();
    }

    // 공지사항 상세 조회
    @Override
    public Announcement getAnnouncementDetails(Long announcementId) {
        Announcement announcement = announcementMapper.selectAnnouncementById(announcementId);
        if (announcement == null) {
            throw new AnnouncementNotFoundException("공지사항을 찾을 수 없습니다.");
        }
        return announcement;
    }



}
