package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import records.SteamID;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiCheapShark {
    public SteamID obterDadosCheapShark(String nomeJogo) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String nomeJogoEncoded = URLEncoder.encode(nomeJogo, StandardCharsets.UTF_8);
        String url = "https://www.cheapshark.com/api/1.0/games?title="+nomeJogoEncoded;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());


        SteamID[] jogos = mapper.readValue(response.body(), SteamID[].class);

        if (jogos.length>0){
            return jogos[0];
        }else{
            System.out.println("Nenhum Jogo encontrado!");
            return null;
        }
    }
}
