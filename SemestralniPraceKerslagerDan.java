import static java.lang.Math.round;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
/**
 * 1. Program searches for the longest sorted subset in a given set.
 * @author dankerslager
 * Java: 17.0.4.1 6.12.2022
 */
public class SemestralniPraceKerslagerDan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int menu = 3;
        while (menu !=0){
            System.out.println("Jaký program si přejete spustit?");
            System.out.println("1 - Vánoční úloha");
            System.out.println("2 - Semestrální práce 1, nejdelší setříděná část v zadané posloupnosti");
            System.out.println("0 - Konec");
            menu = sc.nextInt();
            switch (menu){
                case 1 -> christmas();
                case 2 -> posloupnostMenu();
            }
        }
    }
    public static void posloupnostMenu() {
        /*
        This method is a menu and input for method posloupnost, which finds longest sorted subset in a given set and outputs them using System.out.print.
        */
        Scanner sc = new Scanner(System.in);
        int members=2;
        while (members>1){
            System.out.println("Zadej počet členů: ");
            members = sc.nextInt();
            //array inicialization and declaration from input
            int[] field = new int[members];
            System.out.println("Zadej posloupnost: ");
            for (int i =0;i<field.length;i++){
                field[i] = sc.nextInt();
            }
            posloupnost(field);
        }
    }
    public static void posloupnost(int[] field){
        /*
        This method finds longest sorted subset in a given set and outputs them using System.out.print
        */
        int i=0;
        int maxlen = 1;
        int len = 1;
        int start = 1;
        int raising;
        int wasRaising=-1;
        while (i<field.length-1){
            //equality case
            if (field[i]==field[i+1]){len++;i++;}
            
            else{
                //comparison
                if (field[i]>field[i+1]){raising = 0;}
                else /*if (field[i]<field[i+1])*/{raising = 1;}
                //first num exception
                if (wasRaising == -1){wasRaising=raising;}
                //comparison evaluation
                if (raising == wasRaising){
                    len++;i++;
                }
                else {len=1;}
                //value changes evaluation
                wasRaising=raising;
                if (len>maxlen){maxlen=len;start=i-maxlen+2;}
            }
        }
        //out
        System.out.println("Nejdelsi setridena cast delky: "+maxlen);
        System.out.println("Zacatek "+start+". prvek");
        System.out.println();
    }
    public static void christmas(){
        /*
        This method prints out a parametric image of city with snow. Parameters are the amount of houses and the amount of snow.
        */
        //in
        Scanner sc = new Scanner(System.in);
        System.out.println("kolik je domečků?");
        int domy = sc.nextInt();
        System.out.println("jak moc sněží?(číslo)");
        int sněžení = sc.nextInt();
        //assets
        String[] house = {"            ","            ","   \\         ","   ))        "," .-#-----.   ","/_________\\  "," |[] _ []|   ",".|  |*|  |.  "};
        String[][] hous={{},{},{},{},{},{},{},{}};
        String[] middle = {"                |       ","               -+-       ","    o          _|_       ","   }^{        /___\\      ","   /|\\     .---'-'---.   ","  //|\\\\   /___________\\  ","  //|\\\\    | A /^\\ A |   ",".///|\\\\\\...|   |\"|   |..."};
        String[][] middl={{},{},{},{},{},{},{},{}};
        //asset prep
        for (int i = 0;i<house.length;i++){
            hous[i] = house[i].split("");
            middl[i] = middle[i].split("");
        }
        //declarations
        int tree = (int) round(((domy-1) * Math.random()));
        int prnt;
        int snownum = 0;
        int snowfac = (int) round(sněžení*0.91);
        int[]  snowplaces =  (IntStream.generate(() -> new Random().nextInt((domy*7*13)+24)).limit(5*snowfac).toArray());
        //for (int b=0;b<snowplaces.length;b++){System.out.print(snowplaces[b]+" ");}
        boolean bol;
        //out
        for (int n=0 ; n<house.length ; n++){
            for (int dum=0 ; dum<domy+1 ; dum++){
                if (dum==tree){prnt=23;}
                else {prnt=12;}
                for (int m = 0;m<prnt;m++){
                    bol = true;
                    for (int lmao=0;lmao<snowplaces.length;lmao++){
                        if (snownum==snowplaces[lmao]){
                            snowplaces[lmao]=0;
                            bol=false;
                            System.out.print("*");break;}
                    }
                    if (bol==true){
                    if (dum==tree){  System.out.print(middl[n][m]);}
                    else {System.out.print(hous[n][m]);}
                    snownum=snownum+1;}}
                }    
                    if (n==7){System.out.print(("."));}
                    else {System.out.print((" "));}
                    snownum=snownum+1;
                
            System.out.println();}
        }   
}