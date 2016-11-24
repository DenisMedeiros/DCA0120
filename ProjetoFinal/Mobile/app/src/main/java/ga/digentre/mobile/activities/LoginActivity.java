package ga.digentre.mobile.activities;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ga.digentre.mobile.R;
import ga.digentre.mobile.utils.Mascaras;
import ga.digentre.mobile.utils.ValidadorCPF;


public class LoginActivity extends AppCompatActivity {

    public static final String DigEntregaURL = "http://localhost:8000/DigEntrega/";

    final Context context = this;
    EditText editTextCPF;
    EditText editTextSenha;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSenha = (EditText) this.findViewById(R.id.editTextSenha);
        editTextCPF = (EditText) this.findViewById(R.id.editTextCpf);
        button = (Button) this.findViewById(R.id.buttonEntrar);

        this.setTitle("DigEntrega - Login");
        editTextCPF.addTextChangedListener(Mascaras.insert(Mascaras.CPF_MASK, editTextCPF));

        // temporario para evitar o login

//        Intent intent = new Intent(LoginActivity.this, PedidosActivity.class);
//        intent.putExtra("cpf", "11111111111"); //Optional parameters
//        LoginActivity.this.startActivity(intent);

        // Faz a autenticação do usuário.
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String cpfFormatado = editTextCPF.getText().toString();
                String cpf = cpfFormatado.replace(".", "").replace("-", "");
                String senha = editTextSenha.getText().toString();

                AlertDialog.Builder ad  = new AlertDialog.Builder(context);
                ad.setTitle("Erro");
                ad.setPositiveButton("OK", null);
                ad.setCancelable(true);
                ad.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                // Verifica se algum campo está em branco.
                if(cpf.trim().isEmpty() || senha.trim().isEmpty()) {
                    ad.setMessage("Algum dos campos está em branco!");
                    ad.create().show();
                    return;
                }

                // Verifica se o CPF é válido.
                if(!ValidadorCPF.isValidCPF(cpf)) {
                    // Log.d("erro", "CPF inválido!");
                    //int duration = Toast.LENGTH_SHORT;
                    // Toast toast = Toast.makeText(context, "CPF inválido!", duration);
                    //toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_VERTICAL, 0, 0);
                    //toast.show();

                    ad.setMessage("Este CPF não é válido!");
                    ad.create().show();
                    return;

                }


                // Tenta autenticar o usuário.
                // TODO
                // Na situação final, é para buscar no BD o usuário e senha.
                if(!(cpf.equals("11111111111") && senha.equals("123"))) {
                    ad.setMessage("CPF e/ou senha incorreta.");
                    ad.create().show();
                    return;

                }

                Intent intent = new Intent(LoginActivity.this, PedidosActivity.class);
                intent.putExtra("cpf", cpf); //Optional parameters
                LoginActivity.this.startActivity(intent);


            }
        });



    }




}
