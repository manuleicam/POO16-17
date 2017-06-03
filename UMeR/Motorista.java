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
    private Double desvioAcumulado;
    
    
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
        this.desvioAcumulado=0.0;
   }
   
   public Motorista(Motorista m){
       super(m.getEmail(), m.getNome(), m.getPassword(), m.getMorada(), m.getNascimento());
       this.grauCumpHorario = m.getGrauCumpHorario();
       this.classificacao = m.getClassificacao();
       this.nrViagens = m.getNrViagens();
       this.numKms = m.getNumKms();
       this.estado = m.getEstado();
       this.listaViagens = m.getListaViagens();
       this.desvioAcumulado = m.getDesvioAcumulado();
       this.empresa = m.getEmpresa();
   }

    public Motorista(String email, String nome, String password, String morada, LocalDate nascimento) {
        super(email,nome,password,morada,nascimento);
        this.grauCumpHorario=0;
        this.classificacao=0;
        this.nrViagens=0;
        this.numKms=0;
        this.estado=true;
        this.listaViagens = new ArrayList<Viagem>();
        this.desvioAcumulado = 0.0;
        this.empresa = null;
    }
    
    public Motorista(String email, String nome, String password, String morada, LocalDate nascimento, int grauCumpHorario, int classificacao, int nrViagens, int numKms, Empresa empresa, boolean estado, Double desvioAcumulado) {
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

    public Double getDesvioAcumulado() {
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

    public void setDesvioAcumulado(Double desvioAcumulado) {
        this.desvioAcumulado = desvioAcumulado;
    }
    
    
    //METODOS
    
     public void addViagem(Viagem nova){
      
      this.listaViagens.add(nova);
      this.numKms += nova.getDistancia();
      this.desvioAcumulado += (nova.getPrecoFinal() - nova.getPrecoAcordado());
      this.nrViagens++;
    }
    
    public void trocaEstado(){
        if (this.estado == false) setEstado(true);
        else setEstado(false);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Motorista:");
        sb.append("\nEmail: ");
        sb.append(this.getEmail());
        sb.append("\nNome: ");
        sb.append(this.getNome());
        sb.append("\nMorada: ");
        sb.append(this.getMorada());
        sb.append("\nDataNascimento: ");
        sb.append(this.getNascimento());
        sb.append("\nPotuação de cumprir horários: ");
        sb.append(this.grauCumpHorario);
        sb.append(",");
        sb.append(" Classificação: ");
        sb.append(this.classificacao);
        sb.append(",");
        sb.append(" Estado: ");
        if(this.estado == true) sb.append("Livre,");
        else sb.append("Ocupado,");
        sb.append(" Desvio entre valor acordado e valor final: ");
        sb.append(this.desvioAcumulado);
        sb.append("\n");
        if(this.empresa == null) sb.append("Sem Empresa");
        else sb.append(this.empresa.toStringParaMotoristas());
        return sb.toString();
    }
    
    public String toStringParaEmpresa(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nMotorista:");
        sb.append("\n Email: ");
        sb.append(this.getEmail());
        sb.append("\n Nome: ");
        sb.append(this.getNome());
        sb.append("\n Morada: ");
        sb.append(this.getMorada());
        sb.append("\n DataNascimento: ");
        sb.append(this.getNascimento());
        sb.append("\n Potuação de cumprir horários: ");
        sb.append(this.grauCumpHorario);
        sb.append(",");
        sb.append(" Classificação: ");
        sb.append(this.classificacao);
        sb.append(",");
        sb.append(" Estado: ");
        if(this.estado == true) sb.append("Livre,");
        else sb.append("Ocupado,");
        return sb.toString();
    }
    
    public Motorista Clone(){
        return new Motorista(this);
    }


  /*  public void adicionarKms (int kms){
        this.numKms += kms;
        this.nrViagens++;
   }*/
    
}
