package prodcat.miniprojet.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.data.dto.CategoryDto;
import prodcat.miniprojet.data.moduls.Category;
import prodcat.miniprojet.data.repositories.CategoryRepository;
import prodcat.miniprojet.services.CategoryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/category")
@RestController
@AllArgsConstructor
public class CategoryController {
    private ModelMapper mapper;
   private  CategoryRepository categoryRepository;
   private  CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Category> getById(@PathVariable("id")long id) {

        return categoryRepository.findById(id);
    }

    @PostMapping
    public Category createCategory( @RequestBody CategoryDto categoryDto) {
        var category=  mapper.map(categoryDto, Category.class);

        return  categoryService.createEntity(category);
    }

    @PutMapping("/{id}")
    public Category modifyCategory(@PathVariable("id")long id,
                                   @RequestBody CategoryDto categoryDto) {
        var category=  mapper.map(categoryDto, Category.class);

        return categoryService.modifyEntity(id,category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id")Category category) {

        categoryRepository.delete(category);
    }






    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var stringBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            stringBuilder.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.BAD_REQUEST);
    }


}


