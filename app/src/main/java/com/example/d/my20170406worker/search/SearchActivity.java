package com.example.d.my20170406worker.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.d.my20170406worker.R;


public class SearchActivity extends Activity implements View.OnClickListener {
    private EditText mEditText;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    public void initViews() {
        mEditText = (EditText) findViewById(R.id.Search_mSear);
        mBtn= (Button) findViewById(R.id.Search_mBtnSearch);
        mBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Search_mBtnSearch:
                Intent intent = new Intent(SearchActivity.this,TwoActivity.class );
                intent.putExtra("search", mEditText.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
