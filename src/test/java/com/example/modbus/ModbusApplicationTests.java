package com.example.modbus;

import com.example.modbus.modbus.readwrite.ReadWriteUtils;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModbusApplicationTests {

	@Autowired
	private ReadWriteUtils readWrite;

	@Test
	public void contextLoads() throws ModbusTransportException, ErrorResponseException, ModbusInitException {
//		boolean[] b = {false, false, false, false, false, false, false, false, false, false};
//		boolean bb = readWrite.writeCoils(1, 0, b);
//		boolean[] boo = readWrite.readCoilStatus(1, 0, 12);
//		for(boolean s : boo){
//			System.out.println(s);
//		}
//		short[] shorts = readWrite.readHoldingRegister(1, 0, 111);
//		for (short s : shorts){
//			System.out.println(s);
//		}

	}


}
