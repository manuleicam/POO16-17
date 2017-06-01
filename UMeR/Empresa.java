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
        
        for(Veiculo v : viaturas){
            if(v.getMatricula().equals(matricula)) return v;
        }
        return null;
    }
    
    public StringBuilder listAllVeiculos(){
        StringBuilder sb = new StringBuilder();
        for( Veiculo v : viaturas){
            sb.append(v.toString());
        }
        return sb;
    }
    
}
