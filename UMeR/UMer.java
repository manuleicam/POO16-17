import java.time.LocalDate;
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
        LocalDate da = LocalDate.of(2000,01,01);
        LocalDate db = LocalDate.of(1994,06,8);
        LocalDate dc = LocalDate.of(1995,01,20);

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
                if(temp.getClass().getSimpleName() == "Cliente") return 1;
                if(temp.getClass().getSimpleName() == "Motorista") return 2;
                if(temp.getClass().getSimpleName() == "Empresa") return 3;
                return 1; //ok
            } else {
                return 0; //pass mal
            }
        } else {
            return -1;//nao existe user
        }
    }

    public void register(String email, String nome,String password, String morada, LocalDate dataRecebida, int tipoReg) {
        Cliente tempC;
        Motorista tempM;
        Empresa tempE;
        
        switch(tipoReg){
            case 1:
                tempC = new Cliente(email,nome, password, morada, dataRecebida);
                listaCliente.put(email, tempC);
                break;
            case 2:
                tempM = new Motorista(email, nome, password, morada, dataRecebida);
                listaCliente.put(email, tempM);
                break;
            case 3:
                tempE = new Empresa(email, nome, password, morada, dataRecebida);
                listaCliente.put(email, tempE);
                System.out.println(tempE.toString());
                break;
        }
       

        testeActor(email); //teste, remover!
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
        double distancia = inicio.distancia(fim); //float//discantica por calcular funçao manu
        LocalDate data = LocalDate.now(); //current date?
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
    
    public ArrayList<Viagem> procuraEntreDatas(LocalDate dataInicial, LocalDate dataFinal){
        ArrayList<Viagem> tempV = new ArrayList<>();
        ArrayList<Viagem> tempVf = new ArrayList<>();
        
        tempV.addAll(this.currentUser.getListaViagens());
        for(Viagem v : tempV){
            if (v.getData().isAfter(dataInicial) && v.getData().isBefore(dataFinal)){
                tempVf.add(v);
            }
        }
        return tempVf;
        
    }
    
    public void registarNovaViatura(String matricula, int tipoVeiculo, int velMediaKm, int precoPorKM, Coords c){
        NoveLugares vNL;
        Ligeiros vL;
        Motos vM;
        
        switch(tipoVeiculo){
            case 1:
                vL = new Ligeiros(1, matricula, velMediaKm, precoPorKM, c);
                this.listaVeiculo.put(matricula, vL);
                break;
            case 2:
                vM = new Motos(1,matricula, velMediaKm, precoPorKM, c);
                this.listaVeiculo.put(matricula, vM);
                break;
            case 3:
                vNL = new NoveLugares(1,matricula, velMediaKm, precoPorKM, c);
                this.listaVeiculo.put(matricula,vNL);
                break;
        }
    }


}
