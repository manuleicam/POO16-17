import java.util.*;

public class Motorista extends Actor
{
    private int grauCumpHorario; 
    private int classificacao;
    private int NrViagens;
    private int numKMs;
    private String estado;
    private ArrayList<Viagem> viagens;//Fazer uma lista central (hash) e guarda so os id's da viagem aqui
}
