import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;


public class UMer{
    
    private HashMap<String,Actor> listaCliente = new HashMap<String,Actor>();
    private HashMap<String,Veiculo> listaVeiculo= new HashMap<String,Veiculo>();
    private Actor currentUser;
    
    public UMer(){
        Date da = new Date(2000,01,01);
        Date db = new Date(1994,06,8); //08 é muito grande diz ele ??
        Date dc = new Date(1995,01,20);
        
        Actor a = new Actor("jonas@gmail.com","joao","jo","todoolado",da);
        Actor b = new Actor("rui@gmail.com","rui","321","nunca",db);
        Actor c = new Actor("manu@gmail.com","manu","123","wow",dc);
        
        listaCliente.put("jonas@gmail.com",a); // cuidado que este é sensivel
        listaCliente.put("rui@gmail.com", b);
        listaCliente.put("manu@gmail.com", c);
               
    }
    


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


    public int register (String tudoAoMolho){
        Actor temp;
        Date data = new Date();
        int dia, mes, ano;
        String[] lista = tudoAoMolho.split(",");

        if (listaCliente.containsKey(lista[0])) return 0;

        ano = Integer.parseInt(lista[4]);
        data.setYear(ano);
        mes = Integer.parseInt(lista[5]);
        data.setMonth(mes);
        dia = Integer.parseInt(lista[6]);
        data.setDate(dia);

        temp = new Actor (lista[0]/*email*/, lista[1]/*nome*/, lista[2]/*pass*/, lista[3]/*morada*/, data);
        listaCliente.put(lista[0],temp);

        return 1;
    }
    
    public boolean verificarMail(String email){
        if (listaCliente.containsKey(email)) return true;
        return false;
    }

    public void testeActor (String email){
        Actor ze = new Actor();
        if (listaCliente.containsKey(email)) System.out.println("asdasdasasdasddsa");
        ze = listaCliente.get(email);
        String nome = new String();
        nome = ze.getNome();
        System.out.println(nome);
    }
}
