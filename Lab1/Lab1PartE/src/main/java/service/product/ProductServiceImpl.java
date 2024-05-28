package service.product;

import data.product.ProductRepository;
import domain.Product;
import integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    EmailSender emailSender;

    @Autowired
    public void setCustomerRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
    @Override
    public void addProduct(String productName, double price) {
        productRepository.save(new Product(productName, price));
        emailSender.sendEmail("abc@gmail.com", "New product added: " + productName);
    }
}
