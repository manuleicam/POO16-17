import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Empresa extends Actor {
    
    public ArrayList<Actor> motoristas;
    public ArrayList<Veiculo> viaturas;
    public ArrayList<Integer> viagens;
    
    public Empresa(){
        super();
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }
    
    public Empresa(String email, String nome, String password, String morada, LocalDate nascimento){
        super(email,nome,password,morada,nascimento);
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }
    
    public ArrayList<Veiculo> getViaturas(){
        return this.viaturas;
    }
    
    public Empresa(Empresa e){
        super(e.getEmail(), e.getNome(), e.getPassword(), e.getMorada(), e.getNascimento());
        this.motoristas = new ArrayList<>();
        this.viaturas = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }
    
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
