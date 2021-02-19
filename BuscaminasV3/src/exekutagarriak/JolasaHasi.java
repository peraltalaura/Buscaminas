
package exekutagarriak;

import java.util.Scanner;
import model.Hasiera;
import model.Jokua;

public class JolasaHasi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Sartu ilara kopurua:");
        int tamaina = in.nextInt();
        //Hasiera h=new Hasiera();
        Jokua j = new Jokua(tamaina);
    }
    
}
