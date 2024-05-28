import data.CustomerRepository;
import data.CustomerRepositoryImpl;
import integration.EmailSender;
import integration.EmailSenderImpl;
import integration.Logger;
import integration.LoggerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.CustomerService;
import service.CustomerServiceImpl;

@Configuration
@ComponentScan(basePackages = {"data", "integration", "service"})
public class Config {
//    @Bean
//    public Logger getLogger() {
//        return new LoggerImpl();
//    }
//    @Bean
//    public EmailSender getEmailSender() {
//        return new EmailSenderImpl(getLogger());
//    }
//    @Bean
//    public CustomerRepository getCustomerRepository() {
//        return new CustomerRepositoryImpl(getLogger());
//    }
//    @Bean
//    public CustomerService getCustomerService() {
//        return new CustomerServiceImpl();
//    }
}
