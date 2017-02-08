package myself.obernardovieira.spendme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import myself.obernardovieira.spendme.Core.SpendMeApp;
import myself.obernardovieira.spendme.Database.CategoryDataTable;

public class CategoryActivity extends Activity {

    private FrameLayout frame_color;
    //
    private SeekBar seekRed;
    private SeekBar seekGreen;
    private SeekBar seekBlue;
    //
    private TextView spendCategoryChar;
    //
    private SpendMeApp application;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        application = (SpendMeApp) getApplication();
        //
        spendCategoryChar = (TextView) findViewById(R.id.tv_spend_category_char);
    }

    public void onClickFrameColor(View view)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_color_picker);

        frame_color = (FrameLayout) dialog.findViewById(R.id.frame_color);
        //
        seekRed = (SeekBar) dialog.findViewById(R.id.sb_red);
        seekGreen = (SeekBar) dialog.findViewById(R.id.sb_green);
        seekBlue = (SeekBar) dialog.findViewById(R.id.sb_blue);
        //
        Button dialogButtonOk = (Button) dialog.findViewById(R.id.button_ok);
        dialogButtonOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FrameLayout frameColor = (FrameLayout) findViewById(R.id.frame_category_color);
                int background_color = Color.argb(255,
                        seekRed.getProgress(),
                        seekGreen.getProgress(),
                        seekBlue.getProgress()
                );
                frameColor.setBackgroundColor(background_color);
                spendCategoryChar.setBackgroundColor(background_color);
                dialog.dismiss();
            }
        });
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.button_cancel);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
            }
        });

        seekRed.setOnSeekBarChangeListener(seekBarChanged);
        seekGreen.setOnSeekBarChangeListener(seekBarChanged);
        seekBlue.setOnSeekBarChangeListener(seekBarChanged);

        dialog.show();
    }

    SeekBar.OnSeekBarChangeListener seekBarChanged = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b)
        {
            frame_color.setBackgroundColor(
                Color.argb(255,
                        seekRed.getProgress(),
                        seekGreen.getProgress(),
                        seekBlue.getProgress()
                )
            );
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    };

    public void onEditOrCreateCategory(View view)
    {
        FrameLayout frameColor = (FrameLayout) findViewById(R.id.frame_category_color);
        int background_color = ((ColorDrawable)frameColor.getBackground()).getColor();

        CategoryDataTable.add(background_color,
                ((EditText) findViewById(R.id.et_category_name)).getText().toString(),
                ((EditText) findViewById(R.id.et_category_character)).getText().toString());
        application.updateCategoriesList = true;
        finish();
    }
}
