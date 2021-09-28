package Chess.Server;

import java.io.IOException;

public class Main_Server {
    public static void main(String... args) throws IOException, InterruptedException {
        HTTP_Server server = new HTTP_Server();
        server.runServer();
    }
}
