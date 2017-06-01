import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.*;

public class UMer {

    private HashMap<String, Actor> listaCliente = new HashMap<String, Actor>();
    private HashMap<String, Veiculo> listaVeiculo = new HashMap<String, Veiculo>();
    private Map<Integer,Viagem> listaViagens = new HashMap<Integer,Viagem>();
    private int idViagem = 0;
    private Actor currentUser;

    public UMer() {
        LocalDate da = LocalDate.of(2000,01,01);
        LocalDate db = LocalDate.of(1994,06,8);
        LocalDate dc = LocalDate.of(1995,01,20);
        Coords co = new Coords(3,3);

        Cliente a = new Cliente("jonas@gmail.com", "joao", "jo", "todoolado", da);
        Cliente b = new Cliente("rui@gmail.com", "rui", "321", "nunca", db);
        Cliente c = new Cliente("manu@gmail.com", "manu", "123", "wow", dc);
        Cliente d = new Cliente("email", "joao", "qwerty", "todoolado", da);
        
        Motorista m = new Motorista("rute@gmail.com", "rute", "123","morada",da);
        Motorista m2 = new Motorista("bre", "bru", "123","morada",dc); // fite mi irl bru
        
        Veiculo v = new Veiculo("11-11-11",1,2,co);
        listaVeiculo.put("11-11-11", v);
        
        Empresa e = new Empresa("js","juve sao","321","morada",db);
        e.viaturas.add(v);

        m2.setEmpresa(e);
        
        listaCliente.put("jonas@gmail.com", a);
        listaCliente.put("rui@gmail.com", b);
        listaCliente.put("manu@gmail.com", c);
        listaCliente.put("email", d);
        listaCliente.put("rute@gmail.com",m);
        listaCliente.put("js",e);
        listaCliente.put("bre",m2);
        
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
            dados = "Viatura: " + d.toString() + " Fiabilidade: " + d.getFiabilidade() + " distância: " + dist;
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
    
    public int getTipoMotorista(String user){
        Motorista m = (Motorista) this.currentUser;
        if( m.getEmpresa() == null) return 0;
        return 1;
    }
    
    public int registarNovaViatura(String matricula, int tipoVeiculo, int velMediaKm, int precoPorKM, Coords c){
        NoveLugares vNL;
        Ligeiros vL;
        Motos vM;
        Empresa aux;
        
        switch(tipoVeiculo){
            case 1:
                if(this.currentUser.getClass().getSimpleName() == "Empresa"){
                    vL = new Ligeiros(matricula, velMediaKm, precoPorKM,c);
                    this.listaVeiculo.put(matricula,vL);
                    aux = (Empresa) this.currentUser;
                    aux.addVeiculoLigeiros(vL);
                    return 1;
                }
                else{
                    vL = new Ligeiros(matricula, velMediaKm, precoPorKM, c,this.currentUser);
                    this.listaVeiculo.put(matricula, vL);
                    return 2;
                }
            case 2:
                if(this.currentUser.getClass().getSimpleName() == "Empresa"){
                    vM = new Motos(matricula, velMediaKm, precoPorKM,c);
                    this.listaVeiculo.put(matricula,vM);
                    aux = (Empresa) this.currentUser;
                    aux.addVeiculoMotos(vM);
                    return 1;
                }
                else{
                    vM = new Motos(matricula, velMediaKm, precoPorKM, c, this.currentUser);
                    this.listaVeiculo.put(matricula, vM);
                    return 2;
                }
            case 3:
                if(this.currentUser.getClass().getSimpleName() == "Empresa"){
                    vNL = new NoveLugares(matricula, velMediaKm, precoPorKM,c);
                    this.listaVeiculo.put(matricula,vNL);
                    aux = (Empresa) this.currentUser;
                    aux.addVeiculoNoveLugares(vNL);
                    return 1;
                }
                else{
                    vNL = new NoveLugares(matricula, velMediaKm, precoPorKM, c, this.currentUser);
                    this.listaVeiculo.put(matricula,vNL);
                    return 2;
                }
        }
        return 0;
    }
    
    public int motoristaAssociarAVeiculoDaEmpresa(String matricula){
        int res;
        Empresa e;
        Veiculo v;
        Motorista aux = (Motorista) this.currentUser;
        if(!this.listaVeiculo.containsKey(matricula)) return -1;
        e = aux.getEmpresa();
        
        v = e.getVeiculo(matricula);
        
        if(v == null) return 0;
        
        if(v.getEstado() == false) return -1;
        
        v.setMotorista(aux);
        return 1;
    }

    public ArrayList<Veiculo> verFrota(){
        Empresa e = (Empresa) this.currentUser;
        ArrayList<Veiculo> vl = e.getViaturas();
        return vl;
    }


    private Map<Cliente,Float> sortByValueCliente(Map<Cliente,Float> unsortedMap) {
        Map<Cliente,Float> sortedMap = new TreeMap<Cliente,Float>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    
    private Map<Motorista,Float> sortByValueMotorista(Map<Motorista,Float> unsortedMap) {
        Map<Motorista,Float> sortedMap = new TreeMap<Motorista,Float>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    
        private Map<Veiculo,Float> sortByValueViatura(Map<Veiculo,Float> unsortedMap) {
        Map<Veiculo,Float> sortedMap = new TreeMap<Veiculo,Float>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    public Map<Cliente,Float> top10clientes () {
        Cliente aux = new Cliente();
        Map<Cliente, Float> temp = new HashMap <Cliente, Float>();
        
        for (Actor a : this.listaCliente.values()){
            if (a.getClass().getSimpleName() == "Cliente"){
                aux = (Cliente) a;
                temp.put((aux), (Float)aux.getTotalGasto());//mapa com cliente e valor gasto total
                //System.out.print("CLIENTE ADDED \n"  );
             }
        }
        
        Map<Cliente,Float> sortedMap = sortByValueCliente(temp);
        
       /* System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Cliente, Float> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        }*/ //TESTES

        return sortedMap;
    }


    public Map<Motorista,Float> piores5condutores () {
        Motorista aux = new Motorista();
        Map<Motorista, Float> temp = new HashMap <Motorista, Float>();
        
        for (Actor a : this.listaCliente.values()){
            if (a.getClass().getSimpleName() == "Motorista"){
                aux = (Motorista) a;
                temp.put((aux), (Float)aux.getDesvioAcumulado());//mapa com cliente e valor gasto total
                //System.out.print("motorista ADDED \n"  );
             }
        }
        
        Map<Motorista,Float> sortedMap = sortByValueMotorista(temp);
        
        /*System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Motorista, Float> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        } *///TESTES

        return sortedMap;
    }

     public Map<Veiculo,Double> viaturasProx (Coords posicao) {
        Veiculo aux = new Veiculo();
        Map<Veiculo, Double> temp = new HashMap <Veiculo, Double>();
        
        for (Veiculo a : this.listaVeiculo.values()){
            if (a.getClass().getSimpleName() == "Veiculo"){
                aux = (Veiculo) a;
                double i = a.getPosicao().distancia(posicao);
                temp.put((aux), i);//mapa com cliente e valor gasto total
                System.out.print("motorista ADDED \n"  );
             }
        }
        
        Map<Veiculo,Double> sortedMap = sortByValueMotorista(temp);
        
        System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Motorista, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        } //TESTES

        return sortedMap;
    }
}
