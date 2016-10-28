package com.jackhan.wgleadlife.utils.rxbus;

/**
 * @author hanhuizhong
 * @date 2016-7-27
 */
public class RxEvent {

    public final static int WHAT_LEADPLAN_ADD = 1;

    public int what;
    public String msg;
    public Object obj;

    public RxEvent(int what) {
        // TODO Auto-generated constructor stub
        this.what = what;
    }

    public RxEvent(int what, String msg) {
        // TODO Auto-generated constructor stub
        this.msg = msg;
        this.what = what;
    }

    public RxEvent(int what, String msg, Object obj) {
        // TODO Auto-generated constructor stub
        this.msg = msg;
        this.obj = obj;
        this.what = what;
    }
}
