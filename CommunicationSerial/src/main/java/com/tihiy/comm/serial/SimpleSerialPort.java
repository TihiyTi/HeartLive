package com.tihiy.comm.serial;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.ak.device.bytes.BytesPutable;
import com.google.common.io.Closeables;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import gnu.io.SerialPort;

public final class SimpleSerialPort implements Cloneable, Closeable/*, BytesPutable*/ {
  private static final int OPEN_DELAY_MILLIS = 500;
  private RXTXPort serialPort;

  public SerialPort open(String portName) throws Exception {
    CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);

    if (commPortIdentifier.isCurrentlyOwned()) {
      Logger.getLogger(getClass().getName()).log(Level.FINE,
          String.format("%s is currently owned by %s", toString(), commPortIdentifier.getCurrentOwner()));
      return null;
    }
    else {
      synchronized (this) {
        CommPort port = null;
        try {
          port = commPortIdentifier.open(getClass().getName(),
              (int) Math.round((Math.random() + 1) * OPEN_DELAY_MILLIS));
        }
        catch (PortInUseException ex) {
          Logger.getLogger(getClass().getName()).log(Level.FINE, String.format("%s %s", toString(), ex.getMessage()), ex);
        }

        if (port instanceof RXTXPort) {
          serialPort = (RXTXPort) port;
          serialPort.enableReceiveTimeout(100);
          serialPort.notifyOnDataAvailable(true);
        }
        return serialPort;
      }
    }
  }

//  @Override
//  public void put(byte[] bytes) throws InterruptedException {
//    if (bytes.length > 0) {
//      try {
//        synchronized (this) {
//          if (serialPort != null) {
//            OutputStream outputStream = serialPort.getOutputStream();
//            for (int aByte : bytes) {
//              outputStream.write(aByte);
//            }
//            outputStream.flush();
//          }
//        }
//      }
//      catch (IOException ex) {
//        InterruptedException interruptedException = new InterruptedException(ex.getMessage());
//        interruptedException.initCause(ex);
//        throw interruptedException;
//      }
//    }
//  }

  @Override
  public void close() {
    try {
      synchronized (this) {
        if (serialPort != null) {
          try {
            serialPort.removeEventListener();
          }
          catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, e.getMessage(), e);
          }
          Closeables.close(serialPort.getInputStream(), true);
          Closeables.close(serialPort.getOutputStream(), true);
        }
      }
    }
    catch (IOException e) {
      Logger.getLogger(getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    finally {
      synchronized (this) {
        if (serialPort != null) {
          serialPort.close();
          serialPort = null;
        }
      }
    }
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
}