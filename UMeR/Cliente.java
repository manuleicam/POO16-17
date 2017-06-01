import java.util.*;
import java.time.LocalDate;

public class Cliente extends Actor
{
    private int totalGasto = 0;

    public Cliente(){
        super();  
    }
   
 

    public Cliente(String email, String nome, String password, String morada, LocalDate nascimento)
    {
        super(email, nome, password, morada, nascimento);
        
        Coords x = new Coords(3,3); //mete 4 viagens num cliente
        Coords y = new Coords(5,5);
        
        LocalDate da = LocalDate.of(2000,01,01);
        LocalDate db = LocalDate.of(2010, 01, 01);
        LocalDate dc = LocalDate.of(2005, 01, 01);
        LocalDate di = LocalDate.of(2003, 01, 01);
        
        Motorista m = new Motorista("ola","ola","ola","ola",da);
        Veiculo v = new Veiculo("ola",2,3,x);
        
        Viagem via = new Viagem(1,this,x,y,10,2,3,m,v,da);
        Viagem via2 = new Viagem(2,this,x,y,10,2,3,m,v,db);
        Viagem via3 = new Viagem(3,this,x,y,10,2,3,m,v,dc);
        Viagem via4 = new Viagem(3,this,x,y,10,2,3,m,v,di);
        listaViagens.add(via);
        listaViagens.add(via2);
        listaViagens.add(via3);
        listaViagens.add(via4);
        
    }

    public void addViagem(Viagem nova){
      
        this.listaViagens.add(nova);
        this.totalGasto += nova.getPrecoFinal();
    }


    public void adicionarGasto(int gasto){
        this.totalGasto += gasto; 
    }

}
