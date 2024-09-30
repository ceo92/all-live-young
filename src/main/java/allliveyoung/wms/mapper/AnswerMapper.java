package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerMapper {

    void insertAnswer(Answer answer);

    void updateAnswer(Answer answer);

    void deleteAnswer(@Param("answerId") Long answerId);

    Answer selectAnswerById(@Param("answerId") Long answerId);

    Answer selectAnswerByInquiryId(@Param("inquiryId") Long inquiryId);
}
