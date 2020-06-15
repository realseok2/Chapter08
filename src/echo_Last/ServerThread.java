package echo_Last;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {

			System.out.println("[클라이언트와 연결되었습니다.]");

			// server가 메세지 수신 Stream------------------------------------------7번
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// server가 메세지 발신 Stream------------------------------------------8번
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			// server의 메세지 수신 발신 반복문
			while (true) {

				// server가 메세지 수신
				// 코드를 간단하게 작성하기 위해서 msg에 br.readLine()을 담아줍니다.
				String msg = br.readLine();
				System.out.println("받은 메세지 : " + msg);

				// msg가 null값을 받으면 break합니다.
				if (msg == null) {
					System.out.println("클라이언트 접속 종료");
					break;
				}

				// server가 메세지 발신
				// br.write(msg)를 통하여 메세지를 작성합니다.
				// br.newLine()을 통하여 새로운 줄로 이동합니다.
				// br.flush()를 통하여 강제로 푸쉬합니다.
				bw.write(msg);
				bw.newLine();
				bw.flush();
			} // while

			bw.close();
			br.close();

		} catch (IOException e) {
			System.out.println("예외상황 발생");
		}

	}

}
