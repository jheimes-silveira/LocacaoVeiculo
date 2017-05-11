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
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.unitri.jheimesilveira.locacaoveiculo.Adapter.AdapterDefault;
import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.bo.DataItemBO;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.DataItem;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogAlertCustom;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.MobileApp;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class FgtDataItemAdd extends Fragment {

    private DataItemBO bo;

    private DataItem dataItem;
    private Date dtaInicio;
    private Date dtaFim;
    private ListView listView;
    View view;
    private TextView tvDtaInicio;
    private TextView tvDtaFim;
    private EditText etIntervalo;

    private FloatingActionButton btnSalvar;
    private FloatingActionButton btnRemover;
    private FloatingActionButton btnDtaInicio;
    private FloatingActionButton btnDtaFim;
    private List<DataItem> dataItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fgt_data_item_add, container, false);
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
        initBtnDtaInicio();
        initBtnDtaFim();
        initListView();
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

    private void onBtnRemover() {
        if (dataItem == null) {
            btnRemover.setVisibility(View.GONE);
            return;
        }
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogAlertCustom dialog = new DialogAlertCustom(getContext());
                dialog.show("Alerta", "Tem certeza que deseja remover esse item");
                dialog.getBtnPosition().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bo.delete(dataItem);
                        bo.delete(dataItems);
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
        if (etIntervalo.length() <= 0) {
            flag = false;
            etIntervalo.setError("*Campo obrigatorio");
        }
        if (dtaInicio == null) {
            flag = false;
            Utils.showToast(getContext(), "Data inicio não salva");
        }
        if (dtaFim == null) {
            flag = false;
            Utils.showToast(getContext(), "Data fim não salva");
        }
        return flag;
    }

    private void salvar(boolean isSalvar) {
        if (isSalvar) {
            DataItem item = new DataItem();
            item.setDtaInicio(dtaInicio);
            item.setDtaFim(dtaFim);
            item.setIntervalo(Integer.valueOf(
                            etIntervalo.getText().toString()
            ));
            if(this.dataItem != null) {
                item.setId(this.dataItem.getId());
                bo.update(item);
                bo.delete(dataItems);
            } else {
                bo.create(item);
            }
            datasItervalos(item);
            MobileApp.setFragmentReplacePage(
                    getFragmentManager(),
                    new FgtDataItem()
            );
        }
    }

    private void datasItervalos(DataItem item) {
        Integer total =Integer.valueOf(etIntervalo.getText().toString());
        long dtaIntervalo = dtaFim.getTime() - dtaInicio.getTime();
        long valorDiv = dtaIntervalo / total;
        long montante = 0;
        for(int i = 0 ; i < total ; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(dtaInicio);
            c.add(Calendar.MILLISECOND, (int) montante);
            montante = montante + valorDiv;
            DataItem dataItem = new DataItem();
            dataItem.setPai(item);
            dataItem.setDtaIntervalo(c.getTime());
            bo.create(dataItem);
        }
    }

    private void initDados() {
        try {
            bo = new DataItemBO(getContext());
            dataItems = new ArrayList<>();
            if (getArguments() != null) {
                dataItem = bo.queryForId(getArguments().getInt("id"));
                getListaIntervaloDataItem(dataItem.getId());
            }
            carregarItemSelecionado();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getListaIntervaloDataItem(Integer id) {
        dataItems = bo.getDataItemsPai(id);
    }
    /**
     * inicializa a lista de treinos
     */
    private void initListView() {

        if (dataItems.size() == 0) {
            return;
        }
        listView.setAdapter(new AdapterDefault(getContext(), dataItems, R.drawable.car_black));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Veiculo item = (Veiculo) listView.getAdapter().getItem(position);
                FgtVeiculoAdd fgt = new FgtVeiculoAdd();
                Bundle args = new Bundle();
                args.putInt("id", item.getId());
                fgt.setArguments(args);
                MobileApp.setFragmentReplacePage(
                        getFragmentManager(),
                        fgt);
            }
        });
    }
    private void carregarItemSelecionado() {
        if (dataItem != null) {
            dtaFim = dataItem.getDtaFim();
            dtaInicio = dataItem.getDtaInicio();
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
        etIntervalo = (EditText) view.findViewById(R.id.et_intervalo);
        listView = (ListView) view.findViewById(R.id.lista);
        btnSalvar = (FloatingActionButton) view.findViewById(R.id.btn_salvar);
        btnRemover = (FloatingActionButton) view.findViewById(R.id.btn_remover);
        tvDtaInicio = (TextView) view.findViewById(R.id.tv_dta_inicio);
        tvDtaFim = (TextView) view.findViewById(R.id.tv_dta_fim);
        btnDtaInicio = (FloatingActionButton) view.findViewById(R.id.btn_dta_inicio);
        btnDtaFim = (FloatingActionButton) view.findViewById(R.id.btn_dta_fim);
    }

}
