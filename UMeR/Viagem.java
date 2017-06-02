import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;


public class Viagem
{
    private Cliente cliente;
    private Coords inicio;
    private Coords fim;
    private Double distancia;
    private Double precoAcordado;
    private Double precoFinal;
    private Double tempoEstimado;
    private Double tempoFinal;
    private Motorista condutor;
    private Veiculo veiculo;
    private LocalDate data;
    private int nota;

    
    //CONTRUTOR

    public Viagem(Cliente cliente, Coords inicio, Coords fim, Double distancia, Double precoAcordado, Double precoFinal, Double tempoEstimado, Double tempoFinal, Motorista condutor, Veiculo veiculo, LocalDate data, int nota) {
        this.cliente = cliente;
        this.inicio = inicio;
        this.fim = fim;
        this.distancia = distancia;
        this.precoAcordado = precoAcordado;
        this.precoFinal = precoFinal;
        this.tempoEstimado = tempoEstimado;
        this.tempoFinal = tempoFinal;
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.data = data;
        this.nota = nota;
    }

   

    //GETS E SETS

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setInicio(Coords inicio) {
        this.inicio = inicio;
    }

    public void setFim(Coords fim) {
        this.fim = fim;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public void setPrecoAcordado(Double precoAcordado) {
        this.precoAcordado = precoAcordado;
    }

    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public void setTempoFinal(Double tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public void setCondutor(Motorista condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Coords getInicio() {
        return inicio;
    }

    public Coords getFim() {
        return fim;
    }

    public Double getDistancia() {
        return distancia;
    }

    public Double getPrecoAcordado() {
        return precoAcordado;
    }

    public Double getPrecoFinal() {
        return precoFinal;
    }

    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    public Double getTempoFinal() {
        return tempoFinal;
    }

    public Motorista getCondutor() {
        return condutor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getNota() {
        return nota;
    }

    
    
    
    
    
    
    
   //2STRING EQUALCS CLONE

    
    
    
    @Override
    public String toString() {
        return "Viagem{" + "cliente=" + cliente + ", inicio=" + inicio + ", fim=" + fim + ", distancia=" + distancia + ", precoAcordado=" + precoAcordado + ", precoFinal=" + precoFinal + ", tempoEstimado=" + tempoEstimado + ", tempoFinal=" + tempoFinal + ", condutor=" + condutor + ", veiculo=" + veiculo + ", data=" + data + ", nota=" + nota + '}';
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
        final Viagem other = (Viagem) obj;
        if (this.nota != other.nota) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fim, other.fim)) {
            return false;
        }
        if (!Objects.equals(this.distancia, other.distancia)) {
            return false;
        }
        if (!Objects.equals(this.precoAcordado, other.precoAcordado)) {
            return false;
        }
        if (!Objects.equals(this.precoFinal, other.precoFinal)) {
            return false;
        }
        if (!Objects.equals(this.tempoEstimado, other.tempoEstimado)) {
            return false;
        }
        if (!Objects.equals(this.tempoFinal, other.tempoFinal)) {
            return false;
        }
        if (!Objects.equals(this.condutor, other.condutor)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
   
    
    
    
    
}

 