package su.csmb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:/server.properties")
public class PaymentModuleServer {
    public static void startServer(String[] args) {
        SpringApplication.run(PaymentModuleServer.class, args);
    }
}
