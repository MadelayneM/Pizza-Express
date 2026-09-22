package com.example.projetopizzaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class Pedido extends AppCompatActivity {

    private Button btnConfirmar, btnVoltar;
    private TextView txtPizza1, txtPizza2, txtPizza3, txtPizza4;
    private TextView txtPizzaQuantidade1, txtPizzaQuantidade2, txtPizzaQuantidade3, txtPizzaQuantidade4;
    private TextView txtPrecoPizza1, txtPrecoPizza2, txtPrecoPizza3, txtPrecoPizza4;
    private TextView txtQuantidadePizza, txtValorTotal;

    // Preços definidos de acordo com o layout do cardápio
    private final double PRECO_CALABRESA = 39.90;
    private final double PRECO_PORTUGUESA = 42.90;
    private final double PRECO_QUEIJOS = 44.90;
    private final double PRECO_FRANGO = 41.90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Associação das views aos IDs do layout
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVoltar = findViewById(R.id.btnVoltar);

        txtPizza1 = findViewById(R.id.txtPizza1);
        txtPizza2 = findViewById(R.id.txtPizza2);
        txtPizza3 = findViewById(R.id.txtPizza3);
        txtPizza4 = findViewById(R.id.txtPizza4);

        txtPizzaQuantidade1 = findViewById(R.id.txtPizzaQuantidade1);
        txtPizzaQuantidade2 = findViewById(R.id.txtPizzaQuantidade2);
        txtPizzaQuantidade3 = findViewById(R.id.txtPizzaQuantidade3);
        txtPizzaQuantidade4 = findViewById(R.id.txtPizzaQuantidade4);

        txtPrecoPizza1 = findViewById(R.id.txtPrecoPizza1);
        txtPrecoPizza2 = findViewById(R.id.txtPrecoPizza2);
        txtPrecoPizza3 = findViewById(R.id.txtPrecoPizza3);
        txtPrecoPizza4 = findViewById(R.id.txtPrecoPizza4);

        txtQuantidadePizza = findViewById(R.id.txtPizzaQuantidade);
        txtValorTotal = findViewById(R.id.txtValorTotal);

        // Recebendo os dados passados pelo Intent
        Intent intent = getIntent();
        int q1 = intent.getIntExtra("QUANTIDADE_PIZZA1", 0); // Calabresa
        int q2 = intent.getIntExtra("QUANTIDADE_PIZZA2", 0); // Portuguesa
        int q3 = intent.getIntExtra("QUANTIDADE_PIZZA3", 0); // Quatro Queijos
        int q4 = intent.getIntExtra("QUANTIDADE_PIZZA4", 0); // Frango c/ Catupiry

        int qtdTotal = 0;
        double valorTotal = 0.0;

        // Processamento Pizza 1 - Calabresa
        if (q1 > 0) {
            double subtotal = q1 * PRECO_CALABRESA;
            txtPizza1.setText("Calabresa");
            txtPizzaQuantidade1.setText(String.valueOf(q1));
            txtPrecoPizza1.setText(String.format(Locale.getDefault(), "R$ %.2f", subtotal));
            qtdTotal += q1;
            valorTotal += subtotal;
        } else {
            // Se for 0, oculta a linha
            txtPizza1.setVisibility(View.GONE);
            txtPizzaQuantidade1.setVisibility(View.GONE);
            txtPrecoPizza1.setVisibility(View.GONE);
        }

        // Processamento Pizza 2 - Portuguesa
        if (q2 > 0) {
            double subtotal = q2 * PRECO_PORTUGUESA;
            txtPizza2.setText("Portuguesa");
            txtPizzaQuantidade2.setText(String.valueOf(q2));
            txtPrecoPizza2.setText(String.format(Locale.getDefault(), "R$ %.2f", subtotal));
            qtdTotal += q2;
            valorTotal += subtotal;
        } else {
            txtPizza2.setVisibility(View.GONE);
            txtPizzaQuantidade2.setVisibility(View.GONE);
            txtPrecoPizza2.setVisibility(View.GONE);
        }

        // Processamento Pizza 3 - Quatro Queijos
        if (q3 > 0) {
            double subtotal = q3 * PRECO_QUEIJOS;
            txtPizza3.setText("Quatro Queijos");
            txtPizzaQuantidade3.setText(String.valueOf(q3));
            txtPrecoPizza3.setText(String.format(Locale.getDefault(), "R$ %.2f", subtotal));
            qtdTotal += q3;
            valorTotal += subtotal;
        } else {
            txtPizza3.setVisibility(View.GONE);
            txtPizzaQuantidade3.setVisibility(View.GONE);
            txtPrecoPizza3.setVisibility(View.GONE);
        }

        // Processamento Pizza 4 - Frango c/ Catupiry
        if (q4 > 0) {
            double subtotal = q4 * PRECO_FRANGO;
            txtPizza4.setText("Frango");
            txtPizzaQuantidade4.setText(String.valueOf(q4));
            txtPrecoPizza4.setText(String.format(Locale.getDefault(), "R$ %.2f", subtotal));
            qtdTotal += q4;
            valorTotal += subtotal;
        } else {
            txtPizza4.setVisibility(View.GONE);
            txtPizzaQuantidade4.setVisibility(View.GONE);
            txtPrecoPizza4.setVisibility(View.GONE);
        }

        // Atualização dos totais gerais
        txtQuantidadePizza.setText(String.valueOf(qtdTotal));
        txtValorTotal.setText(String.format(Locale.getDefault(), "R$ %.2f", valorTotal));

        // Botão de confirmação
        btnConfirmar.setOnClickListener(v -> {
            Toast.makeText(this, "Pedido Confirmado!!", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(Pedido.this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finish();
        });

        // Botão voltar
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(v -> finish());
        }
    }
}