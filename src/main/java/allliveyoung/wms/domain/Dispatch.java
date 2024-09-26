package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dispatch {
  private Long id;
  private String code;
  private Status status;
  private Member member;
  private String note;
}
