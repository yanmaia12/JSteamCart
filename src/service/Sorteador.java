package service;

import records.Jogo;

import java.util.List;
import java.util.Random;

public class Sorteador {

    public Jogo sorteador(List<Jogo> jogos){
        if (jogos.isEmpty()){
            return null;
        }

        Random random = new Random();

        int sortear = random.nextInt(jogos.size());

        Jogo sorteado = jogos.get(sortear);

        return sorteado;
    }

}
