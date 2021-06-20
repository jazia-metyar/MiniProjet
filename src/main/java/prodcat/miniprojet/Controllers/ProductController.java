package prodcat.miniprojet.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.Data.DTO.CategoryDto;
import prodcat.miniprojet.Data.DTO.ProductDto;
import prodcat.miniprojet.Data.DTO.ProductRequest;
import prodcat.miniprojet.Services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
@RestController
public class ProductController {

    private ProductService productService;
    @Autowired
    public  ProductController (ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAllEntities();
    }


    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id")long id) {

        return productService.getEntityById(id);
    }


    @PostMapping
    public ProductDto createProduct( @RequestBody ProductRequest productRequest) {

        return productService.createEntity(productRequest);
    }

    @PutMapping("/{id}")
    public ProductDto modifyProduct(@PathVariable("id")long id,
                                      @RequestBody ProductRequest newEntity) {
        return productService.modifyEntity(id, newEntity);
    }

    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(@PathVariable("id")long id) {
        return productService.deleteEntity(id);
    }



    @GetMapping("prodbycatg/{id}")
    public List<ProductDto> getProductByCatg(@PathVariable("id")long id){
        return productService.getProductByCatg(id);
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
