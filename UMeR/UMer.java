import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Math;

public class UMer implements Serializable{

    private HashMap<String, Actor> listaCliente = new HashMap<String, Actor>();
    private HashMap<String, Veiculo> listaVeiculo = new HashMap<String, Veiculo>();
    private Map<Integer,Viagem> listaViagens = new HashMap<Integer,Viagem>();
    private int idViagem = 0;
    private Actor currentUser;


    
    public void save(String file) throws IOException {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
    }
    
    public static UMer createFromFile(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream (fis);
        
        Object obj = ois.readObject();
        ois.close();
        
        if (obj instanceof UMer) {
            return (UMer)obj;
        }
        return null;
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
    }

    public boolean verificarMail(String email) {
        return listaCliente.containsKey(email);
    }

    
    public int realizarViagem(String matricula, Coords inicio, Coords fim){
        Veiculo v;
        Motorista m;
        Cliente aux = (Cliente) this.currentUser;
        double precoAcordado;
        double dist;
        
        dist = inicio.distancia(fim);
        
        
        v = listaVeiculo.get(matricula);
        precoAcordado = dist * v.getPrecoPorKM();
        m = v.getMotorista();
        
        inserirViagem(aux, inicio, fim, precoAcordado, m, v);
        return idViagem;
    }


