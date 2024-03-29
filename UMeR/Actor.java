 import java.util.Date;
 import java.util.*;
 import java.time.LocalDate;
 import java.io.Serializable;

 public class Actor implements Serializable 
 {
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate nascimento;
    public ArrayList<Viagem> listaViagens;

    


    public Actor() {  
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        //this.nascimento = new LocalDate();  // no idea, inicializo uma data com cenas ja? acho que nao da para ter uma data sem nada
        this.listaViagens = new ArrayList<Viagem>();
    }

    public Actor(String email, String nome, String password, String morada, LocalDate nascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.nascimento = nascimento;
        this.listaViagens = new ArrayList<Viagem>();
    }
    
    public Actor(Actor a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();
        this.nascimento = a.getNascimento();
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getMorada() {
        return morada;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public ArrayList<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setListaViagens(ArrayList<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }
    
    


    public boolean logIn(String pass) {
        if (this.password.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean equals(Object actor){
        if(this == actor) return true;
        if ((actor == null) || (this.getClass() != actor.getClass())) return false;
        else {
            Actor a = (Actor) actor;
            return((this.nome.equals(a.getNome())) && (this.email.equals(a.getEmail())) && (this.password.equals(a.getPassword())) && (this.morada.equals(a.getMorada())) && (this.nascimento.equals(a.getNascimento())));
        }
    }
}