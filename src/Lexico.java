import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;

public class Lexico {
		
	 public static enum TokenType {	
		COMENTARIOMULT ("^((/*))"),
	 	COMENTARIO("(("+(char)47+(char)47+")+[^\n]+)"),
	 	SIMBOLOS("[;.!,\'}{)(]"),
	 	NUMERODECIMAL("(-?[0-9]+[.]+[0-9]+)"),
	 	NUMERO("-?[0-9]+"),
	 	STRING("([\"]+?[a-zA-Z0-9. \n]+[\"])"),
	 	ESPACOBRANCO("[ \t\f\r\n]"),
	 	OPERADORESLOGICOS("(== )|(&& )|(>= )|(<= )|(> )|(< )|[|]+[|]+[ ]"),
	 	OPERADORMATEMATICO("[*/+-=]"),
	 	COMPARACAOEREPETICAO("(if )|(if\n)|(else )|(else\n)|(for )|(while )|(foreach)"),
	 	TIPO("(int )|(float )|(string )|(double )"),
	 	VARIAVEL("-?[a-zA-Z0-9.]+"),
	   	TIPOSTRING("([\"]+?[a-zA-Z0-9.]+[\"])");

	        public final String pattern;

	        private TokenType(String pattern) {
	            this.pattern = pattern;
	        }
	    }

	    public static class Token {
	        public TokenType tipo;
	        public String data;

	        public Token(TokenType tipo, String data) {
	            this.tipo = tipo;
	            this.data = data;
	        }
	       
	        @Override
	        public String toString() {
	            return String.format("(%s %s)", tipo.name(), data);
	        }
	        
	       
	    }
	    
	    public static ArrayList<Token> lex(String input) {
	        
	        ArrayList<Token> tokens = new ArrayList<Token>();
 
	        StringBuffer tokenPatternsBuffer = new StringBuffer();
	        for (TokenType tokenType : TokenType.values())
	            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
	            Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
	            
	           
	            Matcher matcher = tokenPatterns.matcher(input);
	            while (matcher.find()) {
	            if (matcher.group(TokenType.COMENTARIOMULT.name()) != null) {
		                 tokens.add(new Token(TokenType.COMENTARIOMULT, matcher.group(TokenType.COMENTARIOMULT.name())));
		                  continue;  
		        }
	            else if (matcher.group(TokenType.COMENTARIO.name()) != null) {
                    tokens.add(new Token(TokenType.COMENTARIO, matcher.group(TokenType.COMENTARIO.name())));
                    continue;
                }
	            else if (matcher.group(TokenType.STRING.name()) != null) {
		                 tokens.add(new Token(TokenType.STRING, matcher.group(TokenType.STRING.name())));
		                  continue;  
		         } 
	            else if (matcher.group(TokenType.NUMERODECIMAL.name()) != null) {
	                    tokens.add(new Token(TokenType.NUMERODECIMAL, matcher.group(TokenType.NUMERODECIMAL.name())));
	                    continue;
	            }
	            else if (matcher.group(TokenType.NUMERO.name()) != null) {
	                    tokens.add(new Token(TokenType.NUMERO, matcher.group(TokenType.NUMERO.name())));
	                    continue;
	                } else if (matcher.group(TokenType.OPERADORMATEMATICO.name()) != null) {
	                    tokens.add(new Token(TokenType.OPERADORMATEMATICO, matcher.group(TokenType.OPERADORMATEMATICO.name())));
	                    continue;
	                    
	                }
	                else if (matcher.group(TokenType.SIMBOLOS.name()) != null) {
	                   // tokens.add(new Token(TokenType.SIMBOLOS, matcher.group(TokenType.SIMBOLOS.name())));
	                    continue;
	                }else if (matcher.group(TokenType.OPERADORESLOGICOS.name()) != null) {
	                    tokens.add(new Token(TokenType.OPERADORESLOGICOS, matcher.group(TokenType.OPERADORESLOGICOS.name())));
	                    continue;
	                }else if (matcher.group(TokenType.VARIAVEL.name()) != null) {
	                    tokens.add(new Token(TokenType.VARIAVEL, matcher.group(TokenType.VARIAVEL.name())));
	                    continue;
	                }else if (matcher.group(TokenType.TIPO.name()) != null) {
	                    tokens.add(new Token(TokenType.TIPO, matcher.group(TokenType.TIPO.name())));
	                    continue;
	                }
	                else if (matcher.group(TokenType.TIPOSTRING.name()) != null) {
	                   //tokens.add(new Token(TokenType.TIPTIPOSTRINGO, matcher.group(TokenType.TIPOSTRING.name())));
	                    continue;
	                }else if (matcher.group(TokenType.COMPARACAOEREPETICAO.name()) != null) {
	                    tokens.add(new Token(TokenType.COMPARACAOEREPETICAO, matcher.group(TokenType.COMPARACAOEREPETICAO.name())));
	                    continue;
	                } 
	                else if (matcher.group(TokenType.ESPACOBRANCO.name()) != null)
	                    continue;
	            }
	            
	            
	        return tokens;
	    }

	    public void analisarTextField(JTextArea inputText, JTextArea logText,JTextArea logArrayText){
	    
	        String input = inputText.getText();
	        String saidaLog = "";
		    
	        // Create tokens and print them
	        ArrayList<Token> tokens = lex(input);
	        ArrayList<ArrayList<String>> analiseLexica = new ArrayList<ArrayList<String>>();	        
	     
	        int i = 0;
	        for (Token token : tokens){
	  	          saidaLog += token.toString()+"\n";
	  	          analiseLexica.add(new ArrayList<String>());
	  	          analiseLexica.get(i).add(token.tipo.name().toString());
	  	          analiseLexica.get(i).add(token.toString().substring(token.tipo.name().toString().length()+2, token.toString().length()-1));
	  	          i++;    
	        }
	       
	       // System.out.println(analiseLexica);
	        logArrayText.setText(analiseLexica.toString());
	        logText.setText(saidaLog); 

	    }
}
