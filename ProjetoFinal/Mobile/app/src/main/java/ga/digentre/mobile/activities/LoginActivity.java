package ga.digentre.mobile.activities;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        final RequestQueue queue = Volley.newRequestQueue(this);

        editTextSenha = (EditText) this.findViewById(R.id.editTextSenha);
        editTextCPF = (EditText) this.findViewById(R.id.editTextCpf);
        button = (Button) this.findViewById(R.id.buttonEntrar);

        this.setTitle("DigEntrega - Login");
        editTextCPF.addTextChangedListener(Mascaras.insert(Mascaras.CPF_MASK, editTextCPF));

        // temporario para evitar o login

        //Intent intent = new Intent(LoginActivity.this, PedidosActivity.class);
        //intent.putExtra("cpf", "11111111111"); //Optional parameters
        //LoginActivity.this.startActivity(intent);

        // Faz a autenticação do usuário.
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String cpfFormatado = editTextCPF.getText().toString();
                final String cpf = cpfFormatado.replace(".", "").replace("-", "");
                final String senha = editTextSenha.getText().toString();


                final AlertDialog.Builder ad  = new AlertDialog.Builder(context);
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
                final String url = "http://digentre.ga/webservice/login/entregador/";

                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject json = new JSONObject(response);
                                    if (json.get("id") != null) {
                                        Intent intent = new Intent(LoginActivity.this, PedidosActivity.class);
                                        intent.putExtra("cpf", cpf); //Optional parameters
                                        intent.putExtra("entregadorId", String.valueOf(json.get("id"))); //Optional parameters
                                        LoginActivity.this.startActivity(intent);
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                ad.setMessage("CPF e/ou senha incorreta.");
                                ad.create().show();
                                return;


                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley", "Error: " + error.getMessage());
                        error.printStackTrace();
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/x-www-form-urlencoded; charset=UTF-8";
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("cpf", cpf);
                        params.put("senha",senha);
                        return params;
                    }

                };

                queue.add(jsonObjRequest);

            }
        });
    }

}
