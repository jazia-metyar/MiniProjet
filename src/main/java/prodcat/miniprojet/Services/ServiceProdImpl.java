package prodcat.miniprojet.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.Data.DTO.CategoryDto;
import prodcat.miniprojet.Data.DTO.ProductDto;
import prodcat.miniprojet.Data.DTO.ProductRequest;
import prodcat.miniprojet.Data.Moduls.Category;
import prodcat.miniprojet.Data.Moduls.Product;
import prodcat.miniprojet.Data.Repositories.ProductRepository;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceProdImpl implements ProductService {


    private ProductRepository productRepo;
    private ServiceCatImpl serviceCat;
    private ModelMapper mapper;

    @Autowired
    public ServiceProdImpl( ProductRepository productRepo,
                            ServiceCatImpl serviceCat,
                            ModelMapper mapper) {
        super();
        this.productRepo=productRepo;
        this.serviceCat=serviceCat;
        this.mapper = mapper;
    }
    @Override
    public List<ProductDto> getAllEntities() {
        List<ProductDto>  productDtoList=new ArrayList<>();
        for(Product cat : productRepo.findAll())
            productDtoList.add(mapper.map(cat, ProductDto.class));
        return productDtoList;
    }



    @Override
    public ProductDto getEntityById(long id) {
        Optional<Product> opt = productRepo.findById(id);
        Product entity;
        if (opt.isPresent())
            entity = opt.get();
        else
            throw new NoSuchElementException("Product with this id is not found");

        return mapper.map(entity,ProductDto.class);
    }




    @Override
    public ProductDto createEntity(ProductRequest entity) {
        // covert ProductRequest to Product
        Product product=mapper.map(entity,Product.class);

        // set Date_creation : current date
        product.setDate_creation(new Timestamp(System.currentTimeMillis()));

        //set Category
        CategoryDto cat=serviceCat.getEntityById(product.getCategory().getId());

        product.setCategory( mapper.map(cat, Category.class));
        // save product
        Product  prodInBase =productRepo.save(product);

        // covert    product to ProductDto
        return mapper.map(prodInBase,ProductDto.class);
    }


    @Override
    public ProductDto modifyEntity(long id, ProductRequest newEntity) {

        // covert  ProductRequest to Product
        Product newProduct=mapper.map(newEntity,Product.class);

        ProductDto oldProduct= this.getEntityById(id);

        // modification  nom
        if (newProduct.getName() != null)
            oldProduct.setName(newProduct.getName());

        // modification  qt
        if (newProduct.getQt() != 0)
            oldProduct.setQt(newProduct.getQt());

        // modification  Dispo
        if ((newProduct.isDispo())!=false)

            oldProduct.setDispo(newProduct.isDispo());

        // set  date modify :current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");


        oldProduct.setDate_modify(new Timestamp(System.currentTimeMillis()));


        // set category

        if (newProduct.getCategory()!=null) {
            CategoryDto cat = serviceCat.getEntityById(newProduct.getCategory().getId());
            oldProduct.setCategory(mapper.map(cat, Category.class));
          }
        // save Product
        Product prodInBase =productRepo.save(mapper.map(oldProduct,Product.class));

        // covert Product to ProductDto
        return mapper.map(prodInBase,ProductDto.class);
    }



    @Override
    public ProductDto deleteEntity(long id) {
        ProductDto entity = this.getEntityById(id);
        productRepo.deleteById(id);
        return entity;
    }

    @Override
    public List<ProductDto> getProductByCatg(long id) {

        List<ProductDto>  productDtoList=new ArrayList<>();
        for(Product cat : productRepo.findByCategory_Id(id))
            productDtoList.add(mapper.map(cat, ProductDto.class));
        return productDtoList;



    }
}
