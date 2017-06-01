
import java.util.Objects;
import java.util.ArrayList;


public class Veiculo {

    private String matricula;
    private int velMediaKM; //o que caralho é uma velocidade média por kilomtero, velocidade media ou uma lista de velocidades médias por cada km?
    private int precoPorKM;
    private int fiabilidade;
    private Coords posicao;
    private int totalFaturado;
    public ArrayList<Viagem> listaViagens = new ArrayList<Viagem>();
    public Motorista motorista = new Motorista();

    public Veiculo(){
        this.matricula = "";
        this.velMediaKM = 0;
        this.precoPorKM = 0;
        this.fiabilidade = 0;
        this.posicao = new Coords();;
        this.totalFaturado = 0;
        this.motorista = null;
    }
    public Veiculo(String matricula, int velMediaKM, int precoPorKM, Coords posicao) {
        this.matricula = matricula;
        this.velMediaKM = velMediaKM;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 0;
        this.posicao = posicao;
        this.totalFaturado = 0;
        this.motorista = null;
    }
    public Veiculo(String matricula, int velMediaKM, int precoPorKM, Coords posicao, Actor m) {
        this.matricula = matricula;
        this.velMediaKM = velMediaKM;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 0;
        this.posicao = posicao;
        this.totalFaturado = 0;
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
        this.listaViagens = v.getlistaViagens();
    }

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
    
    public Motorista getMotorista(){
        return this.motorista;
    }
    
    public void setMotorista(Motorista m){
        this.motorista = m;
    }

    public ArrayList getlistaViagens() {
        return listaViagens;
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
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.posicao, other.posicao)) {
            return false;
        }
        if (!Objects.equals(this.listaViagens, other.listaViagens)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Veiculo{" + "matricula=" + matricula + ", velMediaKM=" + velMediaKM + ", precoPorKM=" + precoPorKM + ", fiabilidade=" + fiabilidade + ", posicao=" + posicao + ", totalFaturado=" + totalFaturado + ", listaViagens=" + listaViagens + '}';
    }
    
    public Veiculo clone(){
        return new Veiculo(this);
    }

    public void addViagem(Viagem nova){

        this.listaViagens.add(nova);
        this.totalFaturado += nova.getPrecoFinal();

    }
    
    
}
