
import java.util.Date;

public class Actor {

    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date nascimento;

    public Actor() {
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.nascimento = new Date();
    }

    public Actor(String email, String nome, String password, String morada, Date nascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.nascimento = nascimento;
    }
    
    public Actor(Actor a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPass();
        this.morada = a.getMorada();
        this.nascimento = a.getNascimento();
    }
    
    public String getEmail(){
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getPass() {
        return this.password;
    }

    public String getMorada() {
        return this.morada;
    }
    
    public Date getNascimento(){
        return this.nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPass(String pass) {
        this.password = pass;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public boolean logIn(String pass) {
        if (this.password.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }
    
    public Actor clone(){
        return new Actor(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nEmail: ");
        sb.append(this.email);
        sb.append("\nNome: ");
        sb.append(this.nome);
        sb.append("\nMorada: ");
        sb.append(this.morada);
        sb.append("\nDataNascimento: ");
        sb.append(this.nascimento);
        return sb.toString();
    }
    
    public boolean equals(Object actor){
        if(this == actor) return true;
        if ((actor == null) || (this.getClass() != actor.getClass())) return false;
        else {
            Actor a = (Actor) actor;
            return((this.nome.equals(a.getNome())) && (this.email.equals(a.getEmail())) && (this.password.equals(a.getPass())) && (this.morada.equals(a.getMorada())) && (this.nascimento.equals(a.getNascimento())));
        }
    }
}
