package practical5;

import java.util.ArrayList;
/**
 *
 * @author DIVYA
 */
public class SmartPhone{
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<Integer> quantities = new ArrayList<>();

    public SmartPhone() {
    }

    public SmartPhone(String name, int quantity) {
        names.add(name);
        quantities.add(quantity);
    }

    public void addItem(String name, int quantity) {
        names.add(name);
        quantities.add(quantity);
    }

    @Override
    public String toString() {
        String str = "{";
        for (int i = 0; i < names.size(); i++) {
            str += ("'"+ names.get(i) + "': '"+ quantities.get(i) + "', ");
        }
        str += "}";
        return str;
    }
}
