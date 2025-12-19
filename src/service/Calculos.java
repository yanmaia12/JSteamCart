package service;

import records.Jogo;

import java.util.List;
import java.util.Scanner;

public class Calculos {
    public void quantoSobra(Double saldo, List<Jogo> jogos){
        Scanner scanner = new Scanner(System.in);
        while (true){
            double precoTotal = jogos.stream()
                    .mapToDouble(Jogo::preco)
                    .sum();

            double diferenca = saldo - precoTotal;

            if (diferenca>0){
                System.out.println("Você consegue comprar todos esses jogos e ainda sobra € %.2f!".formatted(diferenca));
                break;
            }else if (diferenca==0){
                System.out.println("Você consegue comprar todos esses jogos mas não sobra nada!");
                break;
            }else{

                System.out.println("Infelizmente você não consegue comprar todos esses jogos, você precisaria de mais € %.2f para comprar todos!".formatted(Math.abs(diferenca)));
                System.out.println("Considere remover um desses jogos: ");
                jogos.forEach(System.out::println);
                System.out.println();

                System.out.print("Qual jogo deseja remover? (digite o nome) ");
                String jogoRemovido = scanner.nextLine();

                boolean removeu =  jogos.removeIf(j -> j.titulo().equalsIgnoreCase(jogoRemovido));

                if (removeu){
                    System.out.println("Jogo removido!");
                    System.out.println();
                }else {
                    System.out.println("O jogo não foi encontrado, digite o nome igual está na lista!");
                    System.out.println();
                }

                if (jogos.isEmpty()){
                    System.out.println("O carrinho agora está vazio!");
                    break;
                }
            }
        }




    }
}
