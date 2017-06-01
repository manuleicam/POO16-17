
/**
 * Escreva a descrição da classe motos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Motos extends Veiculo
{
    public Motos(){
        super();
    }
    
    public Motos(String matricula, int velMediaKM, int precoPorKM, Coords posicao){
        super(matricula, velMediaKM, precoPorKM, posicao);
    }
    
    public Motos(String matricula, int velMediaKM, int precoPorKM, Coords posicao, Actor m){
        super(matricula, velMediaKM, precoPorKM, posicao, m);
    }
}
