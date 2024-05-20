package com.zilion.ticketsales;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import model.Ingresso;
import model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCodigo, editTextValor, editTextTaxa, editTextFuncao;
    private CheckBox checkBoxVIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextValor = findViewById(R.id.editTextValor);
        editTextTaxa = findViewById(R.id.editTextTaxa);
        checkBoxVIP = findViewById(R.id.checkBoxVIP);
        editTextFuncao = findViewById(R.id.editTextFuncao);
        Button buttonComprar = findViewById(R.id.buttonComprar);

        checkBoxVIP.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editTextFuncao.setVisibility(View.VISIBLE);
            } else {
                editTextFuncao.setVisibility(View.GONE);
            }
        });

        buttonComprar.setOnClickListener(v -> {
            String codigo = editTextCodigo.getText().toString();
            float valor = Float.parseFloat(editTextValor.getText().toString());
            float taxa = Float.parseFloat(editTextTaxa.getText().toString());
            boolean isVIP = checkBoxVIP.isChecked();

            float valorFinal;
            String funcao = null;
            if (isVIP) {
                funcao = editTextFuncao.getText().toString();
                IngressoVIP ingressoVIP = new IngressoVIP(codigo, valor, funcao);
                valorFinal = ingressoVIP.valorFinal(taxa);
            } else {
                Ingresso ingresso = new Ingresso(codigo, valor);
                valorFinal = ingresso.valorFinal(taxa);
            }

            Intent intent = new Intent(MainActivity.this, PurchaseDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("codigo", codigo);
            bundle.putFloat("valor", valor);
            bundle.putFloat("taxa", taxa);
            bundle.putFloat("valorFinal", valorFinal);
            bundle.putBoolean("isVIP", isVIP);
            if (isVIP) {
                bundle.putString("funcao", funcao);
            }
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}