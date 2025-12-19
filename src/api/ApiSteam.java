package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import records.DetalhesJogo;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiSteam {
    public Double obterDadosSteam(String steamID) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String steamIDEncoded = URLEncoder.encode(steamID, StandardCharsets.UTF_8);
        String url = "https://store.steampowered.com/api/appdetails?appids="+steamIDEncoded+"&cc=pt";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        var jsonArv = mapper.readTree(response.body());
        var nodePreco = jsonArv.path(steamID)
                               .path("data")
                               .path("price_overview");

        if ((!nodePreco.isMissingNode())){
            DetalhesJogo detalhesJogo = mapper.treeToValue(nodePreco, DetalhesJogo.class);

            return (double) (detalhesJogo.precoJogo()/100.00);
        }else {
            System.out.println("Jogo grátis ou sem preço!");
            return 0.0;
        }
    }

}
