package com.example.thomasngo_mobiledev_project1;

//gitlpackage com.example.thomasngo_mobiledev_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountsActivity extends AppCompatActivity {

    private TextView text_displayed ;
    private Button btn_update;
    private JsonPlaceholderApi api;
    String content = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        text_displayed = findViewById(R.id.text_view_result);
        btn_update = findViewById(R.id.btn_update);


        // Get context
        Context context = getApplicationContext();
        // Get key
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create / Access file
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "EncryptedSharedPreferences",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        content = sharedPreferences.getString("Database", null);

        if (content == null){

            // Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getUrl())
                    .client(getUnsafeOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(JsonPlaceholderApi.class);


            Call<List<Post>> call = api.getPosts();

            call.enqueue(new Callback<List<Post>>() {

                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (!response.isSuccessful()){
                        content = "Code: "+response.code();
                        return;
                    }

                    List<Post> posts = response.body();

                    for (Post post : posts){

                        content += "id: " + post.getId()+ "\n";
                        content += "account_name: " + post.getAccount_name() + "\n";
                        content += "amount: " + post.getAmount() + "\n";
                        content += "iban: " + post.getIban() + "\n";
                        content += "currency: " + post.getCurrency() + "\n\n";
                        //text_displayed.setText(content);
                    }

                    // Begin store data
                    // Get context
                    Context context = getApplicationContext();
                    // Get key
                    String masterKeyAlias = null;
                    try {
                        masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Create / Access file
                    SharedPreferences sharedPreferences = null;
                    try {
                        sharedPreferences = EncryptedSharedPreferences.create(
                                "EncryptedSharedPreferences",
                                masterKeyAlias,
                                context,
                                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                        );
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Database", content);
                    editor.apply();
                    content = sharedPreferences.getString("Database", null);
                    // End store data
                    text_displayed.setText(content);
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    content = t.getMessage();
                }
            });
        }

        text_displayed.setText(content);

        btn_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text_displayed.setText("");

                // Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getUrl())
                        .client(getUnsafeOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                api = retrofit.create(JsonPlaceholderApi.class);


                Call<List<Post>> call = api.getPosts();

                call.enqueue(new Callback<List<Post>>() {

                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()){
                            content = "Code: "+response.code();
                            return;
                        }

                        List<Post> posts = response.body();

                        for (Post post : posts){

                            content += "id: " + post.getId()+ "\n";
                            content += "account_name: " + post.getAccount_name() + "\n";
                            content += "amount: " + post.getAmount() + "\n";
                            content += "iban: " + post.getIban() + "\n";
                            content += "currency: " + post.getCurrency() + "\n\n";
                            //text_displayed.setText(content);
                        }

                        // Begin store data
                        // Get context
                        Context context = getApplicationContext();
                        // Get key
                        String masterKeyAlias = null;
                        try {
                            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Create / Access file
                        SharedPreferences sharedPreferences = null;
                        try {
                            sharedPreferences = EncryptedSharedPreferences.create(
                                    "EncryptedSharedPreferences",
                                    masterKeyAlias,
                                    context,
                                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                            );
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Database", content);
                        editor.apply();
                        content = sharedPreferences.getString("Database", null);
                        // End store data
                        text_displayed.setText(content);
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        content = t.getMessage();
                    }
                });
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        String url = "1D500A0A00C26DD10D1B79D769D2A0DC7C1EC6B9E9BFC237D6864805957E1B025FFEECBDF8C24B57DF98A47A80270B06233B7CDC693CADD2EBD9C3EFCB0AB38A";
        String decrypted = "";
        try {
            decrypted = AESUtils.decrypt(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}