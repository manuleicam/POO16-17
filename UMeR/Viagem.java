import java.util.*;
/**
 * Write a description of class Viagem here.
 *
 * @author My name
 * @version 1
 */
public class Viagem
{
    private int id;
    private Cliente cliente;
    private Coords inicio;
    private Coords fim;
    private int distancia;
    private int precoAcordado;
    private int precoFinal;
    private Motorista condutor;
    private Veiculo veiculo;
    private Date data;

    public Viagem(int id, Cliente cliente, Coords inicio, Coords fim, int distancia, int precoAcordado, int precoFinal, Motorista condutor, Veiculo veiculo, Date data) {
        this.id = id;
        this.cliente = cliente;
        this.inicio = inicio;
        this.fim = fim;
        this.distancia = distancia;
        this.precoAcordado = precoAcordado;
        this.precoFinal = precoFinal;
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Coords getInicio() {
        return inicio;
    }

    public Coords getFim() {
        return fim;
    }

    public int getPrecoFinal() {
        return precoFinal;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getPrecoAcordado() {
        return precoAcordado;
    }

    public Motorista getCondutor() {
        return condutor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Date getData() {
        return data;
    }
    
     public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setInicio(Coords inicio) {
        this.inicio = inicio;
    }

    public void setFim(Coords fim) {
        this.fim = fim;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setPrecoAcordado(int precoAcordado) {
        this.precoAcordado = precoAcordado;
    }
    
    public void setPrecoFinal(int precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setCondutor(Motorista condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Viagem{" + "id=" + id + ", cliente=" + cliente + ", inicio=" + inicio + ", fim=" + fim + ", distancia=" + distancia + ", precoAcordado=" + precoAcordado + ", precoFinal=" + precoFinal + ", condutor=" + condutor + ", veiculo=" + veiculo + ", data=" + data + '}';
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
        if (this.id != other.id) {
            return false;
        }
        if (this.distancia != other.distancia) {
            return false;
        }
        if (this.precoAcordado != other.precoAcordado) {
            return false;
        }
        if (this.precoFinal != other.precoFinal) {
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

 