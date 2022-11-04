package com.graxa.calculadorgraxa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Criar objetos para recuperar os IDs
    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,
            numeroSete,numeroOito,numeroNove,ponto,soma,subtracao,divisao,limpar,multiplicacao,igual;

    private TextView txtResultado,txtExpressao;
    private ImageView backspace;

    int tipoNumero = 1;
    int tipoOperador = 2;
    int tipoSeparador = 3;

    private void calculaResultado() {
        try {
            String expressaoStr = txtExpressao.getText().toString();
            if(getUltimoTipo(expressaoStr) == tipoOperador){
                expressaoStr = expressaoStr.substring(0,expressaoStr.length()-1);
            }
            Expression expressao = new ExpressionBuilder(expressaoStr).build();
            double resultado = expressao.evaluate();
            double roundResultado = Math.round(resultado * 1000000000.0) / 1000000000.0;
            String resultadoStr = String.valueOf(roundResultado);
            String[] resultadoSplit = resultadoStr.split("[.]");
            String inteiro = resultadoSplit [0];
            String decimal = resultadoSplit [1];
            if(decimal.equals("0")){
                txtResultado.setText(inteiro);
            }else {
                txtResultado.setText(resultadoStr);
            }


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro na expressão");
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
            String resultado = txtResultado.getText().toString();
            if(resultado.isEmpty()){
                return;
            }
            txtExpressao.setText(resultado);
            txtResultado.setText("");

        });
    }

    private String getUltimoNumero (String expressao){
        if(expressao.equals("")){
            return "";
        }
        String ultimoNumero = "";
        String[] expressaoSplit = expressao.split("[-+*/]");

        ultimoNumero = expressaoSplit[expressaoSplit.length-1];
        System.out.println(ultimoNumero);
        return ultimoNumero;

    }

//    private String getUltimoNumero (String expressao){
//        String ultimoNumero = expressao;
//        //exemplo
////        ultimoNumero = getUltimoNumeroPorOperador(ultimoNumero,"+");
////        ultimoNumero = getUltimoNumeroPorOperador(ultimoNumero,"-");
////        ultimoNumero = getUltimoNumeroPorOperador(ultimoNumero,"*");
////        ultimoNumero = getUltimoNumeroPorOperador(ultimoNumero,"/");
////
//        String[] operadores = {"+","-","/","*"};
//        for (int i = 0; i < operadores.length; i ++){
//            ultimoNumero = getUltimoNumeroPorOperador(ultimoNumero,operadores[i]);
//        }
//        return ultimoNumero;
//
//    }


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


    public int getUltimoTipo(String expressao){
        String caractere = "";
        if(expressao.length() >0){
            caractere = String.valueOf(expressao.charAt(expressao.length()-1));
        }
        return getTipo(caractere);
    }


    public void addExpressao(String insertNum, int clickBtn) {

        int ultimoTipo = getUltimoTipo(txtExpressao.getText().toString());
        boolean permiteOperador = true;
        if(ultimoTipo == tipoOperador) {
            permiteOperador = false;
        } else if(ultimoTipo == 0 && insertNum.equals("/") ){
            permiteOperador = false;
        }else if(ultimoTipo == 0 && insertNum.equals("*") ){
            permiteOperador = false;
        }

        boolean permiteSeparador = true;
        String ultimoNumero = getUltimoNumero(txtExpressao.getText().toString());
        if(ultimoTipo != tipoOperador){
            permiteSeparador = !ultimoNumero.contains(".");
        }

        if (clickBtn == tipoNumero) {
            txtExpressao.append(insertNum);

        } else if (clickBtn == tipoOperador && permiteOperador) {
            if(ultimoTipo == tipoSeparador){
                txtExpressao.append("0");
            }
            txtExpressao.append(insertNum);

        } else if (clickBtn == tipoSeparador && permiteSeparador) {
            if(ultimoTipo != tipoNumero){
                txtExpressao.append("0");
            }
            txtExpressao.append(insertNum);
        }
    }

    //Coleta o click dos botões e adiciona os valor
    @Override
    public void onClick(View view) {
        AppCompatButton button = (AppCompatButton)view;
        String caractere = button.getText().toString();
        if(caractere.equals("X")){
            caractere = "*";
        }
        addExpressao(caractere,getTipo(caractere));
        calculaResultado();
    }


    public int getTipo(String caractere) {
        switch (caractere) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return tipoNumero;
            case "+":
            case "-":
            case "/":
            case "*":
                return tipoOperador;
            case ".":
                return tipoSeparador;
        }
        return 0;
    }
}