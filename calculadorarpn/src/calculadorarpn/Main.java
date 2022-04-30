package calculadorarpn;
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {     
        File RPN = new File("C:/Users/LeydsonMaycksonFerra/Documents/Compiladores/calculadorarpn/calculadoraRPN-Compiladores/calculadorarpn/Calc1.stk");
        Scanner in = new Scanner(RPN); 
        Stack<Integer> pile = new Stack<Integer>();
        int result=0;
        
        List<Token> tokens=new ArrayList<Token>();  

        while (in.hasNext()) {
                String line = in.nextLine();
                
                if (isNumeric(line)) {
                    tokens.add(new Token(TokenType.NUM,line));
                    int num = Integer.parseInt(line) ;
                    pile.push(num);
                }
                else {
                    if (line.equals("+")) {
                        tokens.add(new Token(TokenType.PLUS,line));
                        int num2 = pile.pop();
                        int num1 = pile.pop();
                        result = num1+num2;
                    }
                    else if (line.equals("-")) {
                        tokens.add(new Token(TokenType.MINUS,line));
                        int num2 = pile.pop();
                        int num1 = pile.pop();
                        result = num1-num2;
                    }
                    else if (line.equals("*")) {
                        tokens.add(new Token(TokenType.STAR,line));
                        int num2 = pile.pop();
                        int num1 = pile.pop();
                        result = num1*num2;
                    }
                    else if (line.equals("/")) {
                        tokens.add(new Token(TokenType.SLASH,line));
                        int num2 = pile.pop();
                        int num1 = pile.pop();
                        result = num1/num2;
                    }
                    else {
                        throw new Exception("Unexpected character: " + line); 
                    }
                    pile.push(result);
                }
        }
        System.out.println(pile.pop());
        System.out.println(tokens);
    } 

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}
