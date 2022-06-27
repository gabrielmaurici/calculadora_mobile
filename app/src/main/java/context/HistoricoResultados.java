package context;

import java.util.ArrayList;

public final class HistoricoResultados {
    private HistoricoResultados() { }

    private static ArrayList<String>Historico = new ArrayList<>();

    public static void GravarResultado(String resultado) {
        Historico.add(resultado);
    }

    public static ArrayList<String> RetornaHistorico() {
        return Historico;
    }
}
