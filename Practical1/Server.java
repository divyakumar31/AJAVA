import java.net.*;
import java.io.*;

class ClientThread extends Thread {
    private Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream data = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String reply;

            while (true) {
                String msg = data.readUTF();
                
                if("exit".equalsIgnoreCase(msg)){
                    reply = "Good bye...";
                    output.writeUTF(reply);
                    break;
                } else {
                    reply = "Your Message: " + msg + "\nMessage Length: " + msg.length();
                    output.writeUTF(reply);
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            while (true) {
                Socket socket = server.accept();
                new ClientThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
