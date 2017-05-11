package br.com.unitri.jheimesilveira.locacaoveiculo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Categoria;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Cliente;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.DataItem;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Funcionario;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Locacao;
import br.com.unitri.jheimesilveira.locacaoveiculo.domain.Veiculo;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String databaseName = "locacaoVeiculo.db";
    private static final int databaseVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, Cliente.class);
            TableUtils.createTable(cs, Categoria.class);
            TableUtils.createTable(cs, Veiculo.class);
            TableUtils.createTable(cs, Funcionario.class);
            TableUtils.createTable(cs, Locacao.class);
            TableUtils.createTable(cs, DataItem.class);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Cliente.class, true);
            TableUtils.dropTable(cs, Categoria.class, true);
            TableUtils.dropTable(cs, Veiculo.class, true);
            TableUtils.dropTable(cs, Funcionario.class, true);
            TableUtils.dropTable(cs, Locacao.class, true);
            TableUtils.dropTable(cs, DataItem.class, true);
            onCreate(sd, cs);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        super.close();
    }
}
