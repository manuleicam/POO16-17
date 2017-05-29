
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class UMer {

    private HashMap<String, Actor> listaCliente = new HashMap<String, Actor>();
    private Actor currentUser;

    public UMer() {
        Date da = new Date(2000, 01, 01);
        Date db = new Date(1994, 06, 8);
        Date dc = new Date(1995, 01, 20);

        Cliente a = new Cliente("jonas@gmail.com", "joao", "jo", "todoolado", da);
        Cliente b = new Cliente("rui@gmail.com", "rui", "321", "nunca", db);
        Cliente c = new Cliente("manu@gmail.com", "manu", "123", "wow", dc);

        listaCliente.put("jonas@gmail.com", a);
        listaCliente.put("rui@gmail.com", b);
        listaCliente.put("manu@gmail.com", c);

    }

    public int login(String email, String pass) {
        Actor temp;
        temp = listaCliente.get(email);
        if (temp != null) {
            if (temp.logIn(pass)) {
                this.currentUser = temp;
                return 1; //ok
            } else {
                return 0; //pass mal
            }
        } else {
            return -1;//nao existe user
        }
    }

    public int register(String email, String nome,String password, String morada, Date dataRecebida, int tipoReg) {
        Cliente tempC;
        Motorista tempM;
        
        if (tipoReg == 2) {
            tempM = new Motorista(email,nome, password, morada, dataRecebida);
            listaCliente.put(email, tempM); 
            return 1; 
        }
        
        tempC = new Cliente(email, nome, password, morada, dataRecebida);
        listaCliente.put(email, tempC);

        testeActor(email); //teste, remover!

        return 1;
    }

    public boolean verificarMail(String email) {
        return listaCliente.containsKey(email);
    }

    public void testeActor(String email) {
        Actor ze = new Actor();
        if (listaCliente.containsKey(email)) {
            System.out.println("asdasdasasdasddsa");
        }
        ze = listaCliente.get(email);
        String nome = new String();
        nome = ze.getNome();
        System.out.println(nome);
    }
}
