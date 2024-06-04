package other;

import Algorytms.*;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        ProcesorMaker maker = ProcesorMaker.getInstance();
        Equal equal = new Equal();
        SiteError siteError = new SiteError();
        Proportional proportional = new Proportional();
        Zone zone = new Zone();


        boolean t = true;
        while (t){
            t = maker.generateFrames();
            proportional.run(t);
            equal.run(t);
            siteError.run(t);
            zone.run(t);
        }
        equal.getInfo();
        proportional.getInfo();
        siteError.getInfo();
        zone.getInfo();
    }
}