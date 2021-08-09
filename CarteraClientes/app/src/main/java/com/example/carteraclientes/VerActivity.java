package com.example.carteraclientes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carteraclientes.DB.DbContactos;
import com.example.carteraclientes.Entidades.Contactos;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtDireccion, txtCelular, txtEmail;
    Button btnGuarda;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCelular = findViewById(R.id.txtCelular);
        txtEmail = findViewById(R.id.txtEmail);

        btnGuarda = findViewById(R.id.btnGuarda);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtDireccion.setText(contacto.getDireccion());
            txtCelular.setText(contacto.getCelular());
            txtEmail.setText(contacto.getEmail());

            btnGuarda.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtCelular.setInputType(InputType.TYPE_NULL);
            txtEmail.setInputType(InputType.TYPE_NULL);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuEditarEditar:
                EditarRegistro();
                return true;
            case R.id.menuEditarEliminar:
                EliminarRegistro();
                return true;
            case R.id.menuEditarSalir:
                SalirRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void EditarRegistro(){
        Intent intent = new Intent(VerActivity.this, EditarActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
        finish();
    }

    private void EliminarRegistro(){
        final DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);
        AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
        builder.setMessage("¿Desea eliminar este contacto?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(dbContactos.eliminarContacto(id)){
                            lista();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    private void SalirRegistro() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}