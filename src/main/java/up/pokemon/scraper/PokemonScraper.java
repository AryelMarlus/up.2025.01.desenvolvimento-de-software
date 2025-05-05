package up.pokemon.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonScraper {

    private static final String URL = "https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_base_stats_in_Generation_I";
    private static final String CSV_FILE = "pokemon_stats_gen1.csv";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(URL).get();
            Element table = doc.selectFirst("table.roundy"); // Encontra a primeira tabela com a classe "roundy"

            if (table != null) {
                Elements rows = table.select("tbody tr");
                List<Map<String, String>> pokemonData = new ArrayList<>();
                String[] headers = {"Pokémon", "HP", "Attack", "Defense", "Speed", "Special"};

                // Começa da segunda linha para ignorar o cabeçalho principal
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    // Verifica se a linha tem o número correto de colunas de dados
                    if (cols.size() == 6) {
                        Map<String, String> pokemon = new HashMap<>();
                        pokemon.put(headers[0], cols.get(1).text().trim()); // Nome do Pokémon
                        pokemon.put(headers[1], cols.get(2).text().trim()); // HP
                        pokemon.put(headers[2], cols.get(3).text().trim()); // Attack
                        pokemon.put(headers[3], cols.get(4).text().trim()); // Defense
                        pokemon.put(headers[4], cols.get(5).text().trim()); // Speed
                        pokemon.put(headers[5], cols.get(6).text().trim()); // Special (na Geração I era um stat único)
                        pokemonData.add(pokemon);
                    }
                }

                // Escrever para o arquivo CSV
                try (FileWriter writer = new FileWriter(CSV_FILE)) {
                    // Escrever o cabeçalho
                    writer.write(String.join(",", headers) + "\n");

                    // Escrever os dados dos Pokémon
                    for (Map<String, String> pokemon : pokemonData) {
                        writer.write(pokemon.get(headers[0]) + "," +
                                pokemon.get(headers[1]) + "," +
                                pokemon.get(headers[2]) + "," +
                                pokemon.get(headers[3]) + "," +
                                pokemon.get(headers[4]) + "," +
                                pokemon.get(headers[5]) + "\n");
                    }
                    System.out.println("Dados dos Pokémon da Geração I salvos em " + CSV_FILE);

                } catch (IOException e) {
                    System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
                }

            } else {
                System.err.println("Tabela de dados não encontrada na página.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao acessar a URL: " + e.getMessage());
        }
    }
}