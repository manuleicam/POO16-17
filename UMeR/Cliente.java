import java.util.*;

public class Cliente extends Actor
{
	private int totalGasto = 0;

	public Cliente()
	{
   		super();
	}
   


    public Cliente(String email, String nome, String password, String morada, Date nascimento)
    {
		super(email, nome, password, morada, nascimento);
    }

    public void addViagem(Viagem nova){
      
      	this.listaViagens.add(nova);
      	this.totalGasto += nova.getPrecoFinal();
    }


    public void adicionarGasto(int gasto){
    	this.totalGasto += gasto; 
    }

}
