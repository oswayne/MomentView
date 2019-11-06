package org.oswayne.view.moment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DecorationLine extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = 16;
        outRect.bottom = 16;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);

        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            c.drawRect(8, parent.getChildAt(i).getBottom() + 32, parent.getWidth() - 8, parent.getChildAt(i).getBottom() + 33, paint);
        }
    }
}
