package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.config.ModelMapperConfig;
import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.mapper.InboundRequestMapper;
import allliveyoung.allliveinbound.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class InboundRequestServiceImpl implements InboundRequestService {

    private final InboundRequestMapper inboundRequestMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<InboundRequestDTO> findInbounds() {
        List<InboundRequestDTO> requests = inboundRequestMapper.findAll().stream().map(request -> modelMapper.map(request,InboundRequestDTO.class)).collect(Collectors.toList());
        return requests;
    }

    @Override
    public InboundRequestDTO findInbound(Long id) {
        /*Optional<InboundRequest> request = inboundRequestMapper.findById(id);
        InboundRequestDTO inboundRequest = modelMapper.map(request.orElseThrow(),InboundRequestDTO.class);
        return inboundRequest;*/
        return null;
    }

    @Override
    public Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO, List<InboundProductSaveDTO> inboundProductSaveDTOList) {
        inboundRequestMapper.save(inboundRequestSaveDTO);
        inboundRequestMapper.saveProducts(inboundProductSaveDTOList);
        Long id = inboundRequestSaveDTO.getId();
        return id;
    }

    @Override
    public void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO , List<InboundProductUpdateDTO> inboundRequestProducts) {
        inboundRequestMapper.update(inboundRequestUpdateDTO.getId());
        /*inboundRequestMapper.updateProducts(inboundRequestProducts.get((1)));*/
    }

    @Override
    public void deleteInbound(Long id) {
        inboundRequestMapper.delete(id);
    }

    @Override
    public void updateInboundStatus(Long id, String status) {
        Map map = Map.of("id",id,"status",status);
        inboundRequestMapper.updateStatus(map);
    }
}
