package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import context.HistoricoResultados;

public class HistoricoActivity extends AppCompatActivity implements View.OnClickListener {

    Button botaoVoltar;
    ListView listViewHistoricoResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        atribuirId(botaoVoltar, R.id.voltar);

        listViewHistoricoResultados = findViewById(R.id.lista_historico); // Linka listView com id listView do layout

        ArrayList<String> historico = HistoricoResultados.RetornaHistorico(); // Recupera lista de histórico de calculos
        Collections.reverse(historico); // Classe Collections importada para uso do método reverse para listar histórico na ordem decrescente

        ArrayAdapter<String> adapter = new ArrayAdapter<>( // Cria adapter para setar histórico no listview com layout definido
                this, R.layout.texto_listview, historico
        );

        listViewHistoricoResultados.setAdapter(adapter);
    }

    // Método para atribuir o botão ao id do botão definido no layout
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