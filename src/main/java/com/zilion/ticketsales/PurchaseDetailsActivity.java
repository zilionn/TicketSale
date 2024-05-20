package com.zilion.ticketsales;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PurchaseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        TextView textViewDetalhes = findViewById(R.id.textViewDetalhes);
        Button buttonVoltar = findViewById(R.id.buttonVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String codigo = bundle.getString("codigo");
            float valor = bundle.getFloat("valor");
            float taxa = bundle.getFloat("taxa");
            float valorFinal = bundle.getFloat("valorFinal");
            boolean isVIP = bundle.getBoolean("isVIP");
            String funcao = isVIP ? bundle.getString("funcao") : "N/A";

            String detalhes = "Código: " + codigo +
                    "\nValor: " + valor +
                    "\nTaxa de Conveniência: " + taxa +
                    "\nValor Final: " + valorFinal +
                    "\nVIP: " + (isVIP ? "Sim" : "Não") +
                    "\nFunção: " + funcao;

            textViewDetalhes.setText(detalhes);
        }

        buttonVoltar.setOnClickListener(v -> finish());
    }
}