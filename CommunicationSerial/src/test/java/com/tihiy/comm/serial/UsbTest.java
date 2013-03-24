package com.tihiy.comm.serial;

import org.junit.Test;

import javax.usb.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 20.03.13
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 */
public class UsbTest {
    @Test
    public void testUsbLib() throws  UsbException{
        UsbServices services = UsbHostManager.getUsbServices();
        UsbHub root = services.getRootUsbHub();
        listDevices(root);
    }

    public static void listDevices(UsbHub hub) {
        List devices = hub.getAttachedUsbDevices();
        Iterator iterator = devices.iterator();
        while (iterator.hasNext()) {
            UsbDevice device = (UsbDevice) iterator.next();
            System.out.println(device);
            if (device.isUsbHub()) {
                listDevices((UsbHub) device);
            }
        }
    }
}
