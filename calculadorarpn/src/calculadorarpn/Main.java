package calculadorarpn;

import java.util.Stack;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) throws IOException {
		Stack<String> stack = new Stack<String>();

		File arquivo = new File("Calc1.stk");
		byte[] bytes = Files.readAllBytes(arquivo.toPath());
		String expressaorpn[] = new String(bytes).split("\n");
		int contador = 1;
		String saida;

	
		int i = 0;

		while (i < expressaorpn.length) {
			if (!expressaorpn[i].equals("=")) {
				
				boolean isADigit = expressaorpn[i].matches("[0-9]*"); 
				if(isADigit) {
					stack.push(expressaorpn[i]);
				}else{

					if ("-+/*".contains(expressaorpn[i])) {
						
						BigDecimal direita = new BigDecimal(stack.pop());
						BigDecimal esquerda = new BigDecimal(stack.pop());

						switch (expressaorpn[i]) {
						case "+":
							stack.push(esquerda.add(direita).toString());
							return;
						case "-":
							stack.push(esquerda.subtract(direita).toString());
							return;
						case "*":
							stack.push(esquerda.multiply(direita).toString());
							return;
						case "/":
							stack.push(esquerda.divide(direita).toString());
							return;
						}
					}

				}

			} else {
				saida = stack.pop();
				System.out.println("O resultado da expressao" + contador + " no RPN eh: " + saida);
				contador++;
			}

			i++;
		}

		saida = stack.pop();
		System.out.println("O resultado da expressao" + contador + " no RPN eh: " + saida);
	}
}
