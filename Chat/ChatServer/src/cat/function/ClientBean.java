package cat.function;

import java.net.Socket;
/**
 * @author: sly
 * @date:2017��2��28������11:00:29
 */
public class ClientBean {
	private String name;
	private Socket socket;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
