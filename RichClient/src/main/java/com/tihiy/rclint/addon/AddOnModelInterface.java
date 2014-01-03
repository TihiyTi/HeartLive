package com.tihiy.rclint.addon;

public interface AddOnModelInterface <T> {
    public void prepareState(T dataForState);
    public Object getState();
}
