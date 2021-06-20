package prodcat.miniprojet.Services;

import prodcat.miniprojet.Data.DTO.ProductDto;
import prodcat.miniprojet.Data.DTO.ProductRequest;
import prodcat.miniprojet.Data.Moduls.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllEntities();
    ProductDto getEntityById(long id);
    ProductDto createEntity(ProductRequest entity);
    ProductDto modifyEntity(long id, ProductRequest newEntity);
    ProductDto deleteEntity(long id);
    List<ProductDto> getProductByCatg(long id);
}
