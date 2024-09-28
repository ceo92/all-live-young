package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.InboundRequest;

import java.util.List;
import java.util.Optional;

public interface InboundRequestMapper {
    List<InboundRequest> findAll();

    Optional<InboundRequest> findById(Long id);

    void update(InboundRequest inboundRequest);

    void delete(Long id);

    void updateStatus(Long id, String status);
}
