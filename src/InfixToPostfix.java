
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {

    //Creation d'une methode pour les opérateurs unaires

    private int prefOperator(char c) {

        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/')
            return 2;
        else if (c == '$')
            return 3; // la racine est la plus puissante, donc attribuer un grand nombre
        else return -1;


    }
    //Méthode pour le calcule des opérandes

    /******
     *
     *     Parcourir la liste, si un element a l'index I est opérande , on le push dans la queue,si l'element est un operateur
     *     on pop deux opérande, et on fait l'opération
     *
     *
     *
     */


    public float calculatingExpression(List<String> expList) {
        Stack<Float> stackdbl = new Stack<>();

        float res = 0;
        for (int i = 0; i < expList.size(); i++) {

            String character = expList.get(i); // Extraction d'un element de la list
            String pow = expList.get(0); // Commentaire ajouter pour le sqrt
            if (character.length() == 1 && character.charAt(0) == '+' || character.charAt(0) == '-'
                    || character.charAt(0) == '*' || character.charAt(0) == '/') {  // Statement pour verifier si l'element a index =0 est un operateur

                float numb2 = stackdbl.pop(); // Si oui, pop deux nombres
                float numb1 = stackdbl.pop();

                if (character.charAt(0) == '+') {  // Application d'addition, si charAt(0) == + , la meme chose pour les autres opérations
                    res = numb1 + numb2;
                    stackdbl.push(res);


                } else if (character.charAt(0) == '-') {
                    res = numb1 - numb2;
                    stackdbl.push(res);
                } else if (character.charAt(0) == '*') {
                    res = numb1 * numb2;
                    stackdbl.push(res);

                } else if (character.charAt(0) == '/') {
                    res = numb1 / numb2;
                    stackdbl.push(res);

                }


            } else if (character.charAt(0) == '$') { // Si on applique la racine, on doit la faire sortir de la condition ci-dessus, on pop un nombre de la queue
                // ce nombre peut etre un resultat entre l'Addition/soustraction/division/multi entre deux nombres
                float f = stackdbl.pop();

                float l = (float) Math.sqrt(f);
                stackdbl.push(l);

            } else {
                float numb = (float) (Integer.parseInt(character));  // Si element dans la liste est une oprande , on le converte au float, puis on l'ajoute a la queue
                stackdbl.push(numb);

            }


        }


        return stackdbl.peek();

    }

    public List<String> getPostFixString(String s) {
        Stack<Character> stack = new Stack<>();
        List<String> postFixList = new ArrayList<>();
        boolean tracker = false; //tracker les operandes
        for (int i = 0; i < s.length(); i++) {
            char word = s.charAt(i);
            if (word == ' ') {
                continue; // Si l'utilisateur laisse un espace entre les elements de l'expression, le programme continue sa recherche
            }


            if (word == '(') { // on a ajoute '(' a la queue si word =='('
                stack.push(word);
                tracker = false;
            } else if (word == ')') {

                tracker = false;            /*  Parenthese fermé, on verifié si le premier(peek) element de la queue est '(' sinon chaque fois on pop un element et on l'ajoute dans la liste */
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else {
                        postFixList.add(stack.pop() + "");
                    }
                }
            } else if (word == '+' || word == '-' || word == '*' || word == '/' || word == '$') {
                tracker = false;
                if (stack.isEmpty()) {
                    stack.push(word);
                } else {
                    while (!stack.isEmpty() && prefOperator(stack.peek()) >= prefOperator(word)) { /* dans cette boucle on verifie entre deux opérateurs */
                        postFixList.add(stack.pop() + "");    /* si la condition est verifier on pop l'element  dans le peek*/
                    }
                    stack.push(word);
                }
            } else { // si word est  operande , on l'ajoute a la liste et tracker == true
                if (tracker) {
                    String lastNumber = postFixList.get(postFixList.size() - 1);
                    lastNumber += word;
                    postFixList.set(postFixList.size() - 1, lastNumber);
                } else
                    postFixList.add(word + "");
                tracker = true;
            }
        }
        while (!stack.isEmpty()) {
            postFixList.add(stack.pop() + "");
        }
        return postFixList;
    }

    public float calculate(String s) {
        List<String> postFixString = getPostFixString(s);
        return calculatingExpression(postFixString);
    }
}



