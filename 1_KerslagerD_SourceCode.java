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
    static Scanner nextval = new Scanner(System.in);
    public static void main(String[] args){
        int menu = -1;
        while (menu !=0){
            System.out.println("Jaký program si přejete spustit?");
            System.out.println("1 - Vánoční úloha");
            System.out.println("2 - Semestrální práce 1, nejdelší setříděná část v zadané posloupnosti");
            System.out.println("0 - Konec");
            menu = getNextValue(Integer.class);
            switch (menu){
                case 1 -> christmas();
                case 2 -> posloupnostMenu();
                //default
            }
        }
    }
    /**
     * This method is a menu and input for method posloupnost, which finds longest sorted subset in a given set and outputs them using System.out.print.
     */
    public static void posloupnostMenu() {
        int members;
        int[] out = new int[2];
        while (true){
            System.out.println("Zadej počet členů: ");
            members = getNextValue(Integer.class);
            if (members<1){break;}
            //array inicialization and declaration from input
            float[] field = new float[members];
            System.out.println("Zadej posloupnost: ");
            for (int i =0;i<field.length;i++){
                field[i] = getNextValue(Float.class);
            }
            out = posloupnost(field);
            System.out.println("Nejdelsi setridena cast delky: "+out[0]);
            System.out.println("Zacatek "+out[1]+". prvek");
            System.out.println();
        }
    }
    /**
     * This method finds longest sorted subset in a given set and outputs them as an array out[0] = length of longest subset, out[1] = start of longest subset
     * @param field
     * @return 
     */
    public static int[] posloupnost(float[] field){
        int i=0;
        int len = 1; //length of current sorted subset
        int ascending = 1;
        int descending =0;
        int defaultType = -1;
        int curSeqType;
        int seqType=defaultType;
        int[] out = new int[2];
        out[0] = 1;out[1]=1;
        while (i<field.length-1){
            //equality case
            if (field[i]==field[i+1]){len++;i++;}
            else{
                //comparison
                curSeqType = (field[i]>field[i+1])?descending:ascending; 
                //first num exception
                if (seqType == defaultType){
                    seqType=curSeqType;
                }
                //comparison evaluation
                if (curSeqType == seqType){
                    len++;i++;
                }
                else {len=1;}
                //value changes evaluation
                seqType=curSeqType;
            }
            if (len>out[0]){out[0]=len;out[1]=i-out[0]+2;}
        }
        //out
        return out;
    }
    /**
     * This method prints out a parametric image of city with snow. Parameters are the amount of houses and the amount of snow.
     */
    public static void christmas(){
        //in
        System.out.println("kolik je domečků?");
        int domy = getNextValue(Integer.class);
        System.out.println("jak moc sněží?(číslo)");
        int sněžení = getNextValue(Integer.class);
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
    /**
     * This method outputs a Scanner input of a requested class.
     *   Method for threating wrong input class. Outputs request for value reentry when error would occur. Developed with OpenAi GPT3. (While procrastinating making presentation about this code)
     *   Localized for input classes of int, float and string. Can be expanded.
     * @param <T>   => expected class of Scanner input
     * @param clazz => expected class of Scanner input
     * @return      => value of Scanner input of the required class
     */
    public static <T> T getNextValue(Class<T> clazz) {
        while (true) {
            try {
              if (clazz == Integer.class) {
                return (T) Integer.valueOf(nextval.nextInt());
              } else if (clazz == Float.class) {
                return (T) Float.valueOf(nextval.nextFloat());
              } else if (clazz == String.class) {
                return (T) nextval.nextLine();
              } else {
                throw new IllegalArgumentException("Invalid type");
              }
            } catch (Exception e) {
              System.out.println("Špatný vstup, zadejte vadnou hodnotu a následující hodnoty prosím znovu.");
              nextval.nextLine();
            }
        }
    }
}
