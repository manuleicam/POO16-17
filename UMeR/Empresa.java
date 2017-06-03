import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;


public class Empresa extends Actor {
    
    public ArrayList<Actor> motoristas;
    public ArrayList<Veiculo> viaturas;

    
    //CONSTRUTOR
    public Empresa(){
        super();
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
    }
    
    public Empresa(String email, String nome, String password, String morada, LocalDate nascimento){
        super(email,nome,password,morada,nascimento);
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
    }
        
    public Empresa(Empresa e){
        super(e.getEmail(), e.getNome(), e.getPassword(), e.getMorada(), e.getNascimento());
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
    }
    
    
     //GET E SET

    public ArrayList<Actor> getMotoristas() {
        return motoristas;
    }

    public ArrayList<Veiculo> getViaturas() {
        return viaturas;
    }


    public void setMotoristas(ArrayList<Actor> motoristas) {
        this.motoristas = motoristas;
    }

    public void setViaturas(ArrayList<Veiculo> viaturas) {
        this.viaturas = viaturas;
    }

    
    
    //EQUALS 2STRING
    
    public String toStringParaMotoristas(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa:");
        sb.append("\n Nome: ");
        sb.append(this.getNome());
        sb.append("\n Morada: ");
        sb.append(this.getMorada());
        sb.append("\n DataCriação: ");
        sb.append(this.getNascimento());
        return sb.toString();
    }


    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa:");
        sb.append("\nNome: ");
        sb.append(this.getNome());
        sb.append("\nMorada: ");
        sb.append(this.getMorada());
        sb.append("\nDataCriação: ");
        sb.append(this.getNascimento());
        for(Actor m : motoristas){
            Motorista m2 = (Motorista) m;
            sb.append(m2.toStringParaEmpresa());
        }
        sb.append("\nViaturas: ");
        sb.append(this.viaturas);
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.motoristas, other.motoristas)) {
            return false;
        }
        if (!Objects.equals(this.viaturas, other.viaturas)) {
            return false;
        }
        return true;
    }
    
    
    
    
    // METODOS
    public void addVeiculoLigeiros(Ligeiros v){
        this.viaturas.add(v);
    }
    public void addVeiculoMotos(Motos v){
        this.viaturas.add(v);
    }
    public void addVeiculoNoveLugares(NoveLugares v){
        this.viaturas.add(v);
    }
    
    public Veiculo getVeiculo(String matricula){
        Veiculo v;
        System.out.println(matricula);
        for(Veiculo ve : this.viaturas){
            System.out.println(ve.toString());
        }
        for(Veiculo aux : this.viaturas){
            if(aux.getMatricula().equals(matricula)) { v = aux; return v;}
            else System.out.println("SSS");
        }
        return null;
    }
    
}
