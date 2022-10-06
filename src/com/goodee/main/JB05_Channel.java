package com.goodee.main;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JB05_Channel {
	public static void main(String[] args) throws IOException {
		
		Path from = Paths.get("D:/sample/NIO1.txt");
		Path to = Paths.get("D:/sample/NIO3.txt");
		
		//FileChannel : file쪽에 direct로 접근 가능하게하는 메모리 엑세싀
		FileChannel ffrom = FileChannel.open(from, StandardOpenOption.READ);
		FileChannel fto = FileChannel.open(to, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(100);
		int byteCount;
		
		while(true) {
			// 버퍼 초기화
			buffer.clear();
					System.out.println("-----clear 후 -----");
					printState(buffer);
					System.out.println("");
			// 버퍼에 있는 내용을 받기
			byteCount = ffrom.read(buffer);
					System.out.println("-----read 후 -----");
					System.out.println("byteCount : "+byteCount);
					printState(buffer);
					System.out.println("");
			// 만약 해당 파일에 남은 내용이 없다면 break;를 통해 while문 빠져나가기
			if(byteCount == -1) break;
			// 버퍼 flip을 실행하여 읽기 상태로 만듦
			buffer.flip();
					System.out.println("-----filp 후 -----");
					printState(buffer);
					System.out.println("");
			// 메모리 내의 데이터를 전송할 파일로 전송
			fto.write(buffer);	
		}
		ffrom.close();
		fto.close();
		System.out.println("[파일 복사 성공]");
		
	}
		// printState
		public static void printState(Buffer buffer) {
		System.out.print("\tposition : "+ buffer.position());
		System.out.print("\tlimit : "+ buffer.limit());
		System.out.println("\tcapacity : "+ buffer.capacity());
	}
}
