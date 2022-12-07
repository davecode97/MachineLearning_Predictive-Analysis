import MultiLinearRegression.Cramer.MLR_Cramer;
import Optimization.LogisticRegression.LogisticRegression;
import Optimization.DescendingGradient.DescendingGradient;
import MultiLinearRegression.Vectorized.MLR_Vectorized;
import SimpleLinearRegression.SLR;
import SimpleLinearRegression.SLRCramer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int menu;
        Scanner option = new Scanner(System.in);

        menu();
        menu = option.nextInt();

        do {
            switch (menu) {
                case 1:
                    System.out.println("\t----------  S I M P L E  L I N E A R   R E G R E S S I O N   ----------");
                    new SLR().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                case 2:
                    System.out.println("\t----------  S I M P L E  L I N E A R   R E G R E S S I O N   C R A M M E R  2 x 2----------");
                    new SLRCramer().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                case 3:
                    System.out.println("\t---------- M U L T I L I N E A R   R E G R E S S I O N   V E C T O R I Z E D   ----------");
                    new MLR_Vectorized().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                case 4:
                    System.out.println("\t----------M U L T I L I N E A R   R E G R E S S I O N  C R A M E R  3 x 3 ----------");
                    new MLR_Cramer().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                case 5:
                    System.out.println("\t----------O P T I M I Z A T I O N   D E S C E N D I N G   G R A D I E N T   ----------");
                    new DescendingGradient().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                case 6:
                    System.out.println("\t----------  L O G I S T I C   R E G R E S S I O N   ----------");
                    new LogisticRegression().display();
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println("\nSelect another option...");
                    menu();
                    menu = option.nextInt();
                    break;
            }
            System.out.println("Program conclude...");
        }while (menu != 7);
    }

    static void menu()
    {
        System.out.println(" -------  MENU ------");
        System.out.println("1. Simple linear Regression.");
        System.out.println("2. Simple linear Regression - Cramer 2 x 2.");
        System.out.println("3. Multi Linear Regression   - Vectorized.");
        System.out.println("4. Multi Linear Regression   - Cramer 3 x 3.");
        System.out.println("5. Multi Linear Regression   - Descending Gradient.");
        System.out.println("6. Multi Linear Regression   - Logistic Regression.");
        System.out.println("7. Out.");
    }
}