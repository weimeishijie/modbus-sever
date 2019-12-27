package com.example.modbus.modbus.slave;

import com.serotonin.modbus4j.BasicProcessImage;

/**
 * Created by mj on 2019/2/20.
 * 创建线圈和寄存器
 */
public class Register {

    static BasicProcessImage getModscanProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setInvalidAddressValue(Short.MIN_VALUE);
        // 创建可读写开关量
        processImage.setCoil(0, true);
        processImage.setCoil(1, false);
        processImage.setCoil(2, true);
        processImage.setCoil(3, false);
        processImage.setCoil(4, true);
        processImage.setCoil(5, false);
        processImage.setCoil(6, true);
        processImage.setCoil(7, true);
        processImage.setCoil(8, true);
        processImage.setCoil(9, true);
        processImage.setCoil(10, true);
        processImage.setCoil(11, true);
        processImage.setCoil(12, true);

        // 创建只读开关量
        processImage.setInput(0, false);
        processImage.setInput(1, false);
        processImage.setInput(2, true);
        processImage.setInput(3, false);
        processImage.setInput(4, true);
        processImage.setInput(5, true);
        processImage.setInput(6, true);
        processImage.setInput(7, false);
        processImage.setInput(8, true);
        processImage.setInput(9, true);

        // 创建 保持寄存器(只能读的寄存器)
        processImage.setHoldingRegister(0, (short) 0);
        processImage.setHoldingRegister(1, (short) 1);
        processImage.setHoldingRegister(2, (short) 2);
        processImage.setHoldingRegister(3, (short) 3);
        processImage.setHoldingRegister(4, (short) 4);
        processImage.setHoldingRegister(5, (short) 5);
        processImage.setHoldingRegister(6, (short) 6);
        processImage.setHoldingRegister(7, (short) 7);
        processImage.setHoldingRegister(8, (short) 8);
        processImage.setHoldingRegister(9, (short) 9);

        //创建 只读寄存器（可读可写寄存器）
        processImage.setInputRegister(0, (short) 0);
        processImage.setInputRegister(1, (short) 1);
        processImage.setInputRegister(2, (short) 2);
        processImage.setInputRegister(3, (short) 3);
        processImage.setInputRegister(4, (short) 4);
        processImage.setInputRegister(5, (short) 5);
        processImage.setInputRegister(6, (short) 6);
        processImage.setInputRegister(7, (short) 7);
        processImage.setInputRegister(8, (short) 8);
        processImage.setInputRegister(9, (short) 9);

        processImage.addListener(new BasicProcessImageListener());
        return processImage;
    }


}
