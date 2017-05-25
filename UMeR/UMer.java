import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class UMer{
    
    private static HashMap<String,Actor> listaCliente = new HashMap<String,Actor>();
    private static Actor currentUser;
    


    public int login(String email, String pass)
    {
        Actor temp;
        temp = listaCliente.get(email);
        if (temp != null){
           if (temp.logIn(pass)){
                this.currentUser = temp;
                return 1; //ok
            }
            else
                return 0; //pass mal
        }
        else return -1;//nao existe user
    }


    public static int register (String tudoAoMolho){
        Actor temp;
        Date data = new Date();
        int dia, mes, ano;
        String[] lista = tudoAoMolho.split(",");

        // if (listaCliente.containsKey(lista[0])) return 0;

        ano = Integer.parseInt(lista[4]);
        data.setYear(ano);
        mes = Integer.parseInt(lista[5]);
        data.setMonth(mes);
        dia = Integer.parseInt(lista[6]);
        data.setDate(dia);

        temp = new Actor (lista[0]/*email*/, lista[1]/*nome*/, lista[2]/*pass*/, lista[3]/*morada*/, data);
        listaCliente.put(lista[0],temp);

        testeActor(lista[0]); //teste, remover!

        return 1;
    }

    public static void testeActor (String email){
        Actor ze = new Actor();
        if (listaCliente.containsKey(email)) System.out.println("asdasdasasdasddsa");
        ze = listaCliente.get(email);
        String nome = new String();
        nome = ze.getNome();
        System.out.println(nome);
    }
}
