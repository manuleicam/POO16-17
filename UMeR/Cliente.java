import java.util.*;

public class Cliente extends Actor
{
	private int totalGasto;

   public Cliente()
   {
   		super();
   }
   

   public Cliente(String email, String nome, String password, String morada, Date nascimento)
   {
		super(email, nome, password, morada, nascimento);
   }


}
