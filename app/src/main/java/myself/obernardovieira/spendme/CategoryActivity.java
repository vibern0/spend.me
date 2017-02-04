package myself.obernardovieira.spendme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class CategoryActivity extends Activity {

    FrameLayout frame_color;
    //
    SeekBar seekRed;
    SeekBar seekGreen;
    SeekBar seekBlue;
    //
    TextView spendCategoryChar;
    //
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
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
        spendCategoryChar = (TextView) findViewById(R.id.tv_spend_category_char);
        //
        handler = new Handler();
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
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    frame_color.setBackgroundColor(
                            Color.argb(255,
                                    seekRed.getProgress(),
                                    seekGreen.getProgress(),
                                    seekBlue.getProgress()
                            )
                    );
                }
            });
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    };
}
