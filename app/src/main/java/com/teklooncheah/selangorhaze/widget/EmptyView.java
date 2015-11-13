package com.teklooncheah.selangorhaze.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teklooncheah.selangorhaze.R;

/**
 * Created by tekloon on 11/13/15.
 */
public class EmptyView extends LinearLayout{

    private ImageView mIcon;
    private TextView mText;

    public EmptyView(Context context) {
        super(context);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View.inflate(getContext(), R.layout.empty_view, this);
        mIcon = (ImageView)findViewById(R.id.icon);
        mText = (TextView)findViewById(R.id.text);
    }

    public void setIcon(int res) {
        mIcon.setImageResource(res);
    }

    public void setText(String text) {
        mText.setText(text);
    }
}
