package allliveyoung.wms.service;

import allliveyoung.wms.domain.Answer;
import allliveyoung.wms.domain.Inquiry;
import allliveyoung.wms.web.dto.AnswerDTO;
import allliveyoung.wms.web.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {

    // 문의 작성
    void createInquiry(InquiryDTO inquiryDTO);

    // 문의 수정
    void updateInquiry(InquiryDTO inquiryDTO);

    // 문의 삭제
    void deleteInquiry(Long inquiryId, Long memberId);

    // 문의 목록 조회
    List<Inquiry> getInquiriesByMemberId(Long memberId);

    // 문의 상세 조회
    Inquiry getInquiryDetails(Long inquiryId);

    // 답변 작성
    void createAnswer(AnswerDTO answerDTO);

    // 답변 수정
    void updateAnswer(AnswerDTO answerDTO);

    // 답변 삭제
    void deleteAnswer(Long answerId, Long managerId);

    // 답변 조회
    Answer getAnswerByInquiryId(Long inquiryId);

    // 미답변 문의 목록 조회
    List<Inquiry> getUnansweredInquiries();

    // 모든 문의 조회
    List<Inquiry> getAllInquiries();

}
