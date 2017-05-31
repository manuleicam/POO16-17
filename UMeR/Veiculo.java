
import java.util.Objects;
import java.util.ArrayList;


public class Veiculo {

    private int id;
    private String matricula;
    private int velMediaKM; //o que caralho é uma velocidade média por kilomtero, velocidade media ou uma lista de velocidades médias por cada km?
    private int precoPorKM;
    private int fiabilidade;
    private Coords posicao;
    private int totalFaturado;
    public ArrayList<Viagem> listaViagens = new ArrayList<Viagem>();
    public Motorista motorista;

    public Veiculo(){
        this.id = 0;
        this.matricula = "";
        this.velMediaKM = 0;
        this.precoPorKM = 0;
        this.fiabilidade = 0;
        this.posicao = new Coords();;
        this.totalFaturado = 0;
        this.motorista = null;
    }
    public Veiculo(int id, String matricula, int velMediaKM, int precoPorKM, Coords posicao) {
        this.id = id;
        this.matricula = matricula;
        this.velMediaKM = velMediaKM;
        this.precoPorKM = precoPorKM;
        this.fiabilidade = 0;
        this.posicao = posicao;
        this.totalFaturado = 0;
    }

    public int getId() {
        return id;
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

    public ArrayList getlistaViagens() {
        return listaViagens;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setlistaViagens(ArrayList listaViagens) {
        this.listaViagens = listaViagens;
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
        if (this.id != other.id) {
            return false;
        }
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
        return "Veiculo{" + "id=" + id + ", matricula=" + matricula + ", velMediaKM=" + velMediaKM + ", precoPorKM=" + precoPorKM + ", fiabilidade=" + fiabilidade + ", posicao=" + posicao + ", totalFaturado=" + totalFaturado + ", listaViagens=" + listaViagens + '}';
    }

    public void addViagem(Viagem nova){

        this.listaViagens.add(nova);
        this.totalFaturado += nova.getPrecoFinal();

    }
    
    
}
