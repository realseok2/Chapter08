package echo_Last;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	// Server와 Client간의 채팅 프로그램 코드입니다.

	public static void main(String[] args) throws IOException {

		// ServerSocket 객체 생성-------------------------------------------------1번
		ServerSocket serverSocket = new ServerSocket();

		// bind 연결-----------------------------------------------------------------2번
		// InetSocketAddress에는 "" 안에 아이피 주소를 넣고 뒤에 포트번호를 넣습니다.
		serverSocket.bind(new InetSocketAddress("192.168.0.121", 10001));
//		192.168.0.117
		// serverSocket.accept()를 통하여 클라이언트와의 연결을 허용합니다.----5번
		System.out.println("<서버시작>");
		System.out.println("================================");

//////////////////////////////////////////////////////////////////

		// ---------------------------------------------------------------------------반복시작
		// 구간

		while (true) {
			System.out.println("[연결을 기다리고 있습니다.]");
			Socket socket = serverSocket.accept();
			Thread thread = new ServerThread(socket);
			thread.start();

		} // while

//		System.out.println("<서버 종료>");
//		serverSocket.close();

	}

}
