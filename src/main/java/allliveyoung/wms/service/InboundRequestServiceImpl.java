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
    public InboundPageResponseDTO<InboundRequestDTO> findInbounds(InboundPageRequestDTO inboundPageRequestDTO) {
        List<InboundRequestDTO> requests = inboundRequestMapper.findAll(inboundPageRequestDTO).stream()
                .map(request -> modelMapper.map(request,InboundRequestDTO.class)).collect(Collectors.toList());

        int total = inboundRequestMapper.getCount(inboundPageRequestDTO);

        InboundPageResponseDTO<InboundRequestDTO> responseDTO = InboundPageResponseDTO.<InboundRequestDTO>withAll()
                .dtoList(requests).total(total).inboundPageRequestDTO(inboundPageRequestDTO).build();

        return responseDTO;
    }

    @Override
    public List<InboundProductDTO> findInbound(Long id) {
        List<InboundRequestProduct> inboundRequestProducts = inboundRequestMapper.findById(id);
        List<InboundProductDTO> inboundProductDTOList = inboundRequestProducts.stream()
                .map(inboundRequestProduct -> modelMapper.map(inboundRequestProduct,InboundProductDTO.class)).collect(Collectors.toList());

        return inboundProductDTOList;
    }

    @Override
    public Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO) {
        inboundRequestMapper.save(inboundRequestSaveDTO);
        /*inboundRequestMapper.saveProducts(inboundRequestSaveDTO.getInboundProductSaveDTOList());*/
        Long id = inboundRequestSaveDTO.getId();
        return id;
    }

    @Override
    public void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO , List<InboundProductUpdateDTO> inboundRequestProducts) {
        inboundRequestMapper.update(inboundRequestUpdateDTO.getId());
        inboundRequestMapper.updateProducts(inboundRequestProducts);
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

    @Override
    public List<WarehouseDTO> getWarehouseList() {
        List<Warehouse> warehouseList = inboundRequestMapper.getWarehouseList();
        List<WarehouseDTO> warehouseDTOList = warehouseList.stream()
                .map(warehouse -> modelMapper.map(warehouse,WarehouseDTO.class)).collect(Collectors.toList());
        return warehouseDTOList;
    }

    @Override
    public List<ProductDTO> getMatchedProductList(Long id) {
        List<ProductDTO> productDTOList = inboundRequestMapper.getMatchedProductList(id).stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        return productDTOList;
    }
}
