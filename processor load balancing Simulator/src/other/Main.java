package other;

import Algorytms.AboveLoadSend;
import Algorytms.ActiveLoadBalancingSend;
import Algorytms.LoadWithRandomSend;

public class Main {
    public static void main(String[] args) {
        AboveLoadSend two = new AboveLoadSend();
        LoadWithRandomSend one = new LoadWithRandomSend();
        ActiveLoadBalancingSend three = new ActiveLoadBalancingSend();
        AboveLoadSend f = new AboveLoadSend();

        boolean loop = true;
        while (loop){
            loop = false;
            loop |= one.run();
            loop |= two.run();
            loop |= three.run();
        }
        one.getInfo();
        two.getInfo();
        three.getInfo();
    }
}
