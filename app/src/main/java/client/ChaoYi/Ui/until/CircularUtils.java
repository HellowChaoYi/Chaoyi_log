package client.ChaoYi.Ui.until;

import android.content.Context;

/**
 * Created by WCY on 2019/2/26.
 */

public class CircularUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
