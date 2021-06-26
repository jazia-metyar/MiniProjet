package prodcat.miniprojet.data.moduls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private  int qt;
    private  boolean dispo;
    private Timestamp dateCreation;
    private Timestamp dateModify;
   @ManyToOne
    private Category category;

}
