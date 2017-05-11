package br.com.unitri.jheimesilveira.locacaoveiculo.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;
import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.ClienteBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Cliente;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtClienteAdd extends Fragment {

    ClienteBO bo;
    View view;
    private EditText etCpf;
    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;
    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private Cliente cliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_cliente_add, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initComponentes();
    }

    private void initComponentes() {
        initViews();
        initDados();
        onBtnSalvar();
        onBtnRemover();
    }

    private void onBtnRemover() {
        if (cliente == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover " + cliente.getNome());
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(cliente);
                        MobileApp.setFragmentReplacePage(
                                getFragmentManager(),
                                new FgtCliente());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onBtnSalvar() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar(ferificarCampos());
            }
        });
    }

    private boolean ferificarCampos() {
        boolean flag = true;
        if (etCpf.getText().length() <= 0) {
            flag = false;
            etCpf.setError("* Campo Obrigatório!");
        }
        if (etNome.getText().length() <= 0) {
            flag = false;
            etNome.setError("* Campo Obrigatório!");
        }
        if (etEndereco.getText().length() <= 0) {
            flag = false;
            etEndereco.setError("* Campo Obrigatório!");
        }
        if (etTelefone.getText().length() <= 0) {
            flag = false;
            etTelefone.setError("* Campo Obrigatório!");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            Cliente cliente = new Cliente();
            cliente.setTelefone(etTelefone.getText().toString());
            cliente.setNome(etNome.getText().toString());
            cliente.setEndereco(etEndereco.getText().toString());
            cliente.setCpf(etCpf.getText().toString());

            if(this.cliente != null) {
                cliente.setId(this.cliente.getId());
                bo.update(cliente);
            } else {
                bo.create(cliente);
            }

            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtCliente()
            );
        }
    }

    private void initDados() {
        try {
            bo = new ClienteBO(getContext());
            if (getArguments() != null) {
                cliente = bo.queryForId(getArguments().getInt("id"));
                carregarCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarCliente() {
        if (cliente != null) {
            etCpf.setText(cliente.getCpf());
            etTelefone.setText(cliente.getTelefone());
            etNome.setText(cliente.getNome());
            etEndereco.setText(cliente.getEndereco());
        }
    }

    private void initViews() {
        etCpf = (EditText) view.findViewById(R.id.et_cpf);
        etNome = (EditText) view.findViewById(R.id.et_nome);
        etEndereco = (EditText) view.findViewById(R.id.et_endereco);
        etTelefone = (EditText) view.findViewById(R.id.et_telefone);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
    }

}
