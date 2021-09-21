package Chess.Server;

public class Main_Server {
    public static void main(String... args){
        ChessServer server = new ChessServer();
        Thread runnableServer = new Thread(server);

        runnableServer.start();
    }
}
