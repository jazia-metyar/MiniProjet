package prodcat.miniprojet.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prodcat.miniprojet.Data.Moduls.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


   List<Product> findByCategory_Id(long idCAt);
}
