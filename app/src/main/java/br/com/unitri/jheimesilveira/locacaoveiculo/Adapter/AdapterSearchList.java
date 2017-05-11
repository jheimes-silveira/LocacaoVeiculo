package br.com.unitri.jheimesilveira.locacaoveiculo.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;
import br.com.unitri.jheimesilveira.locacaoveiculo.ui.DialogSearchList;
import br.com.unitri.jheimesilveira.locacaoveiculo.utils.ViewHolder;

/**
 * @author Jheimes Santos da Silveira
 *
 */


@SuppressWarnings("ALL")
public class AdapterSearchList<T> extends BaseAdapter {

	private List<T> list = null;
	private List<T> listMultiSelect = null;
	private Context context = null;
	private TextView tvDefault = null;
	private DialogSearchList dialogSearchList;
	private boolean isMulti = false;

	public AdapterSearchList(Context context, List<T> list, List<T> listMultiSelect, boolean isMulti) {
		this.context = context;
		this.list = list;
		this.isMulti = isMulti;
		if(listMultiSelect == null) {
			this.listMultiSelect = new ArrayList<>();
		} else {
			this.listMultiSelect = listMultiSelect;
		}
	}

	public AdapterSearchList(Context context, List<T> list, List<T> listMultiSelect, boolean isMulti, DialogSearchList dialogSearchList) {
		this.context = context;
		this.list = list;
		this.isMulti = isMulti;
		this.dialogSearchList = dialogSearchList;
		if(listMultiSelect == null) {
			this.listMultiSelect = new ArrayList<>();
		} else {
			this.listMultiSelect = listMultiSelect;
		}
	}
	public List<T> getListMultiSelect() {
		notifyDataSetChanged();
		return this.listMultiSelect;
	}

	public int getCount() {
		return list.size();
	}

	public T getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.adapter_search_list, parent, false);

		final T object = list.get(position);

		ViewHolder holder = (ViewHolder) view.getTag();
		tvDefault = null;
		final CheckBox cbIsMulti = (CheckBox) view.findViewById(R.id.adapter_cb_is_multi);

		if (holder == null) {
			tvDefault = (TextView) view.findViewById(R.id.adapter_lt_default_txt);

			holder = new ViewHolder();
			holder.setProperty(tvDefault, "tvDefault");
			view.setTag(holder);
		}

		/**
		 * atribui texto ao campo
		 */

		tvDefault = (TextView) holder.getProperty("tvDefault");

		if(isMulti) {
			cbIsMulti.setVisibility(View.VISIBLE);
			cbIsMulti.setChecked(listMultiSelect.contains(object));

			cbIsMulti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						listMultiSelect.add(object);
					} else {
						listMultiSelect.remove(object);
					}
					if (dialogSearchList != null) {
						dialogSearchList.initToogle();
					}
				}
			});

		} else {
			cbIsMulti.setVisibility(View.GONE);
		}

		tvDefault.setText(object.toString());
		return view;

	}
}
