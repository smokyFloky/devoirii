
import java.util.Scanner;


public class main {

    public static  void main(String[] args){


        System.out.println("-******************** Regular Expression Test********************-");


        System.out.println("Entrer une expression infixee : ");

        Scanner in = new Scanner(System.in);

        String s = in.nextLine();


        InfixToPostfix inf = new InfixToPostfix();



        System.out.println("Expression Postfixee "+inf.getPostFixString(s));


        System.out.println("Resultat = "+ inf.calculate(s));




    }





}
