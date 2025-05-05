package up.strategy.patos;

import up.strategy.voo.Voar;

public class Pato {
    private Voar voar;

    public Pato(Voar voo){
        this.voar = voo;
    }

    public void voar(){
        this.voar.executarVoo();
    }

    public void setVoar(Voar voar) {
        this.voar = voar;
    }
}
