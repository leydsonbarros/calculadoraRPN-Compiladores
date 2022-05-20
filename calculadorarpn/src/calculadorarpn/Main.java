package calculadorarpn;
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {     
        File RPN = new File("C:/Users/LeydsonMaycksonFerra/Documents/Compiladores/calculadorarpn/calculadoraRPN-Compiladores/calculadorarpn/Calc1.stk");
        Scanner in = new Scanner(RPN); 
        List<Token> tokens=new ArrayList<Token>();
        Stack<Integer> pile = new Stack<Integer>();
        Token token = new Token(null,null);
        int result=0, i=0, num1=0, num2=0;        
          

        while (in.hasNext()) {
            String line = in.nextLine();
                
            if (isNumeric(line)) {
                tokens.add(new Token(TokenType.NUM,line));
            }
            else {
                if (line.equals("+")) {
                    tokens.add(new Token(TokenType.PLUS,line));
                }
                else if (line.equals("-")) {
                    tokens.add(new Token(TokenType.MINUS,line));
                }
                else if (line.equals("*")) {
                    tokens.add(new Token(TokenType.STAR,line));
                }
                else if (line.equals("/")) {
                    tokens.add(new Token(TokenType.SLASH,line));
                }
                else {
                    throw new Exception("Unexpected character: " + line); 
                }
            }
        }

        while (i < tokens.size()) {
            token = tokens.get(i);

            if (token.type == TokenType.NUM) {;
                pile.push(Integer.parseInt(token.lexeme));
            }
            else {
                if (token.type == TokenType.PLUS) {
                    num2 = pile.pop();
                    num1 = pile.pop();
                    result = num1+num2;
                }
                if (token.type == TokenType.MINUS) {
                    num2 = pile.pop();
                    num1 = pile.pop();
                    result = num1-num2;
                }
                if (token.type == TokenType.STAR) {
                    num2 = pile.pop();
                    num1 = pile.pop();
                    result = num1*num2;
                }
                if (token.type == TokenType.SLASH) {
                    num2 = pile.pop();
                    num1 = pile.pop();
                    result = num1/num2;
                }
                pile.push(result);
            }
            i++;
        }
         System.out.println(pile.pop());
        System.out.println(tokens);
    } 

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}
