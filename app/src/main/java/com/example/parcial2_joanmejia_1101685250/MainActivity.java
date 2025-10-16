package com.example.parcial2_joanmejia_1101685250;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz
    private EditText etNumero1, etNumero2;
    private Button btnMultiplicar, btnDividir;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de los componentes de la interfaz
        inicializarComponentes();

        // Configuración de los listeners para los botones
        configurarEventos();
    }

    /**
     * Método para inicializar los componentes de la interfaz de usuario
     */
    private void inicializarComponentes() {
        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        tvResultado = findViewById(R.id.tvResultado);
    }

    /**
     * Método para configurar los eventos de los botones
     */
    private void configurarEventos() {
        // Evento para el botón multiplicar
        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMultiplicacion();
            }
        });

        // Evento para el botón dividir
        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarDivision();
            }
        });
    }

    /**
     * Método para realizar la operación de multiplicación
     */
    private void realizarMultiplicacion() {
        try {
            double numero1 = obtenerNumero(etNumero1);
            double numero2 = obtenerNumero(etNumero2);

            double resultado = numero1 * numero2;
            mostrarResultado("Multiplicación: " + numero1 + " × " + numero2 + " = " + resultado);
        } catch (NumberFormatException e) {
            mostrarError("Por favor ingrese números válidos");
        }
    }

    /**
     * Método para realizar la operación de división
     */
    private void realizarDivision() {
        try {
            double numero1 = obtenerNumero(etNumero1);
            double numero2 = obtenerNumero(etNumero2);

            if (numero2 == 0) {
                mostrarError("No se puede dividir por cero");
                return;
            }

            double resultado = numero1 / numero2;
            mostrarResultado("División: " + numero1 + " ÷ " + numero2 + " = " + resultado);
        } catch (NumberFormatException e) {
            mostrarError("Por favor ingrese números válidos");
        }
    }

    /**
     * Método para obtener un número desde un EditText
     * @param editText El EditText del cual obtener el número
     * @return El número como double
     * @throws NumberFormatException Si el texto no es un número válido
     */
    private double obtenerNumero(EditText editText) throws NumberFormatException {
        String texto = editText.getText().toString().trim();
        if (texto.isEmpty()) {
            throw new NumberFormatException("Campo vacío");
        }
        return Double.parseDouble(texto);
    }

    /**
     * Método para mostrar el resultado en el TextView
     * @param resultado El texto del resultado a mostrar
     */
    private void mostrarResultado(String resultado) {
        tvResultado.setText(resultado);
    }

    /**
     * Método para mostrar mensajes de error
     * @param mensaje El mensaje de error a mostrar
     */
    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        tvResultado.setText("Error en la operación");
    }
}
