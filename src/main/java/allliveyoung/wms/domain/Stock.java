package allliveyoung.wms.domain;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {

  private Long id;
  private String stockCode;

  @DateTimeFormat(pattern = "yyyy. MM. dd. a hh:mm")
  private LocalDateTime regDate;


  @DateTimeFormat(pattern = "yyyy. MM. dd. a hh:mm")
  private LocalDateTime modDate;

  private Product product; // 상품명 , 회사명 , 유효기간 , 제조번호 , 보관온도 , 상품 종류 , 총 박스 수량 전부 Product 혹은 Product의 연관관계로 알 수 있음!

  private Warehouse warehouse; //창고명 , 창고주소

  private Pallet pallet;

  public Integer getTotalStockCount(){
    int palletQuantity = pallet.getInboundRequestProduct().getPalletQuantity();
    int boxQuantity = pallet.getInboundRequestProduct().getBoxQuantity();
    return palletQuantity * boxQuantity;
  }




}
