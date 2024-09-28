package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.web.dto.InboundRequestDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestSaveDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InboundRequestMapper {
    List<InboundRequest> findAll();

    Optional<InboundRequest> findById(Long id);

    Long save(InboundRequestSaveDTO inboundRequestSaveDTO);

    void update(InboundRequestUpdateDTO inboundRequestUpdateDTO);

    void delete(Long id);

    void updateStatus(Long id, String status);
}
