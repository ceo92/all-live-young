package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  private String roadAddress;
  private String zipCode;
  private String detailsAddress;
  private String fullAddress = zipCode + " " + roadAddress + " " + detailsAddress;
}
