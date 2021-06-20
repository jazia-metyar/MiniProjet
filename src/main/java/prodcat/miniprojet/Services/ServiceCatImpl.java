package prodcat.miniprojet.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.Data.DTO.CategoryDto;
import prodcat.miniprojet.Data.DTO.CategoryRequest;
import prodcat.miniprojet.Data.Moduls.Category;
import prodcat.miniprojet.Data.Moduls.Product;
import prodcat.miniprojet.Data.Repositories.CategoryRepository;
import prodcat.miniprojet.Data.Repositories.ProductRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceCatImpl implements CategoryService{


    private CategoryRepository categoryRepo;
    private ProductRepository productRepo;
    private ModelMapper mapper;

    @Autowired
    public ServiceCatImpl(CategoryRepository categoryRepo,
                          ProductRepository productRepo,
                          ModelMapper mapper) {
        super();
        this.categoryRepo=categoryRepo;
        this.productRepo=productRepo;
        this.mapper = mapper;
    }
    @Override
    public List<CategoryDto> getAllEntities() {
      List<CategoryDto>  categoryDtoList=new ArrayList<>();
      for(Category cat : categoryRepo.findAll())
          categoryDtoList.add(mapper.map(cat, CategoryDto.class));
      return categoryDtoList;
    }

    @Override
    public CategoryDto getEntityById(long id) {
        Optional<Category> opt = categoryRepo.findById(id);
        Category entity;
        if (opt.isPresent())
            entity = opt.get();
        else
            throw new NoSuchElementException("Category with this id is not found");

        return mapper.map(entity,CategoryDto.class);
    }

    @Override
    public CategoryDto createEntity(CategoryRequest entity) {
       // covert CategoryRequest to Category
            Category category=mapper.map(entity,Category.class);

        // set current date
            category.setDate_creation(new Timestamp(System.currentTimeMillis()));

         // save category
            Category catInBase =categoryRepo.save(category);

           // covert    Category to CategoryDto
    return mapper.map(catInBase,CategoryDto.class);
    }

    @Override
    public CategoryDto modifyEntity(long id, CategoryRequest newEntity) {

        // covert    CategoryRequest to Category
        Category newCategory=mapper.map(newEntity,Category.class);

        CategoryDto oldCategory = this.getEntityById(id);

        // modification  nom
        if (newCategory.getName() != null)
            oldCategory.setName(newCategory.getName());

        // modification  qt
        if (newCategory.getQt() != 0)
            oldCategory.setQt(newCategory.getQt());

        // set current date
        oldCategory.setDate_modify(new Timestamp(System.currentTimeMillis()));


        // save category
        Category catInBase =categoryRepo.save(mapper.map(oldCategory,Category.class));

        // covert Category to CategoryDto
        return mapper.map(catInBase,CategoryDto.class);
    }

    @Override
    public CategoryDto deleteEntity(long id) {
        CategoryDto entity = this.getEntityById(id);
        List<Product> productList=productRepo.findByCategory_Id(id);

         System.err.println(productList);
             for(Product p:productList)
                productRepo.delete(p);

        categoryRepo.deleteById(id);


        return entity;
    }




}
