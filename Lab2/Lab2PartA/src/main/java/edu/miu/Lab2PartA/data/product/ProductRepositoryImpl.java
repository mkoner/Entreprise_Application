package edu.miu.Lab2PartA.data.product;

import edu.miu.Lab2PartA.domain.Product;
import edu.miu.Lab2PartA.integration.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private Logger logger;
    public ProductRepositoryImpl(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void save(Product product) {
        // simple sleep
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ProductRepository: saving product "+ product);
        logger.log("Product is saved in the DB: "+ product );
    }
}
