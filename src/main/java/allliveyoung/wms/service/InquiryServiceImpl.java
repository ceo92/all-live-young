package allliveyoung.wms.service;

import allliveyoung.wms.domain.Answer;
import allliveyoung.wms.domain.Inquiry;
import allliveyoung.wms.exception.InquiryNotFoundException;
import allliveyoung.wms.mapper.AnswerMapper;
import allliveyoung.wms.mapper.InquiryMapper;
import allliveyoung.wms.web.dto.AnswerDTO;
import allliveyoung.wms.web.dto.InquiryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryMapper inquiryMapper;
    private final AnswerMapper answerMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public InquiryServiceImpl(InquiryMapper inquiryMapper, AnswerMapper answerMapper, ModelMapper modelMapper) {
        this.inquiryMapper = inquiryMapper;
        this.answerMapper = answerMapper;
        this.modelMapper = modelMapper;
    }

    // 문의 작성
    @Override
    @Transactional
    public void createInquiry(InquiryDTO inquiryDTO) {
        Inquiry inquiry = modelMapper.map(inquiryDTO, Inquiry.class); // Inquiry 객체가 null이 아닌지 확인
        if (inquiry != null) {
            inquiry.setWriteTime(LocalDateTime.now());
            inquiryMapper.insertInquiry(inquiry);
        } else {
            throw new IllegalStateException("Inquiry 객체 생성에 실패했습니다.");
        }

    }

    // 문의 수정
    @Override
    @Transactional
    public void updateInquiry(InquiryDTO inquiryDTO) {
        Inquiry existingInquiry = inquiryMapper.selectInquiryById(inquiryDTO.getInquiryId());
        if (existingInquiry != null && existingInquiry.getMemberId().equals(inquiryDTO.getMemberId())) {
            existingInquiry.setInquiryTitle(inquiryDTO.getInquiryTitle());
            existingInquiry.setInquiryContent(inquiryDTO.getInquiryContent());
            inquiryMapper.updateInquiry(existingInquiry);
        } else {
            throw new InquiryNotFoundException("문의 정보를 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 문의 삭제
    @Override
    @Transactional
    public void deleteInquiry(Long inquiryId, Long memberId) {
        Inquiry existingInquiry = inquiryMapper.selectInquiryById(inquiryId);
        if (existingInquiry != null && existingInquiry.getMemberId().equals(memberId)) {
            inquiryMapper.deleteInquiry(inquiryId);
        } else {
            throw new InquiryNotFoundException("문의 정보를 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 문의 목록 조회
    @Override
    public List<Inquiry> getInquiriesByMemberId(Long memberId) {
        return inquiryMapper.selectInquiriesByMemberId(memberId);
    }

    // 문의 상세 조회
    @Override
    public Inquiry getInquiryDetails(Long inquiryId) {
        Inquiry inquiry = inquiryMapper.selectInquiryById(inquiryId);
        if (inquiry == null) {
            throw new InquiryNotFoundException("문의 정보를 찾을 수 없습니다.");
        }
        return inquiry;
    }

    // 답변 작성
    @Override
    @Transactional
    public void createAnswer(AnswerDTO answerDTO) {
        Answer answer = modelMapper.map(answerDTO, Answer.class);
        answer.setAnswerWriteTime(LocalDateTime.now());
        answerMapper.insertAnswer(answer);

        // 문의의 답변 상태 업데이트
        Inquiry inquiry = inquiryMapper.selectInquiryById(answerDTO.getInquiryId());
        if (inquiry != null) {
            inquiry.setAnswered(true);
            inquiryMapper.updateInquiry(inquiry);
        } else {
            throw new InquiryNotFoundException("문의 정보를 찾을 수 없습니다.");
        }
    }

    // 답변 수정
    @Override
    @Transactional
    public void updateAnswer(AnswerDTO answerDTO) {
        Answer existingAnswer = answerMapper.selectAnswerById(answerDTO.getAnswerId());
        if (existingAnswer != null && existingAnswer.getMemberId().equals(answerDTO.getMemberId())) {
            existingAnswer.setAnswerContent(answerDTO.getAnswerContent());
            existingAnswer.setAnswerWriteTime(LocalDateTime.now());
            answerMapper.updateAnswer(existingAnswer);
        } else {
            throw new InquiryNotFoundException("답변 정보를 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 답변 삭제
    @Override
    @Transactional
    public void deleteAnswer(Long answerId, Long managerId) {
        Answer existingAnswer = answerMapper.selectAnswerById(answerId);
        if (existingAnswer != null && existingAnswer.getMemberId().equals(managerId)) {
            answerMapper.deleteAnswer(answerId);


            Inquiry inquiry = inquiryMapper.selectInquiryById(existingAnswer.getInquiryId());
            if (inquiry != null) {
                inquiry.setAnswered(false);
                inquiryMapper.updateInquiry(inquiry);
            } else {
                throw new InquiryNotFoundException("문의 정보를 찾을 수 없습니다.");
            }
        } else {
            throw new InquiryNotFoundException("답변 정보를 찾을 수 없거나 권한이 없습니다.");
        }
    }

    // 답변 조회
    @Override
    public Answer getAnswerByInquiryId(Long inquiryId) {
        return answerMapper.selectAnswerByInquiryId(inquiryId);
    }

    // 미답변 문의 목록 조회
    @Override
    public List<Inquiry> getUnansweredInquiries() {
        return inquiryMapper.selectUnansweredInquiries();
    }

    // 모든 문의 조회
    @Override
    public List<Inquiry> getAllInquiries() {
        return inquiryMapper.selectAllInquiries();
    }

}
