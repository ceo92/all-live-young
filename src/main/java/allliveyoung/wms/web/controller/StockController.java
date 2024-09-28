package allliveyoung.wms.web.controller;

import allliveyoung.wms.constant.ProductType;
import allliveyoung.wms.constant.StoreTemperature;
import allliveyoung.wms.domain.Stock;
import allliveyoung.wms.service.StockService;
import allliveyoung.wms.web.dto.StockSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

  private final StockService stockService;


  //이렇게 모델로 넘겨주는 게 나음 ㅇㅇ
  @ModelAttribute("storeTemperatures")
  public StoreTemperature[] storeTemperatures(){
    return StoreTemperature.values();
  }

  @ModelAttribute("productTypes")
  public ProductType[] productTypes(){
    return ProductType.values();
  }

  @GetMapping
  public String getStocks(@ModelAttribute("stockSearch") StockSearch stockSearch, Model model , @Login Member member){
    List<Stock> stocks = stockService.findAll(stockSearch, member);
    model.addAttribute("member" , member);
    model.addAttribute("stocks" , stocks);
    return "stock/stocks";
  }


}
