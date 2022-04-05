package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class GRPCServer {

    private static final Logger logger = Logger.getLogger(GRPCServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        System.out.println("Starting GRPC Server.....");

        String url = "jdbc:mysql://localhost:3306/grpcassignment";
        String user = "root";
        String pass = "";
      //  Statement statement = connection.createStatement();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = getConnection(url, user, pass);

        Server server = ServerBuilder.forPort(8080).addService(new Service(connection)).build();
        server.start();

        logger.info("Server started at port : " + server.getPort());

        server.awaitTermination(); //60, TimeUnit.SECONDS
    }
}
