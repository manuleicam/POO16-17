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
        Coords co2 = new Coords(5,3);

        Cliente a = new Cliente("jonas@gmail.com", "joao", "jo", "todoolado", da);
        Cliente b = new Cliente("rui@gmail.com", "rui", "321", "nunca", db);
        Cliente c = new Cliente("manu", "manu", "123", "wow", dc);
        Cliente d = new Cliente("email", "joao", "qwerty", "todoolado", da);
        
        Motorista m = new Motorista("rute", "rute", "123","morada",da);
        Motorista m3 = new Motorista("reno", "reno", "123","morada",da);
        Motorista m2 = new Motorista("bre", "bru", "123","morada",dc); // fite mi irl bru
        
        Veiculo v = new Veiculo("11-11-11",1,2,co);
        //Veiculo v3 = new Veiculo("33-33-33",1,2,co,m);
        listaVeiculo.put("11-11-11", v);
        //listaVeiculo.put("33-33-33", v3);
        
        Viagem via = new Viagem(a, co, co2, 3.9, 3.5, 4.3,3.3,2.2,m,v,da,2);
        listaViagens.put(1,via);
        
        Empresa e = new Empresa("js","juve sao","321","morada",db);
        e.viaturas.add(v);
        
        e.motoristas.add(m2);
        e.motoristas.add(m3);

        m2.setEmpresa(e);
        m3.setEmpresa(e);
        
        listaCliente.put("jonas@gmail.com", a);
        listaCliente.put("rui@gmail.com", b);
        listaCliente.put("manu", c);
        listaCliente.put("email", d);
        listaCliente.put("rute",m);
        listaCliente.put("js",e);
        listaCliente.put("bre",m2);
        listaCliente.put("reno",m3);
        
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
    
    public void realizarViagem(String matricula, Coords inicio, Coords fim){
        Veiculo v;
        Motorista m;
        Cliente aux = (Cliente) this.currentUser;
        
        v = listaVeiculo.get(matricula);
        m = v.getMotorista();
        
        inserirViagem(aux, idViagem, inicio, fim, 0, m, v);
    }


    public void inserirViagem(Cliente cliente, int id, Coords inicio, Coords fim, double precoAcordado, Motorista condutor, Veiculo veiculo){

      
        Random rando = new Random();
        Double  rng = ((rando.nextInt(41) + (Double)85.0)/100.0); //de 0.9 a 1.1
        Double distancia = inicio.distancia(fim); //Double//discantica por calcular funçao manu
        LocalDate data = LocalDate.now(); //current date
        Double desvio = 0.0;
        
        Double precoFinal = precoAcordado;
    
        Double tempoEstimado = distancia * veiculo.getVelMediaKM();
        Double tempoFinal = tempoEstimado * rng;
        desvio = (tempoEstimado/tempoFinal);
        double precoEstimado = distancia*veiculo.getPrecoPorKM();
        if (desvio<=0.25){
            precoFinal = precoEstimado * desvio;
        }
        
        
        Viagem nova = new Viagem(cliente, inicio, fim, distancia, precoAcordado, precoFinal,tempoEstimado, tempoFinal, condutor, veiculo, data, -1);
        idViagem++;
        listaViagens.put(id,nova);

        cliente.addViagem(nova);
        condutor.addViagem(nova);
        veiculo.addViagem(nova);

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
    
    public int verificarMatricula(String matricula){
        if (this.listaVeiculo.containsKey(matricula)) return 1;
        return 0;
    }
    
    public int registarNovaViatura(String matricula, int tipoVeiculo, int velMediaKm, int precoPorKM, Coords c){
        NoveLugares vNL;
        Ligeiros vL;
        Motos vM;
        Empresa aux;
        Motorista m;
        
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
                    m = (Motorista) this.currentUser;
                    vL = new Ligeiros(matricula, velMediaKm, precoPorKM, c,this.currentUser);
                    for(Veiculo v : this.listaVeiculo.values()){
                        if(v.getMotorista()!=null)
                            if(v.getMotorista().getEmail().equals(m.getEmail())) v.removeMotorista();
                    }
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
                    m = (Motorista) this.currentUser;
                    vM = new Motos(matricula, velMediaKm, precoPorKM, c, this.currentUser);
                    for(Veiculo v : this.listaVeiculo.values()){
                        if(v.getMotorista()!=null)
                            if(v.getMotorista().getEmail().equals(m.getEmail())) v.removeMotorista();
                    }
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
                    m = (Motorista) this.currentUser;
                    vNL = new NoveLugares(matricula, velMediaKm, precoPorKM, c, this.currentUser);
                    for(Veiculo v : this.listaVeiculo.values()){
                        if(v.getMotorista()!=null)
                            if(v.getMotorista().getEmail().equals(m.getEmail())) v.removeMotorista();
                    }
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
        
        if(v.getEstado() == true    ) return -1;
        
        for(Veiculo vei : this.listaVeiculo.values()){
            if(vei.getMotorista()!=null)
                if(vei.getMotorista().getEmail().equals(aux.getEmail())) vei.removeMotorista();
        }
        
        v.setMotorista(aux);
        
        
        return 1;
    }

    public ArrayList<Veiculo> verFrota(){
        Empresa e = (Empresa) this.currentUser;
        ArrayList<Veiculo> vl = e.getViaturas();
        return vl;
    }


    private Map<Cliente,Double> sortByValueCliente(Map<Cliente,Double> unsortedMap) {
        Map<Cliente,Double> sortedMap = new TreeMap<Cliente,Double>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    
    private Map<Motorista,Double> sortByValueMotorista(Map<Motorista,Double> unsortedMap) {
        Map<Motorista,Double> sortedMap = new TreeMap<Motorista,Double>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    
        private Map<Veiculo,Double> sortByValueVeiculo(Map<Veiculo,Double> unsortedMap) {
        Map<Veiculo,Double> sortedMap = new TreeMap<Veiculo,Double>(new ValueComparator(unsortedMap));

        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    public Map<Cliente,Double> top10clientes () {
        Cliente aux = new Cliente();
        Map<Cliente, Double> temp = new HashMap <Cliente, Double>();
        
        for (Actor a : this.listaCliente.values()){
            if (a.getClass().getSimpleName() == "Cliente"){
                aux = (Cliente) a;
                temp.put((aux), (Double)aux.getTotalGasto());//mapa com cliente e valor gasto total
                //System.out.print("CLIENTE ADDED \n"  );
             }
        }
        
        Map<Cliente,Double> sortedMap = sortByValueCliente(temp);
        
       /* System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Cliente, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        }*/ //TESTES

        return sortedMap;
    }


    public Map<Motorista,Double> piores5condutores () {
        Motorista aux = new Motorista();
        Map<Motorista, Double> temp = new HashMap <Motorista, Double>();
        
        for (Actor a : this.listaCliente.values()){
            if (a.getClass().getSimpleName() == "Motorista"){
                aux = (Motorista) a;
                temp.put((aux), (Double)aux.getDesvioAcumulado());//mapa com cliente e valor gasto total
                //System.out.print("motorista ADDED \n"  );
             }
        }
        
        Map<Motorista,Double> sortedMap = sortByValueMotorista(temp);
        
        /*System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Motorista, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        } *///TESTES

        return sortedMap;
    }

    public Map<Veiculo,Double> viaturasProx (Coords posicao) {
        Map<Veiculo, Double> temp = new HashMap <Veiculo, Double>();
        
        for (Veiculo aux : this.listaVeiculo.values()){
                double i = aux.getPosicao().distancia(posicao);
                temp.put((aux), i);//mapa com cliente e distancia
                System.out.print("motorista ADDED \n"  );
        }
        
        Map<Veiculo,Double> sortedMap = sortByValueVeiculo(temp);
        
        /*System.out.println(temp.size());
        System.out.println(sortedMap.size());
        for (Map.Entry<Veiculo, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getMatricula() + " " + e.getValue() + "\n");
        } //TESTES*/

        return sortedMap;
    }
    
    public String trocaEstadoMotorista(){
        Motorista m;
        String o = "Ocupado";
        String l = "Livre";
        m = (Motorista) this.currentUser;
        m.trocaEstado();
        if(m.getEstado() == false) return o;
        else return l;
    }
    
   public String libertarCarro(){
       Motorista aux = (Motorista) this.currentUser;
       String l = "Carro liberto";
       String n = "Não estavas associado a nenhum carro";
       for(Veiculo vei : this.listaVeiculo.values()){
            if(vei.getMotorista()!=null)
                if(vei.getMotorista().getEmail().equals(aux.getEmail())) {vei.setEstado(false); vei.removeMotorista(); return l;}
       }
       return n;
   }

    public Double totalFaturado(Veiculo veiculo, LocalDate after, LocalDate before){

        Double total = 0.0;
        ArrayList<Viagem> lista =  veiculo.getListaViagens();
        
        for (Viagem aux : lista){
                if (aux.getData().isBefore(before) && aux.getData().isAfter(after))
                    total+=aux.getPrecoFinal();
        }
        return total;
    }

    public Double totalFaturado(Empresa empresa,LocalDate after, LocalDate before){
        
        Double total = 0.0;
        ArrayList<Veiculo> lista = empresa.getViaturas();
        for(Veiculo v : lista)
            total+=totalFaturado(v,after,before);

        return total;
    }

    public void rate (Cliente cliente, Viagem viagem, int rate){
        if (rate > 0  && rate < 1);
            viagem.setNota(rate);
    }
    
    
    
}
