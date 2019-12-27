package com.example.modbus.modbus.readwrite;

import com.example.modbus.modbus.master.TcpMaster;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.*;
import org.springframework.stereotype.Component;

/**
 * Created by mj on 2019/2/20
 * 读写工具
 */
@Component
public class ReadWriteUtils {

    private static String HOST = "127.0.0.1";

    private static Integer PORT = 502;

    private static ModbusMaster tcpMaster = TcpMaster.getMaster(HOST, PORT);

//    private static ModbusMaster tcpMaster;
//
//    public void setTcpMaster(ModbusMaster tcpMaster) {
//        ReadWriteUtils.tcpMaster = tcpMaster;
//    }

    /**
     * 写单个（线圈）开关量数据
     *
     * @param slaveId     slave的ID
     * @param writeOffset 位置
     * @param writeValue  值
     * @return 是否写入成功
     */
    public boolean writeCoil(int slaveId, int writeOffset, boolean writeValue)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        // 发送请求并获取响应对象
        WriteCoilResponse response = (WriteCoilResponse) tcpMaster.send(request);
        return !response.isException();
    }

    /**
     * 写多个开关量数据（线圈）
     *
     * @param slaveId     slaveId
     * @param startOffset 开始位置
     * @param bdata       写入的数据
     * @return 是否写入成功
     */
    public boolean writeCoils(int slaveId, int startOffset, boolean[] bdata)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, bdata);
        // 发送请求并获取响应对象
        WriteCoilsResponse response = (WriteCoilsResponse) tcpMaster.send(request);
        return !response.isException();

    }

    /***
     *  保持寄存器写单个
     *
     * @param slaveId slaveId
     * @param writeOffset 开始位置
     * @param writeValue 写入的数据
     */
    public boolean writeRegister(int slaveId, int writeOffset, short writeValue)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求对象
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        // 发送请求并获取响应对象
        WriteRegisterResponse response = (WriteRegisterResponse) tcpMaster.send(request);
        return !response.isException();

    }

    /**
     * 保持寄存器写入多个模拟量数据
     *
     * @param slaveId     modbus的slaveID
     * @param startOffset 起始位置偏移量值
     * @param sdata       写入的数据
     * @return 返回是否写入成功
     */
    public boolean writeRegisters(int slaveId, int startOffset, short[] sdata)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求对象
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, sdata);
        // 发送请求并获取响应对象
        WriteRegistersResponse response = (WriteRegistersResponse) tcpMaster.send(request);
        return !response.isException();
    }

    /**
     * 根据类型写数据（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
     *
     * @param value    写入值
     * @param dataType com.serotonin.modbus4j.code.DataType
     */
    public void writeHoldingRegister(int slaveId, int offset, Number value, int dataType)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 类型
        BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType);
        tcpMaster.setValue(locator, value);
    }


    /**
     * 读（线圈）开关量数据
     *
     * @param slaveId slaveId
     * @param offset  位置
     * @return 读取值
     */
    public boolean[] readCoilStatus(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {

        ReadCoilsRequest request = new ReadCoilsRequest(slaveId, offset, numberOfBits);
        ReadCoilsResponse response = (ReadCoilsResponse)tcpMaster.send(request);
        boolean[] booleans = response.getBooleanData();
        return valueRegroup(numberOfBits, booleans);
    }

    /**
     * 开关数据 读取外围设备输入的开关量
     */
    public boolean[] readInputStatus(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, offset, numberOfBits);
        ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) tcpMaster.send(request);
        boolean[] booleans = response.getBooleanData();
        return valueRegroup(numberOfBits, booleans);
    }

    /**
     * 读取保持寄存器数据
     *
     * @param slaveId slave Id
     * @param offset  位置
     */
    public short[] readHoldingRegister(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, offset, numberOfBits);
        ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) tcpMaster.send(request);
        return response.getShortData();
    }

    /**
     * 读取外围设备输入的数据
     */
    public short[] readInputRegisters(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {

        ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, offset, numberOfBits);
        ReadInputRegistersResponse response = (ReadInputRegistersResponse) tcpMaster.send(request);
        return response.getShortData();
    }

    /**
     * 批量读取 可以批量读取不同寄存器中数据
     */
    public void batchRead() throws ModbusTransportException, ErrorResponseException, ModbusInitException {

        BatchRead<Integer> batch = new BatchRead<Integer>();
        batch.addLocator(0, BaseLocator.holdingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator(1, BaseLocator.inputStatus(1, 0));
        batch.setContiguousRequests(true);
        BatchResults<Integer> results = tcpMaster.send(batch);
        System.out.println("batchRead:" + results.getValue(0));
        System.out.println("batchRead:" + results.getValue(1));

    }

    private boolean[] valueRegroup(int numberOfBits, boolean[] values) {
        boolean[] bs = new boolean[numberOfBits];
        int temp = 1;
        for (boolean b : values) {
            bs[temp - 1] = b;
            temp++;
            if (temp > numberOfBits)
                break;
        }
        return bs;
    }



}
