package com.example.carteraclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carteraclientes.DB.DbContactos;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtDireccion, txtCelular, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCelular = findViewById(R.id.txtCelular);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nuevo, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevoGuardar:
                guardarRegistro();
                return true;
            case R.id.menuNuevoSalir:
                salirRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void guardarRegistro(){
        if(!txtNombre.getText().toString().equals("") && !txtCelular.getText().toString().equals("")) {

            DbContactos dbContactos = new DbContactos(NuevoActivity.this);
            long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtDireccion.getText().toString(), txtCelular.getText().toString(),txtEmail.getText().toString());

            if (id > 0) {
                Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                limpiar();
            } else {
                Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(NuevoActivity.this, "ERROR CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
        }
    }
    public void salirRegistro(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    private void limpiar() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
    }
}