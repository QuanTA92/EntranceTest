package com.fpt.EntranceTest.service.imp;

import com.fpt.EntranceTest.dto.request.ProductColorSizeRequest;
import com.fpt.EntranceTest.dto.request.ProductRequest;
import com.fpt.EntranceTest.dto.response.ProductColorSizeResponse;
import com.fpt.EntranceTest.dto.response.ProductResponse;
import com.fpt.EntranceTest.modal.*;
import com.fpt.EntranceTest.modal.keys.ProductColorSizeKeys;
import com.fpt.EntranceTest.repository.*;
import com.fpt.EntranceTest.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductSizeColorRepository productSizeColorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public boolean addProduct(ProductRequest productRequest) {
        try {

            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setCreateDate(new Date());
//            product.setUpdateDate(new Date());

            product.setCategories(categoriesRepository.findById(productRequest.getIdCategory()).orElse(null));
            product.setStyle(styleRepository.findById(productRequest.getIdStyle()).orElse(null));

            Product savedProduct = productRepository.save(product);

            for (ProductColorSizeRequest colorSizeRequest : productRequest.getProductColorSizes()) {
                ProductColorSize productColorSize = new ProductColorSize();
                ProductColorSizeKeys keys = new ProductColorSizeKeys();
                keys.setIdProduct(savedProduct.getId());
                keys.setIdColor(colorSizeRequest.getIdColor());
                keys.setIdSize(colorSizeRequest.getIdSize());

                productColorSize.setProductColorSizeKeys(keys);
                productColorSize.setQuantity(colorSizeRequest.getQuantity());
                productColorSize.setCreateDate(new Date());
//                productColorSize.setStatus("Available");

                productSizeColorRepository.save(productColorSize);
            }

            if (productRequest.getImage() == null || productRequest.getImage().length == 0) {
                throw new IllegalArgumentException("At least one image must be provided.");
            }

            for (MultipartFile file : productRequest.getImage()) {
                String filePath = rootFolder + File.separator + file.getOriginalFilename();
                file.transferTo(new java.io.File(filePath));

                Image image = new Image();
                image.setUrlImage(filePath);
                image.setCreateDate(new Date());
                image.setProduct(savedProduct);
                imageRepository.save(image);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateProduct(int idProduct, ProductRequest productRequest) {

        try {
            Product existingProduct = productRepository.findById(idProduct).orElse(null);
            if (existingProduct == null) {
                return false;
            }

            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setUpdateDate(new Date());
            existingProduct.setCategories(categoriesRepository.findById(productRequest.getIdCategory()).orElse(null));
            existingProduct.setStyle(styleRepository.findById(productRequest.getIdStyle()).orElse(null));

            productRepository.save(existingProduct);

            productSizeColorRepository.deleteByProduct(existingProduct);

            for (ProductColorSizeRequest colorSizeRequest : productRequest.getProductColorSizes()) {
                ProductColorSize productColorSize = new ProductColorSize();
                ProductColorSizeKeys keys = new ProductColorSizeKeys();
                keys.setIdProduct(existingProduct.getId());
                keys.setIdColor(colorSizeRequest.getIdColor());
                keys.setIdSize(colorSizeRequest.getIdSize());

                productColorSize.setProductColorSizeKeys(keys);
                productColorSize.setQuantity(colorSizeRequest.getQuantity());
                productColorSize.setCreateDate(new Date());
//                productColorSize.setStatus("Available");

                productSizeColorRepository.save(productColorSize);
            }

            imageRepository.deleteByProduct(existingProduct);

            if (productRequest.getImage() != null && productRequest.getImage().length > 0) {
                for (MultipartFile file : productRequest.getImage()) {
                    String filePath = rootFolder + File.separator + file.getOriginalFilename();
                    file.transferTo(new java.io.File(filePath));

                    Image image = new Image();
                    image.setUrlImage(filePath);
                    image.setCreateDate(new Date());
                    image.setProduct(existingProduct);
                    imageRepository.save(image);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int idProduct) {
        try {
            // Kiểm tra xem sản phẩm có tồn tại hay không
            if (productRepository.existsById(idProduct)) {
                // Xóa sản phẩm
                productRepository.deleteById(idProduct);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            for (ProductColorSize productColorSize : product.getProductColorSizes()) {
                ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
                colorSizeResponse.setColor(productColorSize.getColor().getName());
                colorSizeResponse.setSize(productColorSize.getSize().getName());
                if (productColorSize.getQuantity() == 0) {
                    colorSizeResponse.setQuantity(0);
                    colorSizeResponse.setStatus("Sold Out");
                } else {
                    colorSizeResponse.setQuantity(productColorSize.getQuantity());
                    colorSizeResponse.setStatus("Available");
                }

                productColorSizeResponses.add(colorSizeResponse);
            }

            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public List<ProductResponse> getProductDetailsById(int idProduct) {
        Product product = productRepository.findById(idProduct).orElse(null);

        if (product == null) {
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategories().getName());
        productResponse.setStyle(product.getStyle().getName());

        List<String> imageUrls = new ArrayList<>();
        for (Image image : product.getImages()) {
            imageUrls.add(image.getUrlImage());
        }
        productResponse.setImage(imageUrls);

        List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
        for (ProductColorSize productColorSize : product.getProductColorSizes()) {
            ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
            colorSizeResponse.setColor(productColorSize.getColor().getName());
            colorSizeResponse.setSize(productColorSize.getSize().getName());

            if (productColorSize.getQuantity() == 0) {
                colorSizeResponse.setQuantity(0);
                colorSizeResponse.setStatus("Sold Out");
            } else {
                colorSizeResponse.setQuantity(productColorSize.getQuantity());
                colorSizeResponse.setStatus("Available");
            }

            productColorSizeResponses.add(colorSizeResponse);
        }

        productResponse.setProductColorSizeResponses(productColorSizeResponses);

        return List.of(productResponse);
    }

    @Override
    public List<ProductResponse> getAllProductsByCategory(int idCategory) {
        List<Product> products = productRepository.findByCategoriesId(idCategory);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            for (ProductColorSize productColorSize : product.getProductColorSizes()) {
                ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
                colorSizeResponse.setColor(productColorSize.getColor().getName());
                colorSizeResponse.setSize(productColorSize.getSize().getName());
                colorSizeResponse.setQuantity(productColorSize.getQuantity());
                if (productColorSize.getQuantity() == 0) {
                    colorSizeResponse.setQuantity(0);
                    colorSizeResponse.setStatus("Sold Out");
                } else {
                    colorSizeResponse.setQuantity(productColorSize.getQuantity());
                    colorSizeResponse.setStatus("Available");
                }

                productColorSizeResponses.add(colorSizeResponse);
            }

            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public List<ProductResponse> getAllProductsByStyle(int idStyle) {
        List<Product> products = productRepository.findByStyleId(idStyle);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            for (ProductColorSize productColorSize : product.getProductColorSizes()) {
                ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
                colorSizeResponse.setColor(productColorSize.getColor().getName());
                colorSizeResponse.setSize(productColorSize.getSize().getName());
                colorSizeResponse.setQuantity(productColorSize.getQuantity());
                colorSizeResponse.setStatus(productColorSize.getQuantity() > 0 ? "Available" : "Sold Out"); // Kiểm tra số lượng

                productColorSizeResponses.add(colorSizeResponse);
            }

            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public List<ProductResponse> getAllProductsByColor(int idColor) {
        List<ProductColorSize> productColorSizes = productSizeColorRepository.findByColorId(idColor);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductColorSize productColorSize : productColorSizes) {
            Product product = productColorSize.getProduct();

            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
            colorSizeResponse.setColor(productColorSize.getColor().getName());
            colorSizeResponse.setSize(productColorSize.getSize().getName());
            colorSizeResponse.setQuantity(productColorSize.getQuantity());
            colorSizeResponse.setStatus(productColorSize.getQuantity() > 0 ? "Available" : "Sold Out");

            productColorSizeResponses.add(colorSizeResponse);
            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public List<ProductResponse> getAllProductsBySize(int idSize) {
        List<ProductColorSize> productColorSizes = productSizeColorRepository.findBySizeId(idSize);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductColorSize productColorSize : productColorSizes) {
            Product product = productColorSize.getProduct();

            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
            colorSizeResponse.setColor(productColorSize.getColor().getName());
            colorSizeResponse.setSize(productColorSize.getSize().getName());
            colorSizeResponse.setQuantity(productColorSize.getQuantity());
            colorSizeResponse.setStatus(productColorSize.getQuantity() > 0 ? "Available" : "Sold Out");

            productColorSizeResponses.add(colorSizeResponse);
            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public List<ProductResponse> getAllProductsByPrice(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategories().getName());
            productResponse.setStyle(product.getStyle().getName());

            List<String> imageUrls = new ArrayList<>();
            for (Image image : product.getImages()) {
                imageUrls.add(image.getUrlImage());
            }
            productResponse.setImage(imageUrls);

            List<ProductColorSizeResponse> productColorSizeResponses = new ArrayList<>();
            for (ProductColorSize productColorSize : product.getProductColorSizes()) {
                ProductColorSizeResponse colorSizeResponse = new ProductColorSizeResponse();
                colorSizeResponse.setColor(productColorSize.getColor().getName());
                colorSizeResponse.setSize(productColorSize.getSize().getName());
                colorSizeResponse.setQuantity(productColorSize.getQuantity());
                colorSizeResponse.setStatus(productColorSize.getQuantity() > 0 ? "Available" : "Sold Out");
                productColorSizeResponses.add(colorSizeResponse);
            }

            productResponse.setProductColorSizeResponses(productColorSizeResponses);

            productResponses.add(productResponse);
        }
        return productResponses;
    }
}



