package com.jec.ramenlog.common;

/**
 * get id from current thread
 */
public class BaseContext {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * set id
     * @param id
     */
    public static void setCurrentId(int id){
        threadLocal.set(id);
    }

    /**
     * get id
     * @return
     */
    public static int getCurrentId(){
        return threadLocal.get();
    }
}