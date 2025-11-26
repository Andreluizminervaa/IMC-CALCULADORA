package com.andreminerva.imccalculadora
// NÃO MUDE a linha de "package" que já existe no seu arquivo.
// Deixe ela em cima e cole o código abaixo dela.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usa o layout da tela principal
        setContentView(R.layout.activity_main)

        // Ligando os componentes da tela com o código
        val etPeso = findViewById<EditText>(R.id.etPeso)               // campo peso
        val etAltura = findViewById<EditText>(R.id.etAltura)           // campo altura
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)       // botão calcular
        val tvResultado = findViewById<TextView>(R.id.tvResultado)     // texto com valor do IMC
        val tvClassificacao = findViewById<TextView>(R.id.tvClassificacao) // texto com a classificação

        btnCalcular.setOnClickListener {
            // Pega o texto digitado e substitui vírgula por ponto
            val pesoStr = etPeso.text.toString().replace(",", ".")
            val alturaStr = etAltura.text.toString().replace(",", ".")

            // Verifica se os campos estão preenchidos
            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                Toast.makeText(this, "Preencha peso e altura!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Converte para número
            val peso = pesoStr.toDoubleOrNull()
            val altura = alturaStr.toDoubleOrNull()

            if (peso == null || altura == null) {
                Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (peso <= 0 || altura <= 0) {
                Toast.makeText(this, "Peso e altura devem ser maiores que zero.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Fórmula do IMC
            val imc = peso / (altura * altura)

            // Mostra o IMC com 2 casas decimais
            tvResultado.text = "Seu IMC é: %.2f".format(imc)

            // Mostra a classificação
            tvClassificacao.text = classificarImc(imc)
        }
    }

    // Função que devolve a classificação de acordo com o IMC
    private fun classificarImc(imc: Double): String {
        return when {
            imc < 18.5 -> "Classificação: Abaixo do peso"
            imc < 25.0 -> "Classificação: Peso normal"
            imc < 30.0 -> "Classificação: Sobrepeso"
            imc < 35.0 -> "Classificação: Obesidade grau I"
            imc < 40.0 -> "Classificação: Obesidade grau II"
            else -> "Classificação: Obesidade grau III (mórbida)"
        }
    }
}
