import api.ApiCheapShark;
import api.ApiSteam;
import records.Jogo;
import records.SteamID;
import service.Calculos;
import service.Sorteador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Calculos calculos = new Calculos();
        ApiCheapShark apiCheapShark = new ApiCheapShark();
        ApiSteam apiSteam = new ApiSteam();
        Sorteador sorteador = new Sorteador();

        Double saldoSteam = 0.0;

        while (true){
            try{
                System.out.print("Insira seu saldo na Steam: ");
                saldoSteam = scanner.nextDouble();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e){
                System.out.println("Valor inválido, insira apenas números (ex: 40,99).");
                scanner.nextLine();
            }
        }

        List<Jogo> carrinhoJogos = new ArrayList<>();

        while (true){
            System.out.print("Insira o jogo que deseja comprar: ");
            String jogoDesejado = scanner.nextLine();

            SteamID idJogo = apiCheapShark.obterDadosCheapShark(jogoDesejado);

            if (idJogo == null){
                System.out.println("❌ Jogo não encontrado, insira novamente: ");
                continue;
            }

            Double precoJogo = apiSteam.obterDadosSteam(idJogo.idSteam());


            Jogo jogo = new Jogo(idJogo.titulo(), precoJogo, idJogo.idSteam());
            System.out.printf("✅ Jogo encontrado: %s\n", jogo.toString());
            carrinhoJogos.add(jogo);

            System.out.print("Quer adicionar mais um jogo? (s/n) ");
            String resposta = scanner.nextLine().toLowerCase();
            if (resposta.equals("n")){
                break;
            }
        }

        System.out.println();
        System.out.println("O seu carrinho de compras ficou assim: ");
        carrinhoJogos.forEach(System.out::println);

        System.out.println();
        calculos.quantoSobra(saldoSteam, carrinhoJogos);

        System.out.println();
        System.out.println("Quer sortear um jogo do carrinho pra comprar gagora? (s/n) ");
        String res = scanner.nextLine().toLowerCase().substring(0, 1);
        if (res.equals("s")){
            System.out.println(sorteador.sorteador(carrinhoJogos));
        }
    }
}
