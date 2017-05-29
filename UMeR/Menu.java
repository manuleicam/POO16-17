
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    // variáveis de instância
    private List<String> opcoes;    
    private String[] menuPrinc = {"LogIn", "Registar"};
    private String[] menuCliente = {"Bla1", "Bla2", "Bla3", "Bla4"};
    private String[] menuMotorista = {"MM1","MM2"};
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
                menuCliente();
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

        System.out.println("Insira a sua morada");
        morada = escolha.nextLine();
        System.out.println("Insira a password desejada");
        password = escolha.nextLine();
        dados = user + "," + nome + "," + password + "," + morada + "," + datapartida[0] + "," + datapartida[1] + "," + datapartida[2];
        System.out.println(dados); //PRINT DADOS TESTE
        umer.register(dados);
        menuPrinc();
    }
    
}
