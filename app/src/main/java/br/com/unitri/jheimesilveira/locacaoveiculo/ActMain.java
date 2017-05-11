package br.com.unitri.jheimesilveira.locacaoveiculo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtCategoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtCliente;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtDataItem;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtFuncionario;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtFuncionarioAdd;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtLocacao;
import br.com.unitri.jheimesilveira.locacaoveiculo.views.FgtVeiculo;

public class ActMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_main, menu);
        return true;
    }

    public void onClientes(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtCliente());
    }
    public void onCategorias(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtCategoria());
    }
    public void onVeiculos(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtVeiculo());
    }
    public void onDataItem(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtDataItem());
    }
    public void onFuncionarios(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtFuncionario());
    }
    public void onLocacoes(View view) {
        MobileApp.setFragmentReplacePage(
                getSupportFragmentManager(),
                new FgtLocacao());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
