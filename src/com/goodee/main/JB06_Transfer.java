package com.goodee.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class JB06_Transfer {
	
	public static void main(String[] args) throws Exception{
	
		FileChannel fc1 = new FileInputStream("D:/sample/NIO1.txt").getChannel();
		FileChannel fc2 = new FileOutputStream("D:/sample/NIO4.txt").getChannel();
		
		// transferTo(시작지점 , 복사할 사이즈, 타겟)
		// fc1.transferTo(0, fc1.size(), fc2);
		
		// transferFrom(소스, 시작지점, 복사할 사이즈)
		fc2.transferFrom(fc1, 0, fc1.size());
	
		
	
	}
}	
