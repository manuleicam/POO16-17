 import java.util.Date;

 public class Actor 
 {
 	private String email;
    private String nome;
    private String password;
    private String morada;
    private Date nascimento;


   public Actor(){
   		this.email = "";
   		this.nome = "";
   		this.password = "";
   		this.morada = "";
   		this.nascimento = new Date();
   }

   public Actor(String email, String nome, String password, String morada, Date nascimento){
   	  this.email = email;
   		this.nome = nome;
   		this.password = password;
   		this.morada = morada;
   		this.nascimento = nascimento;
   }

   public boolean logIn (String pass){
   		if (this.password.equals(pass))
   			return true;
   		else 
   			return false;

   }
}