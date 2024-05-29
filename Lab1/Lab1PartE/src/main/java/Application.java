import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.customer.CustomerService;
import service.product.ProductService;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		CustomerService customerService = context.getBean(
				CustomerService.class);
		ProductService productService = context.getBean(ProductService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");
		productService.addProduct("ProductA", 11);

	}
}