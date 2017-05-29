import java.util.*;

public class Motorista extends Actor
{
    private int grauCumpHorario; 
    private int classificacao;
    private int nrViagens;
    private int numKms;
    private Empresa empresa;
    private Boolean estado; //true livre, false ocupado, mais facil

   public Motorista()
   {
        super();
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.listaViagens = new ArrayList<Viagem>();
        
   }


    public Motorista(String email, String nome, String password, String morada, Date nascimento)
   {
        super(email,nome, password, morada, nascimento);
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.listaViagens = new ArrayList<Viagem>();
   }


   public void addViagem(Viagem nova){
      
      this.listaViagens.add(nova);
      this.numKms += nova.getDistancia();

    }

    public void adicionarKms (int kms){
        this.numKms += kms;
        this.nrViagens++;
   }

}
