package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryMapper {

    void insertInquiry(Inquiry inquiry);

    void updateInquiry(Inquiry inquiry);

    void deleteInquiry(@Param("inquiryId") Long inquiryId);

    Inquiry selectInquiryById(@Param("inquiryId") Long inquiryId);

    List<Inquiry> selectInquiriesByMemberId(@Param("memberId") Long memberId);

    List<Inquiry> selectUnansweredInquiries();

    List<Inquiry> selectAllInquiries();
}
