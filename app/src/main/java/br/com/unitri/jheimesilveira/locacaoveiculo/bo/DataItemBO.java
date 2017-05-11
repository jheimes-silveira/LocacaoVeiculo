package br.com.unitri.jheimesilveira.locacaoveiculo.bo;

import android.content.Context;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unitri.jheimesilveira.locacaoveiculo.db.GenericCRUD;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.DataItem;

/**
 * Created by jheimes on 04/04/17.
 */

public class DataItemBO extends GenericCRUD<DataItem, Integer> {

    public DataItemBO(Context context) throws SQLException {
        super(context, DataItem.class, DataItem.class.getSimpleName());
    }

    public List<DataItem> getDataItemsPai(Integer id) {

        String query = "SELECT d.id FROM DataItem d WHERE d.idItemPai = ?";
        List<DataItem> list = new ArrayList<>();

        GenericRawResults<String[]> results = null;
        try {
            results = queryRaw(query, id.toString());
            List<String[]> result = results.getResults();
            for (int i = 0 ; i < result.size() ; i++) {
                list.add(queryForId(Integer.valueOf(result.get(i)[0])));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DataItem> queryPais() {

        String query = "SELECT d.id FROM DataItem d WHERE d.intervalo <> 0";
        List<DataItem> list = new ArrayList<>();

        GenericRawResults<String[]> results = null;
        try {
            results = queryRaw(query);
            List<String[]> result = results.getResults();
            for (int i = 0 ; i < result.size() ; i++) {
                list.add(queryForId(Integer.valueOf(result.get(i)[0])));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
