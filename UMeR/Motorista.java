import java.util.*;

public class Motorista extends Actor
{
    private int grauCumpHorario; 
    private int classificacao;
    private int nrViagens;
    private int numKms;
    private Boolean estado; //true livre, false ocupado, mais facil
    private ArrayList<Viagem> viagens;//Fazer uma lista central (hash) e guarda so os id's da viagem aqui

   public Motorista()
   {
        super();
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.viagens = new ArrayList<Viagem>();
        
   }


    public Motorista(String email, String nome, String password, String morada, Date nascimento)
   {
        super(email,nome, password, morada, nascimento);
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.viagens = new ArrayList<Viagem>();
   }


   
}
