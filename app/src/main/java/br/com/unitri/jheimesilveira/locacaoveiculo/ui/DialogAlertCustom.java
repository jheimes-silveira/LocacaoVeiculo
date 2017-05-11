package br.com.unitri.jheimesilveira.locacaoveiculo.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;


/**
 * Created by jheimesilveira on 27/02/2016.
 */
public class DialogAlertCustom extends Dialog {

    private Button btnPosition;
    private Button btnNegative;

    private TextView tvMessage;
    private TextView tvTitle;

    /**
     * Construtor
     * @param context
     */
    public DialogAlertCustom(Context context) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert_custom);

        int width = getWindow().getAttributes().width;
        int height = getWindow().getAttributes().height;
        getWindow().setLayout(width, height);
    }
    

    public void show(String title, String menssage) {
        super.show();
        btnPosition = (Button) findViewById(R.id.dialog_alert_custom_btn_positive);
        btnNegative = (Button) findViewById(R.id.dialog_weight_btn_negative);
        tvMessage = (TextView) findViewById(R.id.dialog_alert_custom_tv_msg);
        tvTitle = (TextView) findViewById(R.id.dialog_alert_custom_tv_title);
        initTitle(title);
        initMessage(menssage);
        initBtnNegative();
    }

    private void initBtnNegative() {
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initTitle(String title) {
        tvTitle.setText(title);
    }

    private void initMessage(String menssage) {
        tvMessage.setText(menssage);
    }

    public Button getBtnPosition() {
        return btnPosition;
    }

    public void setBtnPosition(Button btnPosition) {
        this.btnPosition = btnPosition;
    }

    public Button getBtnNegative() {
        return btnNegative;
    }

    public void setBtnNegative(Button btnNegative) {
        this.btnNegative = btnNegative;
    }

    public TextView getTvMessage() {
        return tvMessage;
    }

    public void setTvMessage(TextView tvMessage) {
        this.tvMessage = tvMessage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }
}
