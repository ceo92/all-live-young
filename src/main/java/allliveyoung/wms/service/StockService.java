package allliveyoung.wms.service;

import allliveyoung.wms.domain.Stock;
import allliveyoung.wms.mapper.StockMapper;
import allliveyoung.wms.web.dto.StockSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class StockService {

  private final StockMapper stockMapper;

  public List<Stock> findAll(StockSearch stockSearch, Member member) {
    return stockMapper.findAll(stockSearch, member);

  }
}
