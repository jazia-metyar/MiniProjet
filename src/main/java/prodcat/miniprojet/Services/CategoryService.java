package prodcat.miniprojet.Services;

import prodcat.miniprojet.Data.DTO.CategoryDto;
import prodcat.miniprojet.Data.DTO.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllEntities();
    CategoryDto getEntityById(long id);
    CategoryDto createEntity(CategoryRequest entity);
    CategoryDto modifyEntity(long id, CategoryRequest newEntity);
    CategoryDto deleteEntity(long id);

}
