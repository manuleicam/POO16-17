
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Menu {
    // variáveis de instância
    private List<String> opcoes;
    private String[] menuPrinc = {"LogIn", "Registar", "Estatistica", "Povoar", "Gravar"};
    private String[] menuCliente = {"Realizar Viagem", "Ver Viagens Efectuadas"};
    private String[] menuViagem = {"Viatura mais próxima","Escolher viatura"};
    private String[] menuEstatistica = {"Top 10 clientes gastadores", "Piores 5 motoristas", "Total facturado por uma empresa", "Total facturado por um veiculo"};
    private String[] menuMotoristaComEmpresa = {"Associar-se a uma viatura","Ver Viagens Efectuadas", "Mudar o estado", "Libertar Carro"};
    private String[] menuMotoristaPrivado = {"Registar Nova Viatura", "Ver Viagens Efectuadas", "Associar-se a uma empresa", "Mudar o estado"};
    private String[] menuEmpresa = {"Registar Nova Viatura","Ver Frota", "Ver Viagens Efectuadas"};
    private static final String OBJECT_FILE = "umerTaxis.obj";
    
    private int op, esc;
    public UMer umer;
    public Scanner escolha; 
    
    public Menu(){
        escolha = new Scanner(System.in);
        umer = new UMer();
    }

    public static void main(String[] args){
        System.out.println('\f');
        new Menu().run();
    }
    
    public void run(){
        
        try {
            umer = UMer.createFromFile(OBJECT_FILE);
            menuPrinc();
        }
        catch (Exception e) {
            System.out.println("NO OBJECT FILE");
            menuPrinc();
        }
        
    }
    
    public void save() {
        try {
            umer.save(OBJECT_FILE);
        }
        catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void menuPrinc(){
        boolean x = true;
        while(x){
            setOpcoes(menuPrinc);
            executa();
            esc = getOpcao();
            switch (esc) {
                case 1:
                    logIN();
                    break;
                case 2:
                    registar();
                    break;
                case 3:
                    estatistica();
                    break;
                case 4:
                    umer.popular();
                    break;
                case 5:
                    save();
                    break;
                case 0:
                    x = false;
                    System.out.println("Adeus!");
                    break;
            }
        }
   }
    
    public void menuCliente(){
        boolean x = true;
        while(x){
            setOpcoes(menuCliente);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1: 
                    System.out.println("1");
                    menuViagem();
                    break;
                case 2:
                    System.out.println("2");
                    verViagens();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void menuViagem(){
        boolean x = true;
        while(x){
            setOpcoes(menuViagem);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1:
                    posicaoInicialCliente(2);
                    System.out.print("1");
                    break;
                case 2:
                    posicaoInicialCliente(6);
                    System.out.println("2");
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void menuEstatistica(){
        boolean x = true;
        while(x){
            setOpcoes(menuEstatistica);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1:
                    top10Clientes();
                    break;
                case 2:
                    pioresCondutores();
                    break;
                case 3:
                    totalFacturadoEmpresa(1);
                    break;
                case 4:
                    totalFacturadoEmpresa(2);
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void menuMotoristaComEmpresa(){
        boolean x = true;
        while(x){
            setOpcoes(menuMotoristaComEmpresa);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1:
                    associarAViatura();
                    break;
                case 2:
                    verViagens();
                    break;
                case 3:
                    mudarEstadoMotorista();
                    break;
                case 4:
                    libertarCarro();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void menuMotoristaPrivado(){
        boolean x = true;
        while(x){
            setOpcoes(menuMotoristaPrivado);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1: 
                    System.out.println("1");
                    registarNovaViatura();
                    break;
                case 2:
                    System.out.println("2");
                    verViagens();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void menuEmpresa(){
        boolean x = true;
        while(x){
            setOpcoes(menuEmpresa);
            executa();
            esc = getOpcao();
            switch(esc){
                case 1: 
                    registarNovaViatura();
                    break;
                case 2:
                    verFrota();
                    break;
                case 3:
                    verViagens();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }
    
    public void setOpcoes(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     * 
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }
    
    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n *** Menu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }
    
    /** Ler uma opção válida */
    private int lerOpcao() {
        int op; 
        
        System.out.print("Opção: ");
        try {
            op = escolha.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
    
    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }
    
    public void logIN() {
        String user, password;
        int flagLog, empresaPertence;

        escolha.nextLine();
        System.out.println("Insira o seu email");
        user = escolha.nextLine();

        System.out.println("Insira a sua password");
        password = escolha.nextLine();

        flagLog = umer.login(user, password);
        while (flagLog != 1 && flagLog != 2 && flagLog != 3) {
            if (flagLog == 0) {
                System.out.println("Password não corresponde ao Email inserido");
            } else {
                System.out.println("O email inserido não se encontra registado");
            }
            System.out.println("Insira o seu Email");
            user = escolha.nextLine();

            System.out.println("Insira a sua password");
            password = escolha.nextLine();

            flagLog = umer.login(user, password);
        }
        if (flagLog == 1) menuCliente();
        else if(flagLog == 2) {
            empresaPertence = umer.getTipoMotorista(user);
            if(empresaPertence == 1) menuMotoristaComEmpresa();
            else menuMotoristaPrivado();
        }
        else menuEmpresa();

    }

    public void registar() {
        String user, password, dados, morada, data, nome;
        String[] datapartida;
        int tipoReg = 0, flagTipo = 0;

        
        
        while(tipoReg > 3 || tipoReg<1){
            if (flagTipo != 0) System.out.println("Opção inválida!");
            System.out.println("Qual o tipo de registo?");
            System.out.println("1 - Cliente");
            System.out.println("2 - Motorista");
            System.out.println("3 - Empresa");
            tipoReg = escolha.nextInt();
        }
        
        escolha.nextLine();
        
        System.out.println("Insira o email desejado");
        user = escolha.nextLine();

        while (umer.verificarMail(user)) {
            System.out.println("Email já em uso");
            System.out.println("Insira o email desejado");
            user = escolha.nextLine();
        }

        System.out.println("Insira o seu nome");
        nome = escolha.nextLine();
        System.out.println("Insira a sua data de nascimento com o seguinte formato YYYY-MM-DD");
        data = escolha.nextLine();
        datapartida = data.split("-");

        while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2) {
            System.out.println("Por favor use o formato descrito");
            System.out.println("Insira a sua data de nascimento com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            datapartida = data.split("-");
        }
        
        int ano = Integer.parseInt(datapartida[0]);
        int mes = Integer.parseInt(datapartida[1]);
        int dia = Integer.parseInt(datapartida[2]);
        LocalDate dataFinal = LocalDate.of(ano, mes, dia);

        System.out.println("Insira a sua morada");
        morada = escolha.nextLine();
        System.out.println("Insira a password desejada");
        password = escolha.nextLine();
        
        
        umer.register(user, nome, password, morada, dataFinal, tipoReg);
        
        menuPrinc();
    }
    
    public void verViagens(){
        escolha.nextLine();
        ArrayList<Viagem> viagens = new ArrayList<>();
        System.out.println("Insira a data incial da procura com o seguinte formato YYYY-MM-DD");
        String data = escolha.nextLine();
        String[] datapartida = data.split("-");

        while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2) {
            System.out.println("Por favor use o formato descrito");
            System.out.println("Insira a data incial da procura com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            datapartida = data.split("-");
        }
        
        System.out.println("Insira a data final da procura com o seguinte formato YYYY-MM-DD");
        data = escolha.nextLine();
        String[] datapartida2 = data.split("-");

        while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2) {
            System.out.println("Por favor use o formato descrito");
            System.out.println("Insira a data final da procura com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            datapartida2 = data.split("-");
        }
        
        int ano = Integer.parseInt(datapartida[0]);
        int mes = Integer.parseInt(datapartida[1]);
        int dia = Integer.parseInt(datapartida[2]);
        LocalDate dataInicial = LocalDate.of(ano, mes, dia);
        ano = Integer.parseInt(datapartida2[0]);
        mes = Integer.parseInt(datapartida2[1]);
        dia = Integer.parseInt(datapartida2[2]);
        LocalDate dataFinal = LocalDate.of(ano,mes,dia);
        viagens.addAll(umer.procuraEntreDatas(dataInicial, dataFinal));
        System.out.println(dataInicial.toString());
        System.out.println(dataFinal.toString());
        
        for(Viagem v : viagens){
            System.out.println();
            System.out.println(v.toString());
        }
        System.out.println();
    }
    
    
    public void registarNovaViatura(){
        String matricula = "";
        int velMediaKm, precoPorKM, flagRep = 1;
        int i=0;
        int x, y, res, tipoVeiculo = 0;
        Coords c;
        escolha.nextLine();
        while(tipoVeiculo > 3 || tipoVeiculo<1){
            if (tipoVeiculo != 0) System.out.println("Opção inválida!");
            System.out.println("Qual o tipo de registo?");
            System.out.println("1 - Ligeiro");
            System.out.println("2 - Mota");
            System.out.println("3 - NoveLugares");
            tipoVeiculo = escolha.nextInt();
        }
        escolha.nextLine();
        while(flagRep == 1 || matricula.length() ==0 ){
            if(i!=0) System.out.println("Veiculo já registado no sistema");
            System.out.println("Insira a matricula do veiculo no formato XX-XX-XX");
            matricula=escolha.nextLine();
            flagRep = umer.verificarMatricula(matricula);
            i++;
        }
        System.out.println("Insira a velocidade média do veiculo");
        velMediaKm = escolha.nextInt();
        System.out.println("Insira o preço que irá cobrar por KM");
        precoPorKM = escolha.nextInt();
        System.out.println("Insira a coordenada X onde se encontra o veiculo");
        x = escolha.nextInt();
        System.out.println("Insira a coordenada Y onde se encontra o veiculo");
        y = escolha.nextInt();
        c = new Coords(x,y);
        
        res = umer.registarNovaViatura(matricula, tipoVeiculo, velMediaKm, precoPorKM, c);
        if (res == 1 || res == 2) System.out.println("Registo Efectuado com sucesso");
        else System.out.println("Ocorreu um erro durante o registo");
    }
    
    public void associarAViatura(){
        String matricula;
        int flag;
        escolha.nextLine();
        System.out.println("Insira a matricula do veiculo no formato XX-XX-XX");
        matricula = escolha.nextLine();
        flag = umer.motoristaAssociarAVeiculoDaEmpresa(matricula);
        switch(flag){
            case 1:
                System.out.println("Associado com sucesso à viatura de matricula " + matricula);
                break;
            case 0:
                System.out.println("O veiculo de matricula " + matricula + "não está associado à empresa");
                break;
            case -1:
                System.out.println("Não se pode associar a um veiculo ocupado");
                break;
        }
    }
    
    public void verFrota(){
        ArrayList<Veiculo> vS;
        vS = umer.verFrota();
        for(Veiculo v : vS){
            System.out.println(v.toString());
        }
    }
    
    public void estatistica(){
        String pass;
        escolha.nextLine();
        System.out.println("Porfavor Insira a password para aceder às estatisticas");
        pass = escolha.nextLine();
        if(pass.equals("password")) menuEstatistica();
    }
    
    public void top10Clientes(){
        Map<Cliente,Double> sortedMap;
        int n = 0;
        sortedMap = umer.top10clientes();
        Iterator<Map.Entry<Cliente, Double>> it = sortedMap.entrySet().iterator();
        //while(it.hasNext() && n < 10){
        //    System.out.println(it.next().getKey().getNome() + " " + it.next().getValue() + "\n");
        //    n++;
        //}
        for (Map.Entry<Cliente, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
            n++;
            if (n>10) break;
        }
        
    }
    
    public void pioresCondutores(){
        int n = 0;
        Map<Motorista,Double> sortedMap; 
        sortedMap = umer.piores5condutores();
        Iterator<Map.Entry<Motorista,Double>> it = sortedMap.entrySet().iterator();
        //while(it.hasNext() && n < 5){
        //    System.out.println(it.next().getKey().getNome() + " " + it.next().getValue() + "\n");
        //    n++;
        //}
        for (Map.Entry<Motorista, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
            n++;
            if (n>5) break;
        }
    }
    
    public void mudarEstadoMotorista(){
        String estado;
        estado = umer.trocaEstadoMotorista();
        System.out.println("Encontra-se neste momento " + estado);
    }
    
    public void libertarCarro(){
        String estado;
        estado = umer.libertarCarro();
        System.out.println(estado);
    }
    
    public void posicaoInicialCliente(int flag){
        int x, y;
        Coords c;
        System.out.println("Diga a posição X onde se encontra");
        x = escolha.nextInt();
        System.out.println("Diga a posição Y onde se encontra");
        y = escolha.nextInt();
        c = new Coords(x,y);
        if(flag == 6)escolherCondutor(c);
        else condutorMaisProximo(c);
    }
    
    public Coords posicaoDestino(){
        int x, y;
        Coords c;
        System.out.println("Diga a posição X do destino");
        x = escolha.nextInt();
        System.out.println("Diga a posição Y do destino");
        y = escolha.nextInt();
        c = new Coords(x,y);
        return c;
    }
    
    public void condutorMaisProximo(Coords c){
        Map<Veiculo,Double> sortedMap;
        Coords f;
        int n=1;
        String matricula = " ";
        Veiculo v;
        
        sortedMap = umer.viaturasProx(c);
        
        for (Map.Entry<Veiculo, Double> e : sortedMap.entrySet()) {  
            System.out.println(e.getKey().toStringCliente() + " " + e.getValue() + "\n");
            matricula = e.getKey().getMatricula();
            n++;
            if (n>1) break;
        }
        f = posicaoDestino();
        umer.realizarViagem(matricula,c,f);
    }
    
    public void escolherCondutor(Coords c){
        Coords f;
        Map<Veiculo,Double> sortedMap;
        String matricula = " ";
        int n=1, flag = 0;
        sortedMap = umer.viaturasProx(c);
        Iterator<Map.Entry<Veiculo,Double>> it = sortedMap.entrySet().iterator();
        System.out.println("Os 5 condutores mais próximos de si são: ");
        for (Map.Entry<Veiculo, Double> e : sortedMap.entrySet()) {
            System.out.println(n + ": ");
            System.out.println(e.getKey().toStringCliente() + " " + e.getValue() + "\n");
            n++;
            if(n>5) break;
        }
        escolha.nextLine();
        while(flag != 1){
            if (flag == 2) System.out.println("Matricula inserida está errada");
            System.out.println("Insira a matricula do veiculo que pretende escolher no formato XX-XX-XX");
            matricula = escolha.nextLine();
            flag = umer.verificarMatricula(matricula);
            if (flag == 0) flag = 2;
        }
        f = posicaoDestino();
        umer.realizarViagem(matricula,c,f);
    }
    
    public void totalFacturadoEmpresa(int flag){
        String empresa = "",matricula = "", data;
        double total;
        
        if (flag == 1){ System.out.println("Insira o nome da empresa que quer ver o lucro"); empresa = escolha.next() + "@gmail.com"; }
        
        else {System.out.println("Insira a matricula da viatura que quer ver o lucro"); matricula = escolha.next();}
        
        escolha.nextLine();
        if (umer.verificarMail(empresa) || umer.verificarMatricula(matricula) == 1) {
            System.out.println("Insira a data incial da procura com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            String[] datapartida = data.split("-");
    
            while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2) {
                System.out.println("Por favor use o formato descrito");
                System.out.println("Insira a data incial da procura com o seguinte formato YYYY-MM-DD");
                data = escolha.nextLine();
                datapartida = data.split("-");
            }
            
            System.out.println("Insira a data final da procura com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            String[] datapartida2 = data.split("-");
    
            while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2) {
                System.out.println("Por favor use o formato descrito");
                System.out.println("Insira a data final da procura com o seguinte formato YYYY-MM-DD");
                data = escolha.nextLine();
                datapartida2 = data.split("-");
            }
            
            int ano = Integer.parseInt(datapartida[0]);
            int mes = Integer.parseInt(datapartida[1]);
            int dia = Integer.parseInt(datapartida[2]);
            LocalDate dataInicial = LocalDate.of(ano, mes, dia);
            ano = Integer.parseInt(datapartida2[0]);
            mes = Integer.parseInt(datapartida2[1]);
            dia = Integer.parseInt(datapartida2[2]);
            LocalDate dataFinal = LocalDate.of(ano,mes,dia);
            System.out.println(empresa);
            if (flag == 1) {total = umer.totalFaturado(empresa, dataInicial, dataFinal); System.out.println("O total facturado pelo empresa " + empresa + " foi de " + total);}
            else {total = umer.totalFaturadoVeiculo(matricula, dataInicial, dataFinal); System.out.println("O total facturado pela viatura " + matricula + " foi de " + total);}
        }
        else {if(flag == 1)System.out.println("Empresa não encontrada"); else System.out.println("Veiculo não encontrado");}
    }
}
