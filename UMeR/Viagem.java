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
    
    public Viagem(Viagem v){
        this.cliente = v.getCliente();
        this.inicio = v.getInicio();
        this.fim = v.getFim();
        this.distancia = v.getDistancia();
        this.precoAcordado = v.getPrecoAcordado();
        this.precoFinal = v.getPrecoFinal();
        this.tempoEstimado = v.getTempoEstimado();
        this.tempoFinal = v.getTempoFinal();
        this.condutor = v.getCondutor();
        this.veiculo = v.getVeiculo();
        this.data = v.getData();
        this.nota = v.getNota();
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

    public Viagem clone(){
        return new Viagem(this);
    }
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.cliente);
        sb.append(this.condutor.toStringParaEmpresa());
        sb.append("\nVeiculo: ");
        sb.append(" " + this.veiculo.getMatricula());
        sb.append("\n Data da viagem: ");
        sb.append(this.data);
        sb.append("\n Inicio da viagem: ");
        sb.append(this.inicio);
        sb.append(", Fim da viagem: ");
        sb.append(this.fim);
        sb.append(", Distância total da viagem: ");
        sb.append(this.distancia);
        sb.append("\n Tempo estimado de viagem: ");
        sb.append(this.tempoEstimado);
        sb.append(", Tempo final de viagem: ");
        sb.append(this.tempoFinal);
        sb.append("\n Preco Acordado Inicialmente: ");
        sb.append(this.precoAcordado);
        sb.append(", Preco final: ");
        sb.append(this.precoFinal);
        sb.append("\n Nota dada pelo cliente: ");
        sb.append(this.nota);
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

 