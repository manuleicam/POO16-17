
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
import java.time.LocalDate;

public class Menu {
    // variáveis de instância
    private List<String> opcoes;
    private String[] menuPrinc = {"LogIn", "Registar"};
    private String[] menuCliente = {"Realizar Viagem", "Ver Viagens Efectuadas", "Bla3", "Bla4"};
    private String[] menuViagem = {"Escolher viatura", "Viatura mais próxima"};
    private String[] menuMotorista = {"MM1","MM2"};
    private String[] menuMotoristaPrivado = {"sss","sss"};
    private String[] menuEmpresa = {"em1","em2"};
    
    private int op, esc;
    public UMer umer;
    public Scanner escolha; 
    
    public Menu(){
        escolha = new Scanner(System.in);
        umer = new UMer();
    }

    public static void main(String[] args){
        new Menu().run();
    }
    
    public void run(){
        menuPrinc();
    }
    
    public void menuPrinc(){
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
            case 0:
                System.out.println("Adeus!");
                break;
        }
    }
    
    public void menuCliente(){
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
        }
    }
    
    public void menuViagem(){
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
        }
    }
    
    public void menuMotorista(){
        setOpcoes(menuMotorista);
        executa();
        esc = getOpcao();
        switch(esc){
            case 1: 
                System.out.println("1");
                menuCliente();
                break;
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
        int flagLog;

        escolha.nextLine();
        System.out.println("Insira o seu user");
        user = escolha.nextLine();

        System.out.println("Insira a sua password");
        password = escolha.nextLine();

        flagLog = umer.login(user, password);
        while (flagLog != 1) {
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
        menuCliente();

    }

    public void registar() {
        String user, password, dados, morada, data, nome;
        String[] datapartida;
        int tipoReg = 0, flagTipo = 0;

        
        
        while(tipoReg > 2 || tipoReg<1){
            if (flagTipo != 0) System.out.println("Opção inválida!");
            System.out.println("Qual o tipo de registo?");
            System.out.println("1 - Cliente");
            System.out.println("2 - Motorista");
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
        Date dataInicial = new Date(ano, mes, dia);
        ano = Integer.parseInt(datapartida2[0]);
        mes = Integer.parseInt(datapartida2[1]);
        dia = Integer.parseInt(datapartida2[2]);
        Date dataFinal = new Date(ano,mes,dia);
        //viagens.addAll(umer.procuraEntreDatas(dataInicial, dataFinal));
        System.out.println(dataInicial.toString());
        System.out.println(dataFinal.toString());
        
        for(Viagem v : viagens){
            System.out.println(v.toString());
        }
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
}
