package br.com.matheush.validationsformsaripaar_simple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Validator.ValidationListener{


    @NotEmpty(message = "Campo obrigatório")
    private EditText edtNome;

    @Email(message = "E-mail inválido")
    private EditText edtEmail;

    @NotEmpty(message = "Campo obrigatório")
    private EditText edtFone;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC, message = "Senha inválida")
    private EditText edtSenha;

    private Validator validator;

    private ArrayList<String> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        validator = new Validator(this);
        validator.setValidationListener(this);


        edtNome = (EditText) findViewById(R.id.edtNome);

        edtEmail = (EditText) findViewById(R.id.edtEmail);

        edtFone = (EditText) findViewById(R.id.edtFone);

        edtSenha = (EditText) findViewById(R.id.edtSenha);

        dados = new ArrayList<String>();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.menu_cadastro_ok:
                validator.validate();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onValidationSucceeded() {
        // a validação passou , siga em frente
        Log.d("LogX", "Deu certo");

        Intent intent = new Intent(MainActivity.this, DadosActivity.class);

        dados.add(edtNome.getText().toString());
        dados.add(edtEmail.getText().toString());
        dados.add(edtFone.getText().toString());
        dados.add(edtSenha.getText().toString());

        intent.putStringArrayListExtra("dados", dados);

        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        // algum campo não foi validado , temos a lista dos
        // erros para descobrirmos o que aconteceu , veremos
        // mais a frente

        for (ValidationError error : errors) {
            View view = error.getView();
            String messager = error.getCollatedErrorMessage(this);

            //Quando for EditText alerta deste modo
            if (view instanceof EditText) {
                ((EditText) view).setError(messager);
            }
            //Condição para ver se é outro tipo de view e dar o alerta adequado
            else {
                //Alerta que será utilizado
            }
        }
    }
}
