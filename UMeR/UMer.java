
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;

public class UMer {

    private HashMap<String, Actor> listaCliente = new HashMap<String, Actor>();
    private HashMap<String, Veiculo> listaVeiculo = new HashMap<String, Veiculo>();
    private Map<Integer,Viagem> listaViagens = new HashMap<Integer,Viagem>();
    private static int idViagem = 0;
    private Actor currentUser;

    public UMer() {
        Date da = new Date(2000, 01, 01);
        Date db = new Date(1994, 06, 8);
        Date dc = new Date(1995, 01, 20);

        Cliente a = new Cliente("jonas@gmail.com", "joao", "jo", "todoolado", da);
        Cliente b = new Cliente("rui@gmail.com", "rui", "321", "nunca", db);
        Cliente c = new Cliente("manu@gmail.com", "manu", "123", "wow", dc);
        Cliente d = new Cliente("email", "joao", "qwerty", "todoolado", da);

        listaCliente.put("jonas@gmail.com", a);
        listaCliente.put("rui@gmail.com", b);
        listaCliente.put("manu@gmail.com", c);
        listaCliente.put("email", d);
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

    public void inserirViagem(Cliente cliente, int id, Coords inicio, Coords fim, int precoAcordado, Motorista condutor, Veiculo veiculo){

      
        Random rando = new Random();
        float  rng = ((rando.nextInt(21) + (float)90)/100); //de 0.9 a 1.1
        int distancia = 1; //discantica por calcular funçao manu
        Date data = new Date(); //current date?
        float precoFinal = precoAcordado * rng;
        
        Viagem nova = new Viagem(id, cliente, inicio, fim, distancia, precoAcordado, precoFinal, condutor, veiculo, data);
        idViagem++;
        listaViagens.put(id,nova);

        cliente.addViagem(nova);
        condutor.addViagem(nova);
        veiculo.addViagem(nova);

    }
    
    public ArrayList<String> top5CondutoresMaisPerto(int x, int y) {
        ArrayList<String> resp = new ArrayList<>();
        String dados;
        int tempX, tempY;
        double dist, distMin;
        Coords coordsAux, coords;
        ArrayList<String> viaturas = new ArrayList<>();
        coords = new Coords(x, y);
        for (Veiculo d : listaVeiculo.values()) {
            coordsAux = d.getPosicao();
            dist = coords.distancia(coordsAux);
            dados = "Viatura: " + d.getId() + " Fiabilidade: " + d.getFiabilidade() + " distância: " + dist;
            resp.add(dados);
        }
        return resp;
    }


}
