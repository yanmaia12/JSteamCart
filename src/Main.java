import api.ApiCheapShark;
import api.ApiSteam;
import records.Jogo;
import records.SteamID;
import service.Calculos;

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

    }
}
