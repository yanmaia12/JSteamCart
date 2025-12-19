package records;

public record Jogo(String titulo, Double preco, String steamID) {
    @Override
    public String toString() {
        return "%s - € %.2f".formatted(titulo, preco);
    }
}
