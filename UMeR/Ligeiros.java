
public class Ligeiros extends Veiculo
{
    public Ligeiros(){
        super();
    }
    
    public Ligeiros(String matricula, int velMediaKM, int precoPorKM, Coords posicao, Actor m){
        super(matricula, velMediaKM, precoPorKM, posicao, m);
    }
    
    public Ligeiros(String matricula, int velMediaKM, int precoPorKM, Coords posicao){
        super(matricula, velMediaKM, precoPorKM, posicao);
    }
}