    public void inserirViagem(Cliente cliente,Coords inicio, Coords fim, double precoAcordado, Motorista condutor, Veiculo veiculo){

      
        Random rando = new Random();
        Double  rng = ((rando.nextInt(51) + (Double)85.0)/100.0); //de 0.85 a 1.35
        Double distancia = inicio.distancia(fim); //Double//discantica por calcular funçao manu
        LocalDate data = LocalDate.now(); //current date
        Double desvio;
        
        Double precoFinal = precoAcordado;
        Double tempoEstimado = distancia*veiculo.getPrecoPorKM();
        Double tempoFinal = tempoEstimado * rng;

        desvio = Math.abs(tempoEstimado-tempoFinal)/tempoEstimado;
        
        if (desvio<=0.25){
            precoFinal = precoAcordado * desvio;
        }
        
        
        Viagem nova = new Viagem(cliente, inicio, fim, distancia, precoAcordado, precoFinal,tempoEstimado, tempoFinal, condutor, veiculo, data, -1);
        idViagem++;
        listaViagens.put(idViagem,nova);
        
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
    
    public void assciarCondutor(String nome){ //associa a empresa e liberta current car
        Motorista m = (Motorista) this.currentUser;
        Empresa empresa = (Empresa)listaCliente.get(nome);
        empresa.adicionarMotorista(m);
        m.setEmpresa(empresa);
        System.out.println(empresa);
        System.out.println(m);
        libertarCarro();
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
        Double i;
        
        for (Veiculo aux : this.listaVeiculo.values()){

                if (aux.getMotorista() != null){           //ver se tem motorista
                    Motorista motorista = aux.getMotorista();
                    if (motorista.getEstado()){             //ver se motorista esta livre                       
                        i = aux.getPosicao().distancia(posicao);
                        temp.put((aux), i);//mapa com cliente e distancia

                        //System.out.print("motorista ADDED \n"  );
                    }
                }
        
        }
        
        Map<Veiculo,Double> sortedMap = sortByValueVeiculo(temp);
        /*
        System.out.println(temp.size());
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

    public Double totalFaturadoVeiculo(String veiculo, LocalDate after, LocalDate before){
        Veiculo v = listaVeiculo.get(veiculo);
        Double total = 0.0;
        ArrayList<Viagem> lista =  v.getListaViagens();
        
        for (Viagem aux : lista){
                if (aux.getData().isBefore(before) && aux.getData().isAfter(after))
                    total+=aux.getPrecoFinal();
        }
        return total;
    }

    public Double totalFaturado(String empresa,LocalDate after, LocalDate before){
        Empresa e = (Empresa) listaCliente.get(empresa);
        Double total = 0.0;
        ArrayList<Veiculo> lista = e.getViaturas();
        for(Veiculo v : lista)
            total+=totalFaturadoVeiculo(v.getMatricula(),after,before);

        return total;
    }

    public int rate (int viagem, int rate){
        if (rate >= 0  && rate <= 5){
            listaViagens.get(viagem).setNota(rate);
            return 1;
        }
        return 0;
    }
    
    
    
    public void popular(){
        LocalDate d1 = LocalDate.of(1991,01,15);
        LocalDate d2 = LocalDate.of(1994,06,8);
        LocalDate d3 = LocalDate.of(1995,01,25);
        LocalDate d4 = LocalDate.of(1954,03,01);
        LocalDate d5 = LocalDate.of(1994,06,2);
        LocalDate d6 = LocalDate.of(1976,12,20);
        LocalDate d7 = LocalDate.of(1987,05,13);
        LocalDate d8 = LocalDate.of(1964,06,17);
        LocalDate d9 = LocalDate.of(1974,11,25);

        Coords c1 = new Coords(1,3);
        Coords c2 = new Coords(5,2);
        Coords c3 = new Coords(4,1);
        Coords c4 = new Coords(8,5);
        Coords c5 = new Coords(1,1);
        Coords c6 = new Coords(4,1);


        Cliente a = new Cliente("jonas@gmail.com", "Joao", "jo", "Braga", d1);
        Cliente b = new Cliente("rui@gmail.com", "Rui", "321", "Nogueiro", d2);
        Cliente c = new Cliente("maria111@gmail.com", "Maria", "123", "Vila Verde", d3);
        Cliente d = new Cliente("email@gmail.com",   "Antonio", "passesSaoParaLoosers", "Lamacaes", d4);
        Cliente e = new Cliente("jonas22@gmail.com", "Jonas", "yoyo123", "Fraiao", d5);
        Cliente f = new Cliente("ruiSCB@gmail.com", "Rui", "321", "Porto", d6);
        Cliente g = new Cliente("manu@hottestmail.com", "Manuel", "123", "Lisboa", d7);
        Cliente h = new Cliente("joaozinho@gmail.com", "Joao", "qwerty", "Madera", d8);
        

        Motorista m1 = new Motorista("socrates@parthenon.com", "Socrates", "123","morada",d2);
        Motorista m2 = new Motorista("Descartes@", "Descartes", "123","morada",d5);
        Motorista m3 = new Motorista("Kant@gmail.com", "Kant", "123","morada",d1); 
        Motorista m4 = new Motorista("Platao@gmail.com", "rute", "123","morada",d7);
        Motorista m5 = new Motorista("KarlxMarx34@gmail.com", "Marx", "123","morada",d3);
        Motorista m6 = new Motorista("MariaL@gmail.com", "bru", "123","morada",d9);
        

        Veiculo v1 = new Veiculo("11-FS-71",1,5,c1,m1);
        Veiculo v2 = new Veiculo("HE-35-16",1,4,c2,m2);
        Veiculo v3 = new Veiculo("18-HW-87",1,3,c3,m3);
        Veiculo v4 = new Veiculo("OO-00-11",1,5,c4,m4);
        Veiculo v5 = new Veiculo("92-GS-29",1,3,c5,m5);
        Veiculo v6 = new Veiculo("FG-76-93",1,4,c6,m6);


        listaVeiculo.put(v1.getMatricula(), v1);
        listaVeiculo.put(v2.getMatricula(), v2);
        listaVeiculo.put(v3.getMatricula(), v3);
        listaVeiculo.put(v4.getMatricula(), v4);
        listaVeiculo.put(v5.getMatricula(), v5);
        listaVeiculo.put(v6.getMatricula(), v6);
        

        listaCliente.put(a.getEmail(),a);
        listaCliente.put(b.getEmail(),b);
        listaCliente.put(c.getEmail(),c);
        listaCliente.put(d.getEmail(),d);
        listaCliente.put(e.getEmail(),e);
        listaCliente.put(f.getEmail(),f);
        listaCliente.put(g.getEmail(),g);
        listaCliente.put(h.getEmail(),h);
        listaCliente.put(m1.getEmail(),m1);
        listaCliente.put(m2.getEmail(),m2);
        listaCliente.put(m3.getEmail(),m3);
        listaCliente.put(m4.getEmail(),m4);
        listaCliente.put(m5.getEmail(),m5);
        listaCliente.put(m6.getEmail(),m6);
        


        
        Empresa continente = new Empresa("continente@gmail.com","Continente","cartaocliente","Minho Center",d1);
        Empresa pingoDoce = new Empresa("pingodoce@gmail.com","Pingo Doce","321","Praga, em todo o lado",d3);
        Empresa feiraNova = new Empresa("feiraNova@gmail.com","Feira Nova","123","fechou",d6);

        continente.viaturas.add(v1);
        continente.viaturas.add(v2);
        continente.motoristas.add(m1);
        continente.motoristas.add(m2);
        m1.setEmpresa(continente);
        m2.setEmpresa(continente);
        
        listaCliente.put(continente.getEmail(),continente);
        listaCliente.put(pingoDoce.getEmail(),pingoDoce);
        listaCliente.put(feiraNova.getEmail(),feiraNova);


        pingoDoce.viaturas.add(v3);
        pingoDoce.motoristas.add(m3);
        m3.setEmpresa(pingoDoce);



        inserirViagem(a,c1, c2, 20.5, m1, v1);
        inserirViagem(a, c4, c1, 20.5, m2, v2);
        inserirViagem(b,c2, c5, 12.0, m4, v4);
        inserirViagem(b,c5, c2, 15.1, m1, v1);
        inserirViagem(c,c1, c2, 20.5, m3, v3);
        inserirViagem(c,c4, c1, 20.5, m2, v2);

        inserirViagem(d,c3, c1, 12.0, m4, v4);
        inserirViagem(d,c5, c6, 15.1, m1, v1);
        inserirViagem(d,c3, c5, 4.50, m1, v1);
        inserirViagem(e,c4, c1, 20.5, m4, v4);
        inserirViagem(f,c2, c3, 25.0, m4, v4);
        inserirViagem(g,c5, c2, 15.1, m6, v6);

        rate(1,5);
        rate(2,3);
        rate(3,2);
        rate(4,4);
        rate(5,5);
        rate(6,3);
        rate(7,2);
        rate(8,6);
        rate(9,1);
        rate(10,5);
        rate(11,2);
        rate(12,4);

    }
    
}
