package br.com.unitri.jheimesilveira.locacaoveiculo.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.ClienteBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.FuncionarioBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.LocacaoBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.VeiculoBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Cliente;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Funcionario;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Locacao;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogSearchList;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtLocacaoAdd extends Fragment {

    private LocacaoBO bo;
    private VeiculoBO boVeiculo;
    private FuncionarioBO boFuncionario;
    private ClienteBO boCliente;

    private Locacao locacao;
    private Funcionario funcionarioCad;
    private Funcionario funcionarioRec;
    private Cliente cliente;
    private Veiculo veiculo;
    private Date dtaInicio;
    private Date dtaFim;

    View view;
    private EditText etFuncionarioCad;
    private EditText etFuncionarioRec;
    private EditText etCliente;
    private EditText etVeiculo;
    private TextView tvDtaInicio;
    private TextView tvDtaFim;

    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private FloatingActionButton btnDtaInicio;
    private FloatingActionButton btnDtaFim;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_locacao_add, container, false);
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
        onTvCliente();
        onTvVaiculo();
        onTvfuncionarioRec();
        onTvfuncionarioCad();
        initBtnDtaInicio();
        initBtnDtaFim();
    }

    private void onTvVaiculo() {
        etVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogSearchList dialog = new DialogSearchList(
                        getContext(),
                        boVeiculo.queryForAll(),
                        null,
                        false);
                dialog.show("Veiculo");
                dialog.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        veiculo = (Veiculo) dialog.getLvDialog().getAdapter().getItem(position);
                        etVeiculo.setText(veiculo.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void initBtnDtaInicio() {
        Calendar c = Calendar.getInstance();
        c.setTime(dtaInicio);
        final int year = c.get(Calendar.YEAR);
        final int monthOfYear = c.get(Calendar.MONTH);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        btnDtaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                c.set(year, monthOfYear, dayOfMonth);
                                tvDtaInicio.setText(Utils.formatDate("dd/MM/yyyy", c.getTime()));
                                dtaInicio = c.getTime();
                            }
                        },
                        year,
                        monthOfYear,
                        dayOfMonth);
                datePickerDialog.show();
            }
        });
    }

    private void initBtnDtaFim() {
        Calendar c = Calendar.getInstance();
        c.setTime(dtaFim);
        final int year = c.get(Calendar.YEAR);
        final int monthOfYear = c.get(Calendar.MONTH);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        btnDtaFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                c.set(year, monthOfYear, dayOfMonth);
                                tvDtaFim.setText(Utils.formatDate("dd/MM/yyyy", c.getTime()));
                                dtaFim = c.getTime();
                            }
                        },
                        year,
                        monthOfYear,
                        dayOfMonth);
                datePickerDialog.show();
            }
        });
    }

    private void onTvfuncionarioCad() {
        etFuncionarioCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogSearchList dialog = new DialogSearchList(
                        getContext(),
                        boFuncionario.queryForAll(),
                        null,
                        false);
                dialog.show("Funcionários");
                dialog.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        funcionarioCad = (Funcionario) dialog.getLvDialog().getAdapter().getItem(position);
                        etFuncionarioCad.setText(funcionarioCad.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onTvfuncionarioRec() {
        etFuncionarioRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogSearchList dialog = new DialogSearchList(
                        getContext(),
                        boFuncionario.queryForAll(),
                        null,
                        false);
                dialog.show("Funcionários");
                dialog.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        funcionarioRec = (Funcionario) dialog.getLvDialog().getAdapter().getItem(position);
                        etFuncionarioRec.setText(funcionarioRec.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onTvCliente() {
        etCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogSearchList dialog = new DialogSearchList(
                        getContext(),
                        boCliente.queryForAll(),
                        null,
                        false);
                dialog.show("Clientes");
                dialog.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cliente = (Cliente) dialog.getLvDialog().getAdapter().getItem(position);
                        etCliente.setText(cliente.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onBtnRemover() {
        if (veiculo == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover " + veiculo.getModelo());
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(locacao);
                        MobileApp.setFragmentReplacePage(
                                getFragmentManager(),
                                new FgtVeiculo());
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
                salvar(verificarCampos());
            }
        });
    }

    private boolean verificarCampos() {
        boolean flag = true;
        if (cliente == null) {
            flag = false;
            etCliente.setError("* Campo Obrigatório!");
        }
        if (funcionarioCad == null) {
            flag = false;
            etFuncionarioCad.setError("* Campo Obrigatório!");
        }
        if (funcionarioRec == null) {
            flag = false;
            etFuncionarioRec.setError("* Campo Obrigatório!");
        }
        if (veiculo == null) {
            flag = false;
            etVeiculo.setError("* Campo Obrigatório!");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            Locacao item = new Locacao();
            item.setCliente(cliente);

            item.setFuncionarioCad(funcionarioCad);
            item.setFuncionarioRec(funcionarioRec);
            item.setVeiculo(veiculo);
            item.setCliente(cliente);
            item.setDtaInicio(dtaInicio);
            item.setDtaFim(dtaFim);

            if(this.locacao != null) {
                item.setId(this.locacao.getId());
                bo.update(item);
            } else {
                bo.create(item);
            }

            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtLocacao()
            );
        }
    }

    private void initDados() {
        try {
            bo = new LocacaoBO(getContext());
            boVeiculo = new VeiculoBO(getContext());
            boFuncionario = new FuncionarioBO(getContext());
            boCliente = new ClienteBO(getContext());
            if (getArguments() != null) {
                locacao = bo.queryForId(getArguments().getInt("id"));
                dtaInicio = locacao.getDtaInicio();
                dtaFim = locacao.getDtaFim();
                veiculo = boVeiculo.queryForId(locacao.getVeiculo().getId());
                funcionarioCad = boFuncionario.queryForId(locacao.getFuncionarioCad().getId());
                funcionarioRec = boFuncionario.queryForId(locacao.getFuncionarioRec().getId());
                cliente = boCliente.queryForId(locacao.getCliente().getId());
            }
            carregarItemSelecionado();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarItemSelecionado() {
        if (locacao != null) {
            etCliente.setText(cliente.toString());
            etFuncionarioRec.setText(funcionarioRec.toString());
            etFuncionarioCad.setText(funcionarioCad.toString());
            etVeiculo.setText(veiculo.toString());
        }
        if (dtaInicio == null) {
            dtaInicio = new Date();
        }
        if (dtaFim == null) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 7);
            dtaFim = c.getTime();
        }
        tvDtaInicio.setText(Utils.formatDate("dd/MM/yyyy", dtaInicio));
        tvDtaFim.setText(Utils.formatDate("dd/MM/yyyy", dtaFim));
    }

    private void initViews() {
        etCliente = (EditText) view.findViewById(R.id.et_cliente);
        etFuncionarioCad = (EditText) view.findViewById(R.id.et_funcionario_cad);
        etFuncionarioRec = (EditText) view.findViewById(R.id.et_funcionario_rec);
        etVeiculo = (EditText) view.findViewById(R.id.et_veiculo);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
        tvDtaInicio = (TextView) view.findViewById(R.id.tv_dta_inicio);
        tvDtaFim = (TextView) view.findViewById(R.id.tv_dta_fim);
        btnDtaInicio = (FloatingActionButton) view.findViewById(R.id.btn_dta_inicio);
        btnDtaFim = (FloatingActionButton) view.findViewById(R.id.btn_dta_fim);
    }

}
