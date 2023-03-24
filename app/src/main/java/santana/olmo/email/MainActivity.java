package santana.olmo.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pegando o botao pelo id
        Button btnEnviar = findViewById(R.id.btnEnviar);
        //Definicao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pegando o campo de texto com o email pelo id
                EditText etEmail = findViewById(R.id.etEmail);
                //pegando o email informado dentro do campo de texto
                String email = etEmail.getText().toString();
                //pegando o campo de texto com o assunto da conversa
                EditText etAssunto = findViewById(R.id.etAssunto);
                //pegando o assunto informado dentro do campo de texto
                String assunto = etAssunto.getText().toString();
                //pegando o campo de texto com a mensagem pelo id
                EditText etTexto = findViewById(R.id.etTexto);
                //pegando a mensagem informada dentro do campo de texto
                String texto = etTexto.getText().toString();

                //criando uma intencao implicita
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //estabelecendo que queremos mandar um email
                i.setData(Uri.parse("mailto:"));

                //criando um array de emails
                String[] emails = new String[]{email};
                //informando o email destinatario
                i.putExtra(Intent.EXTRA_EMAIL,emails);
                //informando o assunto do email
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                //informando a mensagem do email
                i.putExtra(Intent.EXTRA_TEXT,texto);

                //o usuario deve escolher o app com o qual deseja enviar o email
                try{
                    startActivity(Intent.createChooser(i,"Escolha o APP"));
                }
                //caso nao tenha nenhum app que possa enviar o email vai aparecer um erro na tela
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this,"Não há nenhum app que posso realizar essa operação",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}