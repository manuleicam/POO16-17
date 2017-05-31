import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


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
    
}
