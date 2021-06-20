package prodcat.miniprojet.Data.Moduls;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private  int qt;
    private  boolean dispo;
    private Timestamp Date_creation;
    private Timestamp Date_modify;
   @ManyToOne( cascade = CascadeType.DETACH)
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public Timestamp getDate_creation() {
        return Date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        Date_creation = date_creation;
    }

    public Timestamp getDate_modify() {
        return Date_modify;
    }

    public void setDate_modify(Timestamp date_modify) {
        Date_modify = date_modify;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
