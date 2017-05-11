package br.com.unitri.jheimesilveira.locacaoveiculo.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.com.unitri.jheimesilveira.locacaoveiculo.Adapter.AdapterSearchList;
import br.com.unitri.jheimesilveira.locacaoveiculo.R;

/**
 * Created by jheimesilveira on 27/02/2016.
 */
public class DialogSearchList<T> extends Dialog {

    /**Componente dialog showSearchList() */
    private TextView tvDialog = null;
    private ListView lvDialog = null;
    private SearchView svDialog = null;
    private List<T> list = null;
    private List<T> listFind = null;
    private boolean isMultiSelect = false;
    private FloatingActionButton floatBtnSelect = null;
    private AdapterSearchList adapter = null;
    private List<T> listSelects;

    /** texto global que sera aplicado a consulta*/
    private CharSequence txtSearchView = null;

    /**
     * Construtor
     * @param context
     */
    public DialogSearchList(Context context,
                            List<T> list,
                            List<T> listSelects,
                            boolean isMultiSelect) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_search);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setLayout(width, height);
        this.listFind = list;
        this.isMultiSelect = isMultiSelect;
        this.listSelects = listSelects;
    }

    /**
     * inicializa o componente para buscar um item desejado
     * @param title
     */
    public void show(String title) {

        show();

        tvDialog = (TextView) findViewById(R.id.dialog_seach_tv);
        lvDialog = (ListView) findViewById(R.id.dialog_seach_lv);
        svDialog = (SearchView) findViewById(R.id.dialog_seach_sv);
        floatBtnSelect = (FloatingActionButton) findViewById(R.id.fab);
        svDialog.setOnQueryTextListener(new FiltroSv());
        tvDialog.setText(title);
        initSvDialog();
        initListDialog();
        initFloatBtnSelect();
    }

    public void initToogle() {
        if (adapter.getListMultiSelect().size() > 0) {
            floatBtnSelect.show(true);
        } else {
            floatBtnSelect.hide(true);
        }
    }

    /**
     * inicializa o componente para buscar um item desejado
     */
    public void show() {

        super.show();

        tvDialog = (TextView) findViewById(R.id.dialog_seach_tv);
        lvDialog = (ListView) findViewById(R.id.dialog_seach_lv);
        svDialog = (SearchView) findViewById(R.id.dialog_seach_sv);
        floatBtnSelect = (FloatingActionButton) findViewById(R.id.fab);
        svDialog.setOnQueryTextListener(new FiltroSv());
        initSvDialog();
        initListDialog();
        initFloatBtnSelect();
    }

    private void initFloatBtnSelect() {
        initToogle();
    }

    /**
     * Implementa o metodo de pesquisa
     */
    private void initSvDialog() {
        svDialog.setOnQueryTextListener(new FiltroSv());
    }

    /**
     * filta a consulta
     */
    private class FiltroSv implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextChange(String newText) {

            txtSearchView = newText.toString();
            initListDialog();
            return false;
        }
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
    }

    private void  initListDialog() {
        list = new ArrayList<T>();
        for(T object : listFind) {
            if(txtSearchView != null) {
                if(object.toString().toUpperCase().contains(txtSearchView.toString().toUpperCase())) {
                    list.add(object);
                }
            } else {
                list.add(object);
            }
        }

        if (listSelects == null) {
            listSelects = new ArrayList<>();
        }
        if (adapter != null) {
            listSelects = getListMultiSelect();
            list.addAll(listSelects);
        }
        list = new ArrayList(new HashSet(list));
        adapter = new AdapterSearchList(getContext(), list, listSelects, isMultiSelect, this);
        lvDialog.setAdapter(adapter);
//        if(isMultiSelect) {
//            lvDialog.setOnScrollListener(new AbsListView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                    if (scrollState > 0) {
//                        floatBtnSelect.hide(true);
//                    } else {
//                        floatBtnSelect.show(true);
//                    }
//                }
//
//                @Override
//                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                }
//            });
//        }
    }

    public FloatingActionButton getFloatBtnSelect() {
        return floatBtnSelect;
    }

    public ListView getLvDialog() {
        return this.lvDialog;
    }

    public List<T> getListMultiSelect() {
        return adapter.getListMultiSelect();
    }
}
