package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */

public interface OnSnapListener {

    /**
     * Called when RecyclerView is snapped
     *
     * @param position snapped position
     */
    void snapped(int position);
}
