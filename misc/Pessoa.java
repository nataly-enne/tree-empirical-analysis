package misc;

public class Pessoa implements Indexable {

    private int chave;

    public Pessoa(int chave) {
        this.chave = chave;
    }

    @Override
    public int getKey() {
        return chave;
    }
}
