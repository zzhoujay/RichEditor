package com.zzhoujay.richeditor.callback;

/**
 * Created by zhou on 2016/11/29.
 * 光标监听器
 */
public interface CursorChangeListener {

    /**
     * 光标发送变化时被调用
     *
     * @param newPosition 光标的新位置
     */
    void cursorChange(int newPosition);

}
