import java.io.File;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    public static void main(String[] args) {
        try {
            System.out.println("Hey");
            DatagramSocket ds = new DatagramSocket(3000);
            byte[] buf = new byte[1024];
            File file = new File("./reFiles/recfile.txt");
            FileWriter fr = new FileWriter(file, true);
            String reply = "";

            while (true) {
                DatagramPacket dp = new DatagramPacket(buf, 1024);
                ds.receive(dp);
                reply = new String(dp.getData(), 0, dp.getLength());

                if (reply.equalsIgnoreCase("endOfText")) {
                    break;
                }
                fr.write(reply + "\n");
                System.out.println(reply);
            }
            fr.close();
            ds.close();
            System.out.println("Ends here...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}