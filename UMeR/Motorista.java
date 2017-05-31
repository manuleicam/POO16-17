import java.util.*;
import java.time.LocalDate;

public class Motorista extends Actor
{
    private int grauCumpHorario; 
    private int classificacao;
    private int nrViagens;
    private int numKms;
    private Empresa empresa;
    private boolean estado; //true livre, false ocupado, mais facil
    private float desvioAcumulado;
    
    
    //BOB
    
   public Motorista()
   {
        super();
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.empresa=null;
        this.estado=true;
        this.desvioAcumulado=0;
   }

    public Motorista(String email, String nome, String password, String morada, LocalDate nascimento) {
        super(email,nome,password,morada,nascimento);
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.listaViagens = new ArrayList<Viagem>();
        this.desvioAcumulado = desvioAcumulado;
    }
    
    public Motorista(String email, String nome, String password, String morada, LocalDate nascimento, int grauCumpHorario, int classificacao, int nrViagens, int numKms, Empresa empresa, boolean estado, float desvioAcumulado) {
        super(email,nome,password,morada,nascimento);
        this.grauCumpHorario = grauCumpHorario;
        this.classificacao = classificacao;
        this.nrViagens = nrViagens;
        this.numKms = numKms;
        this.empresa = empresa;
        this.estado = estado;
        this.listaViagens = new ArrayList<Viagem>();
        this.desvioAcumulado = desvioAcumulado;
    }


   

    //GETS E SETS

    public int getGrauCumpHorario() {
        return grauCumpHorario;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public int getNrViagens() {
        return nrViagens;
    }

    public int getNumKms() {
        return numKms;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public boolean getEstado() {
        return estado;
    }

    public float getDesvioAcumulado() {
        return desvioAcumulado;
    }

    public void setGrauCumpHorario(int grauCumpHorario) {
        this.grauCumpHorario = grauCumpHorario;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public void setNrViagens(int nrViagens) {
        this.nrViagens = nrViagens;
    }

    public void setNumKms(int numKms) {
        this.numKms = numKms;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setDesvioAcumulado(float desvioAcumulado) {
        this.desvioAcumulado = desvioAcumulado;
    }
    
    
    //METODOS
    
     public void addViagem(Viagem nova){
      
      this.listaViagens.add(nova);
      this.numKms += nova.getDistancia();
      this.desvioAcumulado += (nova.getPrecoFinal() - nova.getPrecoAcordado());
      this.nrViagens++;
    }

  /*  public void adicionarKms (int kms){
        this.numKms += kms;
        this.nrViagens++;
   }*/
    
}
