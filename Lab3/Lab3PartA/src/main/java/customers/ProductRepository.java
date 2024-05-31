package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String, Object> params = new HashMap<String, Object>();
        jdbcTemplate.update("DELETE from product",params);
    }
    public void save(Product product) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("productNumber", product.getProductNumber());
        jdbcTemplate.update("INSERT INTO product VALUES (:productNumber, :name, :price)", params);
    }
    public Product findProduct(int productNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE productNumber = :productNumber", params,
                (rs, rowNum)-> new Product(rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        return product;
    }
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum)-> new Product(rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
    }
    public Product findProductByName(String productName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productName", productName);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE name = :productName", params,
                (rs, rowNum)-> new Product(rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        return product;
    }
    public void removeProduct(int productNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE FROM product WHERE productNumber = :productNumber", params);
    }

}
