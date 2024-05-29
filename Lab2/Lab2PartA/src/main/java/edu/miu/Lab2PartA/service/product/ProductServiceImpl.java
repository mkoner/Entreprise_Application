package edu.miu.Lab2PartA.service.product;

import edu.miu.Lab2PartA.data.product.ProductRepository;
import edu.miu.Lab2PartA.domain.Product;
import edu.miu.Lab2PartA.integration.EmailSender;
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
