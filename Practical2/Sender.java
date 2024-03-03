import java.io.BufferedReader;
import java.io.FileReader;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            System.out.println("Hello");

            FileReader f = new FileReader("./senderFiles/sendercode.txt");
            BufferedReader br = new BufferedReader(f);
            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getLocalHost(), 3000);
                ds.send(dp);
                str = br.readLine();
            }
            str = "endOfText";
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getLocalHost(), 3000);
            ds.send(dp);
            ds.close();
            br.close();
            System.out.println("Ends here...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}