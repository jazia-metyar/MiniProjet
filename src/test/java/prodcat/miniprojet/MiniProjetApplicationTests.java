package prodcat.miniprojet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prodcat.miniprojet.data.moduls.Category;
import prodcat.miniprojet.data.moduls.Product;
import prodcat.miniprojet.data.repositories.CategoryRepository;
import prodcat.miniprojet.data.repositories.ProductRepository;
import prodcat.miniprojet.services.CategoryService;
import prodcat.miniprojet.services.ProductService;

import java.sql.Timestamp;

@SpringBootTest
class MiniProjetApplicationTests {
   CategoryRepository categoryRepository;
   ProductRepository productRepository;
   CategoryService categoryService;
   ProductService productService;

    @Autowired
    public MiniProjetApplicationTests(CategoryRepository categoryRepository,
                                      ProductRepository productRepository,
                                      CategoryService categoryService,
                                      ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryService=categoryService;
        this.productService=productService;
    }


/*
    @Test
    void contextLoads() {
    }
 */  @Test
    void addCategory()
    {
       Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        Category category=new Category();
        category.setName("Phones");
        category.setQt(214);
        category.setDateCreation(timestamp);

          categoryService.createEntity(category);
    }

    @Test
    void addProduct()
    {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        Product product=new Product();
        product.setName("pc");
        product.setQt(51);
        product.setDispo(true);
        product.setDateCreation(timestamp);
        product.setCategory(categoryRepository.findById((long)21).get());
         productService.createEntity(product);
      //  assertThat(body).isEqualTo("Hello World");

    }

}
