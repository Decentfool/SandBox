package su.csmb;

import lombok.extern.java.Log;
import su.csmb.client.PaymentModuleClient;
import su.csmb.server.PaymentModuleServer;
@Log
public class App {
    public static void main(String[] args) {
        try {
            if (args[0].equalsIgnoreCase("S")) {
                PaymentModuleServer.startServer(args);
            } else if (args[0].equalsIgnoreCase("C")) {
                PaymentModuleClient.startClient(args);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info("Inward Parameters is null, please start App with argument, possible value: S - Server, C - Client");
        }

    }
}
