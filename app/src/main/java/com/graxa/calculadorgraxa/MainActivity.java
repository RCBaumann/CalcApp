package com.graxa.calculadorgraxa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,
            numeroSete,numeroOito,numeroNove,ponto,soma,subtracao,divisao,limpar,multiplicacao,igual;

    private TextView txtResultado,txtExpressao;
    private ImageView backspace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
        getSupportActionBar().hide();
    }

    private void iniciarComponentes(){
        ponto         = findViewById(R.id.ponto);
        numeroZero    = findViewById(R.id.numero_zero);
        backspace     = findViewById(R.id.backspace);
        igual         = findViewById(R.id.btn_igual);
        numeroUm      = findViewById(R.id.numero_um);
        numeroDois    = findViewById(R.id.numero_dois);
        numeroTres    = findViewById(R.id.numero_tres);
        soma          = findViewById(R.id.btn_soma);
        numeroQuatro  = findViewById(R.id.numero_quatro);
        numeroCinco   = findViewById(R.id.numero_cinco);
        numeroSeis    = findViewById(R.id.numero_seis);
        subtracao     = findViewById(R.id.btn_subtracao);
        numeroSete    = findViewById(R.id.numero_sete);
        numeroOito    = findViewById(R.id.numero_oito);
        numeroNove    = findViewById(R.id.numero_nove);
        multiplicacao = findViewById(R.id.btn_multiplicacao);
        limpar        = findViewById(R.id.btn_limpar);
        divisao       = findViewById(R.id.btn_divisao);
        txtExpressao  = findViewById(R.id.txt_expressao);
        txtResultado  = findViewById(R.id.txt_resultado);
    }

    public void addExpressao(String insert_num, boolean clear_inf) {
        if (txtResultado.getText().equals("")) {
            txtExpressao.setText("");
        }
        if (clear_inf) {
            txtResultado.setText("");
            txtExpressao.append(insert_num);
        }else {
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(insert_num);
            txtResultado.setText("");
        }
    }

    @Override
    public void onClick(View view) {

    }
}