package com.zzhoujay.richeditor.callback;

/**
 * Created by zhou on 2016/11/25.
 * 光标提供器
 */
public interface CursorProvider {

    /**
     * 获取光标开始位置
     *
     * @return start position
     */
    int getCursorStart();

    /**
     * 获取光标结束位置
     *
     * @return end position
     */
    int getCursorEnd();

}
