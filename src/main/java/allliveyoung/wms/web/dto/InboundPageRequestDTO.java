package allliveyoung.allliveinbound.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundPageRequestDTO {

    private List<String> types;

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;
    private String type;
    private String link;

    public int getSkip() {
        return (page - 1) * 10;
    }

    public String[] getTypes(){
        if(type == null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    public boolean hasTypes() {
        return types != null && !types.isEmpty();
    }

    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            if(type != null && type.length() > 0){
                builder.append("&type=" + type);
            }
            link = builder.toString();
        }
        return link;
    }
}