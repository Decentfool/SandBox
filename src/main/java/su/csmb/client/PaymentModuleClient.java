package su.csmb.client;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:/client.properties")
public class PaymentModuleClient {
    public static void startClient(String[] args) {
        SpringApplication.run(PaymentModuleClient.class, args);
    }

}
