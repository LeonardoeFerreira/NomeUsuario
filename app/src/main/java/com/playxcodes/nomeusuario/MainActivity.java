package com.playxcodes.nomeusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editText;
    private TextView textViewResultado;

    //constante
    private static final String ARQUIVOS_PREFERENCIA = "ArquivoPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // Criando relação com os componentes
        buttonSalvar = findViewById(R.id.buttonSalvar);
        editText = findViewById(R.id.editNome);
        textViewResultado = findViewById(R.id.textViewResultado);

        //evento de onclick para o button
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //classe para salvar na preferencias do usuario
                //salvando dados no formato xml no dispositivo

                SharedPreferences preferences = getSharedPreferences(ARQUIVOS_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //processo de validação
                if(editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();

                }else{
                    String nome = editText.getText().toString();
                    editor.putString("nome",nome ); //chaves que serão salvas
                    editor.commit(); //realiza o salvamento

                    textViewResultado.setText("Olá "+ nome);

                }


            }
        });

        //Recuperando os dados que fio salvo
        SharedPreferences preferences = getSharedPreferences(ARQUIVOS_PREFERENCIA, 0);

        if (preferences.contains("nome")){
            String nome = preferences.getString("nome", "usuario não definido");
            textViewResultado.setText(" Olá "+nome);
        } else {
            textViewResultado.setText("Olá, usuario não definido");
        }



    }
}