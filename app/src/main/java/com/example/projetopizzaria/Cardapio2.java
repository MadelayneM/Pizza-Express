package com.example.projetopizzaria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cardapio2 extends AppCompatActivity {

    private Button btnVoltar, btnFazerPedido, btnLimpar;
    private EditText edtQtdChocolate, edtQtdMorango, edtQtdPacoca, edtQtdBanana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardapio2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Associação das Views (Variavel = findViewById)
        btnVoltar = findViewById(R.id.btnVoltar);
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        btnLimpar = findViewById(R.id.btnLimpar);

        edtQtdChocolate = findViewById(R.id.edtQtdChocolate);
        edtQtdMorango = findViewById(R.id.edtQtdMorango);
        edtQtdPacoca = findViewById(R.id.edtQtdPacoca);
        edtQtdBanana = findViewById(R.id.edtQtdBanana);

        // Limpar os campos de quantidade
        btnLimpar.setOnClickListener(v -> {
            edtQtdChocolate.setText("");
            edtQtdMorango.setText("");
            edtQtdPacoca.setText("");
            edtQtdBanana.setText("");
        });

        // Fazer Pedido
        btnFazerPedido.setOnClickListener(v -> {
            String qtdChocolateStr = edtQtdChocolate.getText().toString().trim();
            String qtdMorangoStr = edtQtdMorango.getText().toString().trim();
            String qtdPacocaStr = edtQtdPacoca.getText().toString().trim();
            String qtdBananaStr = edtQtdBanana.getText().toString().trim();

            int qtdChocolate = qtdChocolateStr.isEmpty() ? 0 : Integer.parseInt(qtdChocolateStr);
            int qtdMorango = qtdMorangoStr.isEmpty() ? 0 : Integer.parseInt(qtdMorangoStr);
            int qtdPacoca = qtdPacocaStr.isEmpty() ? 0 : Integer.parseInt(qtdPacocaStr);
            int qtdBanana = qtdBananaStr.isEmpty() ? 0 : Integer.parseInt(qtdBananaStr);

            // Valida se foi inserida ao menos uma quantidade
            if (qtdChocolate == 0 && qtdMorango == 0 && qtdPacoca == 0 && qtdBanana == 0) {
                Toast.makeText(this, "Selecione pelo menos uma Pizza!!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Envia para a Activity de resumo do pedido doce
            Intent intent = new Intent(Cardapio2.this, PedidoDoce.class);
            intent.putExtra("QUANTIDADE_PIZZA1", qtdChocolate);
            intent.putExtra("QUANTIDADE_PIZZA2", qtdMorango);
            intent.putExtra("QUANTIDADE_PIZZA3", qtdPacoca);
            intent.putExtra("QUANTIDADE_PIZZA4", qtdBanana);
            startActivity(intent);
        });

        // Botão voltar para a tela anterior
        btnVoltar.setOnClickListener(v -> finish());
    }
}