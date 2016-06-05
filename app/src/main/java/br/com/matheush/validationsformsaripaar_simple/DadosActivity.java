package br.com.matheush.validationsformsaripaar_simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class DadosActivity extends AppCompatActivity {


    private TextView txvNome;
    private TextView txvEmail;
    private TextView txvFone;
    private TextView txvSenha;

    ArrayList<String> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        txvNome = (TextView) findViewById(R.id.txvNome);
        txvEmail = (TextView) findViewById(R.id.txvEmail);
        txvFone = (TextView) findViewById(R.id.txvFone);
        txvSenha = (TextView) findViewById(R.id.txvSenha);

        dados = new ArrayList<String>();

        dados = intent.getStringArrayListExtra("dados");

        String nome = dados.get(0).toString();
        String email = dados.get(1).toString();
        String fone = dados.get(2).toString();
        String senha = dados.get(3).toString();

        txvNome.setText(nome);
        txvEmail.setText(email);
        txvFone.setText(fone);
        txvSenha.setText(senha);
    }

}
