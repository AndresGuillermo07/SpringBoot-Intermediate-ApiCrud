package ls.andres.springboot.app.springboot_crud.controller;

import jakarta.validation.Valid;
import ls.andres.springboot.app.springboot_crud.entity.Product;
import ls.andres.springboot.app.springboot_crud.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> list() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {

        if(result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {

        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product,BindingResult result,@PathVariable Long id) {

        if(result.hasFieldErrors()){
            return validation(result);
        }

        Optional<Product> productOptional = productService.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.delete(id);
            return ResponseEntity.ok(product.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
