package com.tihiy.rclint.control;

import com.tihiy.rclint.mvcAbstract.AbstractController;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 02.10.12
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class Controller extends AbstractController {

    public static final String COMMAND_RANDOM_LIST = "randomList";

    public void command(String command){
        applyCommand(command);
    }
}
