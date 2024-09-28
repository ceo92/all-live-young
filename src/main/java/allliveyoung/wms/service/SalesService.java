package allliveyoung.wms.service;

import allliveyoung.wms.domain.Sales;
import allliveyoung.wms.mapper.SalesMapper;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import allliveyoung.wms.web.dto.SalesResponseDTO;
import allliveyoung.wms.web.dto.SalesSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesMapper salesMapper;
    private final MemberMapper memberMapper;

    /**
     * 매출 전체 항목 조회
     *
     * @param salesRequestDTO
     * @return
     */
    @Transactional(readOnly = true)
    public SalesResponseDTO findSales(SalesRequestDTO salesRequestDTO) {
        return SalesResponseDTO.builder()
                .salesRequestDTO(salesRequestDTO)
                .sales(salesMapper.findAll(salesRequestDTO))
                .total(salesMapper.count(salesRequestDTO))
                .build();
    }

    /**
     * 매출 단일 항목 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Sales findSales(Long id) {
        return salesMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 id 값입니다."));
    }

    /**
     * 매출 등록
     *
     * @param salesSaveDTO
     * @return
     */
    @Transactional
    public Long saveSale(SalesSaveDTO salesSaveDTO, Warehouse warehouse) {
        if (salesSaveDTO.getCategory().equals("기타")) {
            salesMapper.save(Sales.builder()
                    .warehouse(warehouse)
                    .salesDate(salesSaveDTO.getSalesDate())
                    .category(salesSaveDTO.getCategory())
                    .amount(salesSaveDTO.getAmount())
                    .description(salesSaveDTO.getDescription())
                    .regDate(LocalDateTime.now())
                    .build());
        } else {
            salesMapper.save(Sales.builder()
                    .warehouse(warehouse)
                    .salesDate(salesSaveDTO.getSalesDate())
                    .category(salesSaveDTO.getCategory())
                    .member(memberMapper.findByName(salesSaveDTO.getMemberName()).get())
                    .amount(salesSaveDTO.getAmount())
                    .description(salesSaveDTO.getDescription())
                    .regDate(LocalDateTime.now())
                    .build());
        }
        return 1L;
    }
}
