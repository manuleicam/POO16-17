
public class NoveLugares extends Veiculo
{
    public NoveLugares(){
        super();
    }
    
    public NoveLugares(String matricula, int velMediaKM, int precoPorKM, Coords posicao, Actor m){
        super(matricula, velMediaKM, precoPorKM, posicao, m);
    }
    
    public NoveLugares(String matricula, int velMediaKM, int precoPorKM, Coords posicao){
        super(matricula, velMediaKM, precoPorKM, posicao);
    }
}
