
import java.util.Objects;
import java.util.ArrayList;
import java.io.Serializable;


public class Veiculo implements Serializable {

    private String matricula;
    private int velMediaKM; //o que caralho é uma velocidade média por kilomtero, velocidade media ou uma lista de velocidades médias por cada km?
    private int precoPorKM;
    private int fiabilidade;
    private Coords posicao;
    private int totalFaturado;
    private boolean estado; // true o condutor está a usar o veiculo
    public ArrayList<Viagem> listaViagens = new ArrayList<Viagem>();
    public Motorista motorista = new Motorista();

    
    //CONSTRUTOR
    
    public Veiculo(){
        this.matricula = "";
        this.velMediaKM = 0;
        this.precoPorKM = 0;
        this.fiabilidade = 0;
        this.posicao = new Coords();
        this.totalFaturado = 0;
        this.estado = false;
        this.listaViagens = new ArrayList<Viagem>();
        this.motorista = null;
    }
    public Veiculo(String matricula, int velMediaKM, int precoPorKM, Coords posicao) {
        this.matricula = matricula;
        this.velMediaKM = velMediaKM;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 0;
        this.posicao = posicao;
        this.estado = false;
        this.totalFaturado = 0;
        this.listaViagens = new ArrayList<Viagem>();
        this.motorista = null;
    }
    public Veiculo(String matricula, int velMediaKM, int precoPorKM, Coords posicao, Actor m) {
        this.matricula = matricula;
        this.velMediaKM = velMediaKM;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 0;
        this.posicao = posicao;
        this.totalFaturado = 0;
        this.estado = true;
        this.listaViagens = new ArrayList<Viagem>();
        this.motorista = (Motorista) m;
    }
    public Veiculo(Veiculo v){
        this.matricula = v.getMatricula();
        this.velMediaKM = v.getVelMediaKM();
        this.precoPorKM = v.getPrecoPorKM();
        this.fiabilidade = v.getFiabilidade();
        this.posicao = v.getPosicao();
        this.totalFaturado = v.getTotalFaturado();
        this.motorista = v.getMotorista();
        this.estado = v.getEstado();
        this.listaViagens = v.getListaViagens();
    }
    //GET E SET

    public String getMatricula() {
        return matricula;
    }

    public int getVelMediaKM() {
        return velMediaKM;
    }

    public int getPrecoPorKM() {
        return precoPorKM;
    }

    public int getFiabilidade() {
        return fiabilidade;
    }

    public Coords getPosicao() {
        return posicao;
    }

    public int getTotalFaturado() {
        return totalFaturado;
    }

    public boolean getEstado() {
        return estado;
    }

    public ArrayList<Viagem> getListaViagens() {
        return listaViagens;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setVelMediaKM(int velMediaKM) {
        this.velMediaKM = velMediaKM;
    }

    public void setPrecoPorKM(int precoPorKM) {
        this.precoPorKM = precoPorKM;
    }

    public void setFiabilidade(int fiabilidade) {
        this.fiabilidade = fiabilidade;
    }

    public void setPosicao(Coords posicao) {
        this.posicao = posicao;
    }

    public void setTotalFaturado(int totalFaturado) {
        this.totalFaturado = totalFaturado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setListaViagens(ArrayList<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
    
    public void removeMotorista(){
        this.motorista = null;
    }
   
    //EQUALTS 2STRING CLONE
    
    public String toStringCliente(){
        StringBuilder sb = new StringBuilder();
        sb.append("Veiculo:");
        sb.append("\nMatricula: ");
        sb.append(this.matricula);
        sb.append("\nPosicao onde se encontra no momento: ");
        sb.append(this.posicao);
        if (this.motorista!= null) sb.append(this.motorista.toStringParaCliente());
        else sb.append("\nSem Motorista de momento");
        return sb.toString();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Veiculo:");
        sb.append("\nMatricula: ");
        sb.append(this.matricula);
        sb.append("\nVelocidade média: ");
        sb.append(this.velMediaKM);
        sb.append("\nPreço a pagar por KM: ");
        sb.append(this.precoPorKM);
        sb.append("\nFiabilidade: ");
        sb.append(this.fiabilidade);
        sb.append("\nPosicao onde se encontra no momento: ");
        sb.append(this.posicao);
        sb.append("\nTotal já facturado: ");
        sb.append(this.totalFaturado);
        sb.append("\nLista das Viagens já realizadas: ");
        sb.append(this.listaViagens);
        sb.append("\nEstado: ");
        if (this.estado == false) sb.append("Livre");
        else sb.append("Ocupado");
        if (this.motorista!= null) sb.append(this.motorista.toStringParaEmpresa());
        else sb.append("\nSem Motorista de momento");
        return sb.toString();
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (this.velMediaKM != other.velMediaKM) {
            return false;
        }
        if (this.precoPorKM != other.precoPorKM) {
            return false;
        }
        if (this.fiabilidade != other.fiabilidade) {
            return false;
        }
        if (this.totalFaturado != other.totalFaturado) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.posicao, other.posicao)) {
            return false;
        }
        if (!Objects.equals(this.listaViagens, other.listaViagens)) {
            return false;
        }
        if (!Objects.equals(this.motorista, other.motorista)) {
            return false;
        }
        return true;
    }
 
  
    public Veiculo clone(){
        return new Veiculo(this);
    }

    
    
    public void addViagem(Viagem nova){

        this.listaViagens.add(nova);
        this.totalFaturado += nova.getPrecoFinal();
        this.posicao = nova.getFim();

    }
    
    
}
