package allliveyoung.wms.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

  private Long id;
  private String name;
  private Address address;
  private String code;


}
