package com.graxa.calculadorgraxa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Criar objetos para recuperar os IDs
    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,
            numeroSete,numeroOito,numeroNove,ponto,soma,subtracao,divisao,limpar,multiplicacao,igual;

    private TextView txtResultado,txtExpressao;
    private ImageView backspace;

    int tipoNumero = 1;
    int tipoOperador = 2;
    int tipoSeparador = 3;

    int ultimoTipo = 0;

    boolean permiteNumero = true;
    boolean permiteOperador = true;
    boolean permiteSeparador = true;

    private void calculaResultado() {
        try {
            Expression expressao = new ExpressionBuilder(txtExpressao.getText().toString()).build();
            double resultado = expressao.evaluate();
            long longResult = (long) resultado;

            if (resultado == (double) longResult){
                txtResultado.setText(String.valueOf(longResult));
            } else {
                txtResultado.setText(String.valueOf(resultado));
            }

        } catch (Exception e){
            System.out.println("Erro de sintaxe");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        //Recupera os eventos de click
        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);

        limpar.setOnClickListener(view -> {

            txtExpressao.setText("");
            txtResultado.setText("");
            permiteNumero = true;
            permiteOperador = true;
            permiteSeparador = true;
            ultimoTipo = 0;
        });

        backspace.setOnClickListener(view -> {

            TextView expressao = findViewById(R.id.txt_expressao);
            String numeros = expressao.getText().toString();

            if (!numeros.isEmpty()) {

                int var0 = 0;
                int var1 = numeros.length()-1;

                String txtExpressao = numeros.substring(var0,var1);
                expressao.setText(txtExpressao);
            }
            txtResultado.setText("");
        });

        //botão de igual utliza a lib net.objecthunter para fazer as operações
        igual.setOnClickListener(view -> {
            txtExpressao.setText(txtResultado.getText().toString());
            txtResultado.setText("");

        });

    }

    //Vincula os botões aos ids do xml
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

    //Expressão é a conta em si
//    public void addExpressao(String insertNum, boolean clickBtn) {
//        if (txtResultado.getText().equals("")) {
//            txtExpressao.setText(" ");
//        }
//        if (clickBtn) {
//            txtResultado.setText(" ");
//            txtExpressao.append(insertNum);
//        }else {
//            txtExpressao.append(txtResultado.getText());
//            txtExpressao.append(insertNum);
//            txtResultado.setText(" ");
//        }
//
//        String valorSetado = String.valueOf(txtExpressao);
//        if(valorSetado.contains("++") || valorSetado.contains("--") || valorSetado.contains("**") || valorSetado.contains("//")){
//            txtExpressao.setText("");
//        }
//    }

    public void addExpressao(String insertNum, int clickBtn) {

        if (!txtResultado.getText().equals("")) {

            if (clickBtn == tipoNumero && permiteNumero) {
                txtExpressao.append(insertNum);
                permiteOperador = true;

            } else if (clickBtn == tipoOperador && permiteOperador) {
                txtExpressao.setText("");
                txtExpressao.append(txtResultado.getText());
                txtExpressao.append(insertNum);
                permiteOperador = false;
                permiteSeparador = true;


            } else if (clickBtn == tipoSeparador && permiteSeparador) {
                if(ultimoTipo != tipoNumero){
                    txtExpressao.append("0");
                }
                txtExpressao.append(insertNum);
                permiteSeparador = false;
            }

            txtResultado.setText("");

        } else {
            if (clickBtn == tipoNumero && permiteNumero) {
                txtExpressao.append(insertNum);
                permiteOperador = true;

            } else if (clickBtn == tipoOperador && permiteOperador) {
                if(ultimoTipo == tipoSeparador){

                    StringBuilder expressaoBuilder = new StringBuilder(txtExpressao.getText());
                    int lastIndex = expressaoBuilder.length()-1;

                    expressaoBuilder.deleteCharAt(lastIndex);
                    txtExpressao.setText(expressaoBuilder.toString());

                }
                txtExpressao.append(insertNum);
                permiteOperador = false;
                permiteSeparador = true;


            } else if (clickBtn == tipoSeparador && permiteSeparador) {
                if(ultimoTipo != tipoNumero){
                    txtExpressao.append("0");
                }
                txtExpressao.append(insertNum);
                permiteSeparador = false;
            }
        }
        ultimoTipo = clickBtn;
    }

    //Coleta o click dos botões e adiciona os valor
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.numero_zero:
                addExpressao("0", tipoNumero);
                break;
            case R.id.numero_um:
                addExpressao("1", tipoNumero);
                break;
            case R.id.numero_dois:
                addExpressao("2", tipoNumero);
                break;
            case R.id.numero_tres:
                addExpressao("3", tipoNumero);
                break;
            case R.id.numero_quatro:
                addExpressao("4", tipoNumero);
                break;
            case R.id.numero_cinco:
                addExpressao("5", tipoNumero);
                break;
            case R.id.numero_seis:
                addExpressao("6", tipoNumero);
                break;
            case R.id.numero_sete:
                addExpressao("7", tipoNumero);
                break;
            case R.id.numero_oito:
                addExpressao("8", tipoNumero);
                break;
            case R.id.numero_nove:
                addExpressao("9", tipoNumero);
                break;
            case R.id.ponto:
                addExpressao(".", tipoSeparador);
                break;
            case R.id.btn_soma:
                addExpressao("+", tipoOperador);
                break;
            case R.id.btn_subtracao:
                addExpressao("-", tipoOperador);
                break;
            case R.id.btn_multiplicacao:
                addExpressao("*", tipoOperador);
                break;
            case R.id.btn_divisao:
                addExpressao("/", tipoOperador);
                break;
        }
        calculaResultado();
    }
}