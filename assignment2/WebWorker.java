/**
 * Web worker: an object of this class executes in its own new thread
 * to receive and respond to a single HTTP request. After the constructor
 * the object executes on its "run" method, and leaves when it is done.
 *
 * One WebWorker object is only responsible for one client connection. 
 * This code uses Java threads to parallelize the handling of clients:
 * each WebWorker runs in its own thread. This means that you can essentially
 * just think about what is happening on one client at a time, ignoring 
 * the fact that the entirety of the webserver execution might be handling
 * other clients, too. 
 *
 * This WebWorker class (i.e., an object of this class) is where all the
 * client interaction is done. The "run()" method is the beginning -- think
 * of it as the "main()" for a client interaction. It does three things in
 * a row, invoking three methods in this class: it reads the incoming HTTP
 * request; it writes out an HTTP header to begin its response, and then it
 * writes out some HTML content for the response content. HTTP requests and
 * responses are just lines of text (in a very particular format). 
 *
 **/

import java.net.Socket;
import java.lang.Runnable;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.util.TimeZone;

public class WebWorker implements Runnable {

	private Socket socket;
	private String fileName;
	private File myFile;

	public WebWorker(Socket s) {
		socket = s;
	}

	/**
	 * Worker thread starting point. Each worker handles just one HTTP request
	 * and then returns, which destroys the thread. This method assumes that
	 * whoever created the worker created it with a valid open socket object.
	 **/
	public void run() {
		System.err.println("Handling connection...");
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			readHTTPRequest(is);
			
			String type = checkContentType();
			writeHTTPHeader(os, type);



			writeContent(os);
			os.flush();
			socket.close();
		} catch (Exception e) {
			System.err.println("Output error: " + e);
		}
		System.err.println("Done handling connection.");
		return;
	}

	/**
	 * Read the HTTP request header.
	 **/
	private void readHTTPRequest(InputStream is) {
		String line;
		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		int count = 0;
		while (true) {
			try {
				while (!r.ready())
					Thread.sleep(1);
				line = r.readLine();
				
				if(count == 0) {
					String[] tokens = line.split(" ");
					fileName = tokens[1].substring(1);
					
				}
				count++;
				System.err.println("Request line: (" + line + ")");

				if (line.length() == 0)
					break;
			} catch (Exception e) {
				System.err.println("Request error: " + e);
				break;
			}
		}
		return;
	}

	/**
	 * Write the HTTP header lines to the client network connection.
	 * 
	 * @param os
	 *            is the OutputStream object to write to
	 * @param contentType
	 *            is the string MIME content type (e.g. "text/html")
	 **/
	private void writeHTTPHeader(OutputStream os, String contentType)
			throws Exception {
			Date d = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			df.setTimeZone(TimeZone.getTimeZone("GMT-6:00"));
			
			myFile = new File(fileName);
			if(fileName.isEmpty() || myFile.exists()) {
				os.write("HTTP/1.1 200 OK\n".getBytes());
			}
			else {
				os.write("HTTP/1.1 404 Not Found\n".getBytes());
			}

			os.write("Date: ".getBytes());
			os.write((df.format(d)).getBytes());
			os.write("\n".getBytes());
			os.write("Server: Jon's very own server\n".getBytes());
			// os.write("Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT\n".getBytes());
			// os.write("Content-Length: 438\n".getBytes());
			os.write("Connection: close\n".getBytes());
			os.write("Content-Type: ".getBytes());
			os.write(contentType.getBytes());
			os.write("\n\n".getBytes()); // HTTP header ends with 2 newlines
			return;
	}

	/**
	 * Write the data content to the client network connection. This MUST be
	 * done after the HTTP header has been written out.
	 * 
	 * @param os
	 *            is the OutputStream object to write to
	 **/
	private void writeContent(OutputStream os) throws Exception {
		myFile = new File(fileName);
		if(!myFile.exists()) {
			myFile = new File("myfile/404.html");
			Scanner sc1 = new Scanner(myFile);
			while(sc1.hasNext()) {
				String l = sc1.nextLine();
				os.write(l.getBytes());
			}
			sc1.close();
		}
		else {
			if(checkContentType().equals("text/html")) {
		
				Scanner sc = new Scanner(myFile);
				while(sc.hasNext()) {
					String l = sc.nextLine();
					if(l.contains("<cs371date>")) {
						Date d1 = new Date();
						DateFormat df = DateFormat.getDateTimeInstance();
						df.setTimeZone(TimeZone.getTimeZone("GMT-6:00"));
						os.write("Date: ".getBytes());
						os.write((df.format(d1)).getBytes());
						os.write("\n".getBytes());
					}

					if(l.contains("<cs371server>")) {
						os.write("Server: Hang's very own server\n".getBytes());
					}
					os.write(l.getBytes());
				}
				sc.close();
			}

			else {
				byte[] bFile = new byte[(int) myFile.length()];
				FileInputStream fis = new FileInputStream(fileName);
				fis.read(bFile);
				for(int i = 0; i < bFile.length; i++) {
					os.write(bFile[i]);
				}
				fis.close();
			}
		}
	}

	public String checkContentType() {
		String contentType = null;

		if(fileName.contains(".html")) {
			contentType = "text/html";
		}
		else if(fileName.contains(".jpg") || fileName.contains(".jpeg")) {
			contentType = "image/jpg";
		}
		else if(fileName.contains(".png")) {
			contentType = "image/png";
		}
		else if(fileName.contains(".gif"))  {
			contentType = "image/gif";
		} 

		return contentType;
	}

	/*public boolean exist() {

		if(fileName.contains(".html") || fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".png") || fileName.contains(".gif")) {
			if(myFile.exists()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			myFile = new File("myfile/404.html");
						
		return false;
	
		}
	}*/

} // end class
