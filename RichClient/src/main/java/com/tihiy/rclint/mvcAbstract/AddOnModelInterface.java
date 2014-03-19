package com.tihiy.rclint.mvcAbstract;

public interface AddOnModelInterface <T> {
    public void prepareState(T dataForState);
    public Object getState();
}
