package com.example.modbus;

import com.example.modbus.modbus.master.TcpMaster;
import com.example.modbus.modbus.readwrite.ReadWriteUtils;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

/**
 * Created by mj on 2019/2/22.
 *
 */
public class TestMain {

    public static void main(String[] args) throws ModbusTransportException, ErrorResponseException, ModbusInitException {
//        ModbusMaster tcpMaster = TcpMaster.getMaster("127.0.0.1", 502);
//        ReadWriteUtils readWrite = new ReadWriteUtils();
//        readWrite.setTcpMaster(tcpMaster);
//        short[] shorts = readWrite.readHoldingRegister(1, 0, 2);
//        for (short s : shorts){
//            System.out.println(s);
//        }
    }

}
