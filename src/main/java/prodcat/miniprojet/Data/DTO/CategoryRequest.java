package prodcat.miniprojet.Data.DTO;

import lombok.*;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private String name;
    private  int qt;

}
