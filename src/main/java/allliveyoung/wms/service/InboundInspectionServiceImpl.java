package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.config.ModelMapperConfig;
import allliveyoung.allliveinbound.domain.InboundInspection;
import allliveyoung.allliveinbound.mapper.InboundInspectionMapper;
import allliveyoung.allliveinbound.mapper.InboundRequestMapper;
import allliveyoung.allliveinbound.web.dto.InboundInspectionDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class InboundInspectionServiceImpl implements InboundInspectionService{

    private final ModelMapper modelMapper;
    private final InboundInspectionMapper inboundInspectionMapper;

    @Override
    public List<InboundInspectionDTO> findInboundInspections() {
        List<InboundInspection> inspections = inboundInspectionMapper.findAll();
        List<InboundInspectionDTO> inspectionDTOs = inspections.stream().map(inspection -> modelMapper.map(inspection, InboundInspectionDTO.class)).collect(Collectors.toList());
        return inspectionDTOs;
    }

    @Override
    public InboundInspectionDTO findInboundInspection(Long id) {
        Optional<InboundInspection> inspection = inboundInspectionMapper.findById(id);
        InboundInspectionDTO inspectionDTO = modelMapper.map(inspection.orElseThrow(), InboundInspectionDTO.class);
        return inspectionDTO;
    }
}
