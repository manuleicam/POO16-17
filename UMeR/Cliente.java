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
        Coords x = new Coords(3,3);
        Coords y = new Coords(5,5);
        Date da = new Date(2000, 01, 01);
        Date db = new Date(2010, 01, 01);
        Date dc = new Date(2005, 01, 01);
        Date di = new Date(2003, 01, 01);
        Motorista m = new Motorista("ola","ola","ola","ola",da);
        Veiculo v = new Veiculo(2,"ola",2,3,3,x,3);
        
        Viagem via = new Viagem(1,this,x,y,10,2,3,m,v,da);
        Viagem via2 = new Viagem(2,this,x,y,10,2,3,m,v,db);
        Viagem via3 = new Viagem(3,this,x,y,10,2,3,m,v,dc);
        Viagem via4 = new Viagem(3,this,x,y,10,2,3,m,v,di);
        listaViagens.add(via);
        listaViagens.add(via2);
        listaViagens.add(via3);
        listaViagens.add(via4);
        for(Viagem viag : listaViagens){
            String viagem = viag.toString();
            System.out.println(viagem);
        }
    }

    public void addViagem(Viagem nova){
      
        this.listaViagens.add(nova);
        this.totalGasto += nova.getPrecoFinal();
    }


    public void adicionarGasto(int gasto){
        this.totalGasto += gasto; 
    }

}
