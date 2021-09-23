package Chess.Server;

import java.io.IOException;

public class Main_Server {
    public static void main(String... args) throws IOException, InterruptedException {
        ChessServer server = new ChessServer();
        server.runServer();
    }
}
