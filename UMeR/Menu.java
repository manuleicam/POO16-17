
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

public class Menu {
    // variáveis de instância
    private List<String> opcoes;
    private String[] menuPrinc = {"LogIn", "Registar", "Estatistica"};
    private String[] menuCliente = {"Realizar Viagem", "Ver Viagens Efectuadas", "Bla3", "Bla4"};
    private String[] menuViagem = {"Escolher viatura", "Viatura mais próxima"};
    private String[] menuEstatistica = {"Top 10 clientes gastadores", "Piores 5 motoristas"};
    private String[] menuMotoristaComEmpresa = {"Associar-se a uma viatura","Ver Viagens Efectuadas", "Mudar o estado"};
    private String[] menuMotoristaPrivado = {"Registar Nova Viatura","Associar-se a uma empresa", "Ver Viagens Efectuadas", "Mudar o estado"};
    private String[] menuEmpresa = {"Registar Nova Viatura","Ver Frota", "Ver Viagens Efectuadas"};
    
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
        menuPrinc();
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
                    escolherCondutor();
                    System.out.print("1");
                    break;
                case 2:
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
                    System.out.print("1");
                    break;
                case 2:
                    pioresCondutores();
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
                    System.out.println("1");
                    registarNovaViatura();
                    break;
                case 2:
                    verFrota();
                    break;
                case 3:
                    System.out.println("2");
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
        System.out.println("Insira o seu user");
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
            System.out.println("Insira o seu user");
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
    
    public void escolherCondutor(){
        int x, y;
        System.out.println("Insira a coordenada X onde se encontra");
        x = escolha.nextInt();
        System.out.println("Insira a coordenada Y onde se encontra");
        y = escolha.nextInt();
        umer.top5CondutoresMaisPerto(x, y);
        System.out.println("Insira o email do condutor que deseja");
    }
    
    public void registarNovaViatura(){
        String matricula;
        int velMediaKm, precoPorKM;
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
        System.out.println("Insira a matricula do veiculo no formato XX-XX-XX");
        matricula=escolha.nextLine();
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
        while(it.hasNext() && n < 10){
            System.out.println(it.next().getKey().getNome() + " " + it.next().getValue() + "\n");
            n++;
        }
        
    }
    
    public void pioresCondutores(){
        int n = 0;
        Map<Motorista,Double> sortedMap; 
        sortedMap = umer.piores5condutores();
        Iterator<Map.Entry<Motorista,Double>> it = sortedMap.entrySet().iterator();
        while(it.hasNext() && n < 5){
            System.out.println(it.next().getKey().getNome() + " " + it.next().getValue() + "\n");
        }
        //for (Map.Entry<Motorista, Double> e : sortedMap.entrySet()) {  
        //    System.out.println(e.getKey().getNome() + " " + e.getValue() + "\n");
        //}
    }
    
    public void mudarEstadoMotorista(){
        String estado;
        estado = umer.trocaEstadoMotorista();
        System.out.println("Encontra-se neste momento " + estado);
    }
}
