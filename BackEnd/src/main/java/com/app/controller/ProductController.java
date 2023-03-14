package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.dto.web_product.CreateProductRequest;
import com.app.dto.web_product.ProductResponse;
import com.app.dto.web_product.ProductsPagedResponse;
import com.app.dto.web_product.UpdateProductRequest;
import com.app.entity.product;
import com.app.service.ImageHandlingService;
import com.app.service.ProductService;



@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ImageHandlingService imageService;
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest){
    	Long product = productService.createProduct(createProductRequest);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{productId}")
//                .buildAndExpand(product).toUri();
    System.out.println("id"+product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") Long productId) {

        ProductResponse product = productService.getProduct(productId);

        return ResponseEntity.ok(product);
    }
    
    @GetMapping("/products/{productId}")
    public ProductResponse getMyProduct(@PathVariable("productId") Long productId) {

        ProductResponse product=productService.getProduct(productId);

        return product;
    }
    
    
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody  UpdateProductRequest updateProductRequest) {
System.out.println("1");
        productService.updateProduct(updateProductRequest);

        return ResponseEntity.ok("success");
    }


    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            PagedResourcesAssembler<ProductResponse> assembler) {

        Page<ProductResponse> list = productService.getAllProducts(sort, page, size);
    
        Link link =Link.of(ServletUriComponentsBuilder.fromCurrentRequest().build()
                                                        .toUriString());

        PagedModel<EntityModel<ProductResponse>> resource = assembler.toModel(list, link);
    
        ProductsPagedResponse productsPagedResponse = new ProductsPagedResponse();
        productsPagedResponse.setPage(list);

        if (resource.getLink("first").isPresent()) {
            productsPagedResponse.get_links().put("first", resource.getLink("first").get().getHref());
        }

        if (resource.getLink("prev").isPresent()) {
            productsPagedResponse.get_links().put("prev", resource.getLink("prev").get().getHref());
        }

        if (resource.getLink("self").isPresent()) {
            productsPagedResponse.get_links().put("self", resource.getLink("self").get().getHref());
        }

        if (resource.getLink("next").isPresent()) {
            productsPagedResponse.get_links().put("next", resource.getLink("next").get().getHref());
        }

        if (resource.getLink("last").isPresent()) {
            productsPagedResponse.get_links().put("last", resource.getLink("last").get().getHref());
        }
    
        return ResponseEntity.ok(productsPagedResponse);

    }
    
    @GetMapping(value = "/products/get")
    public ResponseEntity<?> getAllProductss(){
    	
    	System.out.println("inside get all products");
    	return  new ResponseEntity<>(productService.getAllProductss(), HttpStatus.OK);
    }
 
    @GetMapping(value = "/products/get/{CategoryId}")
    public ResponseEntity<?> getAllProductssbycategory(@PathVariable Long CategoryId){
    	System.out.println("inside get all products by category id: "+CategoryId);
    List<product> products=	productService.getAllProductss(CategoryId);
    products.forEach(System.out::println);
    	return  new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @PostMapping(value="/{Productid}/image",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@RequestPart("image") MultipartFile imageFile,
			@PathVariable String Productid
			) throws IOException {
		System.out.println("in upload img " + Productid + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(imageService.uploadImage(Long.valueOf(Productid), imageFile), HttpStatus.CREATED);
	}
    	

	@GetMapping(value = "/{Productid}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable String Productid) throws IOException {
		System.out.println("in serve img " + Productid);
		return new ResponseEntity<>(imageService.serveImage(Long.valueOf(Productid)), HttpStatus.OK);
	}
}

