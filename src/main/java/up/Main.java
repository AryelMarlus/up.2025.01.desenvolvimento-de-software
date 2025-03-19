package up;

import up.patos.Pato;
import up.voo.VoarComAsas;
import up.voo.VoarComBalao;
import up.voo.VoarNoWay;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Pato abelardo = new Pato(new VoarComAsas());
        abelardo.voar();

        abelardo.setVoar(new VoarNoWay());
        abelardo.voar();

        abelardo.setVoar(new VoarComBalao());
        abelardo.voar();
    }
}