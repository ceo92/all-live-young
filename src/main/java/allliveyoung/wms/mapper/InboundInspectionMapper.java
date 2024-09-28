package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.InboundInspection;
import allliveyoung.allliveinbound.domain.InboundRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InboundInspectionMapper {
    List<InboundInspection> findAll();

    Optional<InboundInspection> findById(Long id);
}
