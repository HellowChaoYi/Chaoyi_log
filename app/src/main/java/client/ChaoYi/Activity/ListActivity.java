package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import client.ChaoYi.R;
import client.ChaoYi.Ui.CustomTitleBar;

public class ListActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        mTitleTextView = (TextView) findViewById(R.id.text_title);
//        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
//        mBackwardbButton = (Button) findViewById(R.id.button_backward);
//        showBackwardView(R.string.text_back,true);
//        showForwardView(R.string.text_forward,true);
        CustomTitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListActivity.this, "左边", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
