package prodcat.miniprojet.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.Data.DTO.CategoryDto;
import prodcat.miniprojet.Data.DTO.CategoryRequest;
import prodcat.miniprojet.Services.ServiceCatImpl;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*")
@RequestMapping("/api/category")
@RestController
public class CategoryController {

    private ServiceCatImpl serviceCat;

    @Autowired
    public CategoryController(ServiceCatImpl serviceCat)
    {		super();
        this.serviceCat=serviceCat;
    }


    @GetMapping
    public List<CategoryDto> getAll(){
        return serviceCat.getAllEntities();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable("id")long id) {

        return serviceCat.getEntityById(id);
    }



    @PostMapping
    public CategoryDto createCategory( @RequestBody CategoryRequest categoryRequest) {

        return serviceCat.createEntity(categoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryDto modifyCategory(@PathVariable("id")long id,
                                     @RequestBody CategoryRequest newCategory) {
        return serviceCat.modifyEntity(id, newCategory);
    }

    @DeleteMapping("/{id}")
    public CategoryDto deleteCategory(@PathVariable("id")long id) {
        return serviceCat.deleteEntity(id);
    }






    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // To Return 1 validation error
        //return new ResponseEntity<String>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }


}


