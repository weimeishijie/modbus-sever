package com.example.modbus.modbus.slave;

import com.serotonin.modbus4j.ProcessImageListener;

/**
 * Created by mj on 2019/2/20.
 * 监听线圈和寄存器
 */
public class BasicProcessImageListener implements ProcessImageListener {
    @Override
    public void coilWrite(int offset, boolean oldValue, boolean newValue) {
        System.out.println("Coil at " + offset + " was set from " + oldValue + " to " + newValue);
    }

    @Override
    public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
        System.out.println("HoldRrgister at " + offset + " was set from " + oldValue + " to " + newValue);
    }
}
