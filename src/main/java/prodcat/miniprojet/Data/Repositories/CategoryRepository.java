package prodcat.miniprojet.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prodcat.miniprojet.Data.Moduls.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
