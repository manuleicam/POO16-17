import java.util.Scanner;

public class Menus
{
    public static void main(String args[]){
        menu1();
    }
    
    public static void menu1(){
        int esc;
        System.out.println("#######################");
        System.out.println("BEM VINDO AO SISTEMA");
        System.out.println("Escolha uma das opções a baixo");
        System.out.println("1- Menu 1");
        System.out.println("2- Menu 2");
        System.out.println("3- Menu 3");
        System.out.println("0- Sair ");
        Scanner escolha = new Scanner(System.in);
        esc = escolha.nextInt();
        switch(esc){
            case 1: System.out.println("1");
                    menu1();
                    break;
            case 2: System.out.println("2");
                    menu1();
                    break;
            case 3: System.out.println("3");
                    menu1();
                    break;
            case 0: System.out.println("Adeus!");
                    break;
            default: System.out.println("Insira um do números presentes");
                    menu1();
                    break;
        }
    }
}
