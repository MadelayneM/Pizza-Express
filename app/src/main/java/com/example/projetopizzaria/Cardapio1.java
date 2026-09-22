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

public class Cardapio1 extends AppCompatActivity {

    private Button btnVoltar, btnFazerPedido, btnLimpar;
    private EditText edtQtdCalabresa, edtQtdPortuguesa, edtQtdQueijos, edtQtdFrango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardapio1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialização correta das Views (Variavel = findViewById)
        btnVoltar = findViewById(R.id.btnVoltar);
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        btnLimpar = findViewById(R.id.btnLimpar);

        edtQtdCalabresa = findViewById(R.id.edtQtdCalabresa);
        edtQtdPortuguesa = findViewById(R.id.edtQtdPortuguesa);
        edtQtdQueijos = findViewById(R.id.edtQtdQueijos);
        edtQtdFrango = findViewById(R.id.edtQtdFrango);

        // Limpar campos
        btnLimpar.setOnClickListener(v -> {
            edtQtdCalabresa.setText("");
            edtQtdPortuguesa.setText("");
            edtQtdQueijos.setText("");
            edtQtdFrango.setText("");
        });

        // Fazer pedido
        btnFazerPedido.setOnClickListener(v -> {
            String qtdCalabresaStr = edtQtdCalabresa.getText().toString().trim();
            String qtdPortuguesaStr = edtQtdPortuguesa.getText().toString().trim();
            String qtdQueijosStr = edtQtdQueijos.getText().toString().trim();
            String qtdFrangoStr = edtQtdFrango.getText().toString().trim();

            // Garante valor 0 para campos vazios
            int qtdCalabresa = qtdCalabresaStr.isEmpty() ? 0 : Integer.parseInt(qtdCalabresaStr);
            int qtdPortuguesa = qtdPortuguesaStr.isEmpty() ? 0 : Integer.parseInt(qtdPortuguesaStr);
            int qtdQueijos = qtdQueijosStr.isEmpty() ? 0 : Integer.parseInt(qtdQueijosStr);
            int qtdFrango = qtdFrangoStr.isEmpty() ? 0 : Integer.parseInt(qtdFrangoStr);

            // Verifica se pelo menos uma pizza foi selecionada
            if (qtdCalabresa == 0 && qtdPortuguesa == 0 && qtdQueijos == 0 && qtdFrango == 0) {
                Toast.makeText(this, "Selecione pelo menos uma Pizza!!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Envia os dados inteiros para a Activity de Pedido
            Intent intent = new Intent(Cardapio1.this, Pedido.class);
            intent.putExtra("QUANTIDADE_PIZZA1", qtdCalabresa);
            intent.putExtra("QUANTIDADE_PIZZA2", qtdPortuguesa);
            intent.putExtra("QUANTIDADE_PIZZA3", qtdQueijos);
            intent.putExtra("QUANTIDADE_PIZZA4", qtdFrango);
            startActivity(intent);
        });

        btnVoltar.setOnClickListener(v -> {
            finish(); // Fecha a tela atual e volta para a anterior
        });
    }
}