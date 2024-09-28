package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundInspection;
import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.web.dto.InboundInspectionDTO;

import java.util.List;

public interface InboundInspectionService {
    List<InboundInspectionDTO> findInboundInspections();

    InboundInspectionDTO findInboundInspection(Long id);
}
