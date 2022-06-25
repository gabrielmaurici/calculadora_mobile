package com.example.calculadora;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import context.HistoricoResultados;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultadoTv, expressaoTv;
    Button botaoC, botaoAbreParentese, botaoFechaParentese;
    Button botaoDividir, botaoMultiplicar, botaoSomar, botaoDiminuir, botaoIgual;
    Button botao0, botao1, botao2, botao3, botao4, botao5, botao6, botao7, botao8, botao9;
    Button botaoApagar, botaoPonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultadoTv = findViewById(R.id.text_resultado);
        expressaoTv = findViewById(R.id.text_expressao);

        atribuirId(botaoC, R.id.button_C);
        atribuirId(botaoAbreParentese, R.id.button_abre_arentese);
        atribuirId(botaoFechaParentese, R.id.button_fecha_parentese);
        atribuirId(botaoDividir, R.id.button_divide);
        atribuirId(botaoMultiplicar, R.id.button_multiplica);
        atribuirId(botaoSomar, R.id.button_soma);
        atribuirId(botaoDiminuir, R.id.button_diminui);
        atribuirId(botaoIgual, R.id.button_igual);
        atribuirId(botao0, R.id.button_0);
        atribuirId(botao1, R.id.button_1);
        atribuirId(botao2, R.id.button_2);
        atribuirId(botao3, R.id.button_3);
        atribuirId(botao4, R.id.button_4);
        atribuirId(botao5, R.id.button_5);
        atribuirId(botao6, R.id.button_6);
        atribuirId(botao7, R.id.button_7);
        atribuirId(botao8, R.id.button_8);
        atribuirId(botao9, R.id.button_9);
        atribuirId(botaoApagar, R.id.button_apaga);
        atribuirId(botaoPonto, R.id.button_ponto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_calculadora:
                Toast.makeText(this, "Menu Calculadora", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_historico_resultados:
                Intent historicoTela = new Intent(this, HistoricoActivity.class);
                startActivity(historicoTela);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void atribuirId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button botao = (Button) view;
        String botaoText = botao.getText().toString();
        String expressaoParaCalcular = expressaoTv.getText().toString();

        if(botaoText.equals("=")) {
            calculaTrataResultado(expressaoParaCalcular);
            gravarResultado();
            return;
        }

        if(botaoText.equals("c")) {
            expressaoTv.setText("0");
            resultadoTv.setText("0");
            return;
        }

        if(botaoText.equals("Del")) {
            apagaUltimoDigito(expressaoParaCalcular);
            return;
        }

        expressaoParaCalcular = montaExpressao(botaoText, expressaoParaCalcular);

        expressaoTv.setText(expressaoParaCalcular);
    }

    @NonNull
    private String montaExpressao(String botaoText, String expressaoParaCalcular) {
        String verificaSeForZero = expressaoParaCalcular;
        expressaoParaCalcular += botaoText;

        if(expressaoParaCalcular.length() == 2 && verificaSeForZero.equals("0")) {
            expressaoParaCalcular = expressaoParaCalcular.substring(1);
        }
        return expressaoParaCalcular;
    }

    @Nullable
    private void apagaUltimoDigito(String expressaoParaCalcular) {
        if(expressaoParaCalcular.length() == 1) {
            expressaoTv.setText("0");

        } else {
            expressaoTv.setText(expressaoParaCalcular.substring(0, expressaoParaCalcular.length() - 1));
        }
    }

    private void calculaTrataResultado(String expressaoParaCalcular) {
        String resultado = calcularResultado(expressaoParaCalcular);

        if(!resultado.equals("Error"))
            resultadoTv.setText(resultado);
    }

    private String calcularResultado(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            return context
                    .evaluateString(scriptable, data, "Javascript", 1, null)
                    .toString();
        } catch(Exception e) {
            return "Error";
        }
    }

    private void gravarResultado() {
        String geraRegistro = expressaoTv.getText() + " = " + resultadoTv.getText();
        HistoricoResultados.GravarResultado(geraRegistro);
    }
}