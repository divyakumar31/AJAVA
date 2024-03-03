// import java.io.BufferedReader;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.InputStreamReader;
import java.io.*;
import java.net.Socket;

public class Clients {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream data = new DataInputStream(socket.getInputStream());
            String message;

            System.out.println("Enter exit for close the connection...");
            while (true) {
                System.out.println("Enter the message: ");
                output.writeUTF(br.readLine());
    
                message = data.readUTF();
                System.out.println(message);
                if ("Good bye...".equalsIgnoreCase(message)) {
                    break;
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
