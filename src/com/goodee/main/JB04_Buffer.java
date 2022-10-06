package com.goodee.main;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class JB04_Buffer {
	public static void main(String[] args) {
		// 선언을 할때는 new가아니라 메모리영역에 있는 것을 가져오기때문에 new를 사용하지 않는다.
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		
		// DirectBuffer 이용시 allocateDirect 이용
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(100);
		
		System.out.println("buffer1 저장용량 : "+buffer1.capacity()+" 바이트");
		System.out.println("buffer2 저장용량 : "+buffer2.capacity()+" 바이트");
		
		byte[] byte1 = new byte[100];
		byte[] byte2 = new byte[7];
		
		//wrap으로 선언하면 무조건 DirectBuffer로 들어간다.
		ByteBuffer buffer3 = ByteBuffer.wrap(byte1);
		ByteBuffer buffer4 = ByteBuffer.wrap(byte2);
		
		System.out.println("buffer3 저장용량 : "+buffer3.capacity()+" 바이트");
		System.out.println("buffer4 저장용량 : "+buffer4.capacity()+" 바이트");
		
		System.out.println("---------------데이터 제어------------------");
		
		printState(buffer4);
		
		buffer4.put((byte)10);
		buffer4.put((byte)11);
		System.out.println("[2바이트 저장 후]");
		printState(buffer4);
		
		buffer4.put((byte)12);
		buffer4.put((byte)13);
		buffer4.put((byte)14);
		System.out.println("[3바이트 저장 후]");
		printState(buffer4);
		
		// flip을 실행하게 되면 position은 위치가 0으로 변하고, limit는 채워진 데이터의 끝의 위치로 변한다.
		buffer4.flip();
		System.out.println("[flip 실행 후]");
		printState(buffer4);
		
		// flip으로 채워져있는 데이터를 읽으려면 아래와 같이 사용하면 된다.
		byte[] byte3 = new byte[3];
		buffer4.get(byte3);
		System.out.println(byte3[0]+","+byte3[1]+","+byte3[2]);
		System.out.println("[3바이트 읽은 후]");
		printState(buffer4);
		
		buffer4.mark();
		System.out.println("--------------[현재 위치를 마크 해놓음]------------------");
		
		byte[] byte4 = new byte[2];
		buffer4.get(byte4);
		System.out.println(byte4[0]+","+byte4[1]);
		System.out.println("[2바이트 읽은 후]");
		printState(buffer4);
		
		buffer4.reset();
		System.out.println("---------------[position을 mark 위치로 옮김]------------");
		printState(buffer4);
		
		// remind를 실행하게되면 position만 0으로 돌아간다.
		buffer4.rewind();
		System.out.println("[rewind 실행 후]");
		printState(buffer4);
		
		// clear를 하게 되면, limit는 맨 끝으로, position은 0으로 초기화를 시킨다.
		buffer4.clear();
		System.out.println("[clear 실행 후]");
		printState(buffer4);
		
	}
	
	public static void printState(Buffer buffer) {
		System.out.print("\tposition : "+ buffer.position());
		System.out.print("\tlimit : "+ buffer.limit());
		System.out.println("\tcapacity : "+ buffer.capacity());
	}
}
