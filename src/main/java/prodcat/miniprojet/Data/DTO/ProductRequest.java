package prodcat.miniprojet.Data.DTO;

import lombok.*;
import prodcat.miniprojet.Data.Moduls.Category;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private  int qt;
    private  boolean dispo;
    private Timestamp Date_creation;
    private Timestamp Date_modify;

    private Category category;
}
