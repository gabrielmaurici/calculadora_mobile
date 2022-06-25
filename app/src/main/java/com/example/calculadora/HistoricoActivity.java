package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import context.HistoricoResultados;

public class HistoricoActivity extends AppCompatActivity implements View.OnClickListener {

    Button botaoVoltar;
    ListView listViewHistoricoResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        atribuirId(botaoVoltar, R.id.voltar);

        listViewHistoricoResultados = findViewById(R.id.lista_historico);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.texto_listview, HistoricoResultados.Historico
        );

        listViewHistoricoResultados.setAdapter(adapter);
    }

    void atribuirId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}