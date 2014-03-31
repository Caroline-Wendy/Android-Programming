package mzx.spike.todolist.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by C.L.Wang on 14-3-16.
 */
public class ToDoListItemView extends TextView{

    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    public ToDoListItemView (Context context, AttributeSet ats, int ds) {
        super(context, ats, ds);
        init();
    }

    public  ToDoListItemView (Context context) {
        super(context);
        init();
    }

    public ToDoListItemView (Context context, AttributeSet ats) {
        super(context, ats);
        init();
    }

    private void init() {

        //获得对资源列表的引用
        Resources myResources = getResources();

        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(myResources.getColor(R.color.notepad_margin));

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(myResources.getColor(R.color.notepad_lines));

        paperColor = myResources.getColor(R.color.notepad_paper);
        margin = myResources.getDimension(R.dimen.notepad_margin);
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawColor(paperColor);

        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);

        //输出文本后移
        canvas.save();
        canvas.translate(margin, 0);

        super.onDraw(canvas);
        canvas.restore();
    }

}
