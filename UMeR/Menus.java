
import java.util.Scanner;

public class Menus {

    public static Scanner escolha = new Scanner(System.in);
    public static int esc;

    public static void main(String args[]) {
        inicial();
    }

    public static String inicial() {
        String dados = "";
        System.out.println("#######################");
        System.out.println("BEM VINDO AO SISTEMA");
        System.out.println("Escolha uma das opções a baixo");
        System.out.println("1-LogIn");
        System.out.println("2-Registar");
        esc = escolha.nextInt();
        switch (esc) {
            case 1:
                System.out.println("1");
                dados = logIN();
                break;
            case 2:
                System.out.println("2");
                dados = registar();
                break;
        }
        
        return dados;

    }

    public static String logIN() {
        String user, dados, password = " ";
        
        escolha.nextLine();
        System.out.println("Insira o seu user");
        user = escolha.nextLine();
        if (user == "\n") System.out.println("ENTER");
        System.out.println("Insira a sua password");
        password = escolha.nextLine();
        dados = user + "," + password;
        System.out.println(dados);
        return dados;
    }
    
    public static String registar(){
        String user, password, dados, morada, data, nome;
        String[] datapartida;
        
        escolha.nextLine();
        System.out.println("Insira o email desejado");
        user = escolha.nextLine();
        System.out.println("Insira o seu nome");
        nome = escolha.nextLine();
        System.out.println("Insira a sua data de nascimento com o seguinte formato YYYY-MM-DD");
        data = escolha.nextLine();
        datapartida = data.split("-");
        
        while (data.length() != 10 || datapartida[0].length() != 4 || datapartida[1].length() != 2 || datapartida[2].length() != 2){
            System.out.println("Por favor use o formato descrito");
            System.out.println("Insira a sua data de nascimento com o seguinte formato YYYY-MM-DD");
            data = escolha.nextLine();
            datapartida = data.split("-");
        }
        
        System.out.println("Insira a sua morada");
        morada = escolha.nextLine();
        System.out.println("Insira a password desejada");
        password = escolha.nextLine();
        dados = user + ","  + nome + "," + password + "," + morada + "," + datapartida[0] + "," + datapartida[1] + "," + datapartida[2];
        System.out.println(dados); //PRINT DADOS TESTE
        return dados;
    }

    public static void menu1() {
        System.out.println("#######################");
        System.out.println("BEM VINDO AO SISTEMA");
        System.out.println("Escolha uma das opções a baixo");
        System.out.println("1- Menu 1");
        System.out.println("2- Menu 2");
        System.out.println("3- Menu 3");
        System.out.println("0- Sair ");

        esc = escolha.nextInt();
        switch (esc) {
            case 1:
                System.out.println("1");
                menu1();
                break;
            case 2:
                System.out.println("2");
                menu1();
                break;
            case 3:
                System.out.println("3");
                menu1();
                break;
            case 0:
                System.out.println("Adeus!");
                break;
            default:
                System.out.println("Insira um do números presentes");
                menu1();
                break;
        }
    }
}
