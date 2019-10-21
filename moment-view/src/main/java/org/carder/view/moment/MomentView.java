package org.carder.view.moment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public final class MomentView extends ViewGroup {

    private RecyclerView mMomentRecyclerView;
    private MomentViewAdapter mMomentAdapter;

    public MomentView(Context context) {
        super(context);
        init();
    }

    public MomentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mMomentRecyclerView.layout(getPaddingLeft(), getPaddingTop(), getMeasuredWidth(), getMeasuredHeight());
    }

    private void init() {
        mMomentRecyclerView = new RecyclerView(getContext());
        mMomentRecyclerView.setLayoutParams(generateDefaultLayoutParams());
        mMomentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMomentRecyclerView.setPadding(0, 0, 0, 12);
        mMomentRecyclerView.addItemDecoration(new DecorationLine());
        mMomentAdapter = new MomentViewAdapter();
        mMomentAdapter.bindToRecyclerView(mMomentRecyclerView);
        addView(mMomentRecyclerView);
    }

    public void setData(List<MomentProvider> data) {
        mMomentAdapter.setNewData(data);
    }
}
