/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical4;

import java.util.ArrayList;

/**
 *
 * @author DIVY
 */
public class SmartPhone {

    private static ArrayList<String> names = new ArrayList<String>();

    public SmartPhone() {
    }

    public SmartPhone(String name) {
        names.add(name);
    }

    public void addItem(String name) {
        names.add(name);
    }

    @Override
    public String toString() {
        String str = "You have added ";
        for (int i = 0; i < names.size(); i++) {
            str += (names.get(i) + ", ");
        }
        str += "in your cart.";
        return str;
    }
}
