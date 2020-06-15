package echo_Last;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		// Socket 객체를 생성합니다.-------------------------------------------------3번
		Socket socket = new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("============================");

		// Socket.connect를 통하여 서버와 연결을 합니다.--------------------------4번
		//	접속하고자 하는 곳의 ip주소를 입력해 주세요.
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.0.117", 10001));
		System.out.println("[서버에 연결되었습니다.]");
//		192.168.0.121	내ip주소
		
		// Client가 메세지 발신 Stream---------------------------------------------6번
		// 서버에 메세지를 보내는 용도이기 때문에 Output을 사용합니다.
		// Buffered는 쟁반이라는 그릇에 담아 한번에 작업을 합니다.
		// 쟁반 안에 양이 가득차지 않으면 보내주지 않기 때문에 bw.flush()를 사용하여 강제로 보내줍니다.
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// Client가 메세지 수신 Stream--------------------------------------------9번
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 키보드 입력
		Scanner sc = new Scanner(System.in);

		while (true) {
			String msg = sc.nextLine();

			// msg에 /q가 입력되면 break합니다.
			if ("/q".equals(msg)) {
				System.out.println("[접속을 종료합니다.]");
				break;
			}

			// Client가 메세지 발신
			bw.write("똥멍청이 : " + msg);
			bw.newLine();
			bw.flush();

			// Client가 메세지 수신
			String reMsg = br.readLine();
			System.out.println("Server : [ " + reMsg + " ]");

		} // while

		System.out.println("<Client 종료>");

		bw.close();
		br.close();
		socket.close();
		sc.close();

	}

}
