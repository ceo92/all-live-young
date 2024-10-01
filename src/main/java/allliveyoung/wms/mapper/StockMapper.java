package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.InboundRequestProduct;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.Stock;
import allliveyoung.wms.web.dto.StockSearch;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper {

  void update(@Param("stockId") Long stockId , @Param("inboundRequestProduct") InboundRequestProduct inboundRequestProduct); //재고 수정(재고 실사)

  List<Stock> findAll(@Param(value = "stockSearch") StockSearch stockSearch, @Param(value = "member") Member member); // 검색조건 별 전체 재고 화면 조회

  Optional<Stock> findById(Long id); //상세 재고 화면 조회



}
