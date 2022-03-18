

import java.io.*;  

import java.net.*;  


import java.util.StringTokenizer; 
public class WebResponse {  

	static int a = 0;
  
	public static void main(String a[]) throws IOException { 
	  int queue_length = 6;   
	  int Numberforport = 2540; ///   port number
	  Socket mainsock; /// create a communication  with socket
  
	  ServerSocket updatedSocket = new ServerSocket(Numberforport, queue_length);
  
	  System.out.println("Hello from Huseyn Mammadov's MiniWebserver with port number 2540.");
	  System.out.println("Point Firefox browser to http://localhost:2540/abc.\n");
	  int i=9;
	  while (i==9) {   
		// wait for the next client connection:
		mainsock = updatedSocket.accept();
		new ListenWorker (mainsock).start();
	  }
	}
  }

class ListenWorker extends Thread {    
  Socket mainsock;                  
  ListenWorker (Socket newsock) {mainsock = newsock;} 
  public void run(){
    PrintStream exit = null;   
    BufferedReader enter = null; 
    try {
      exit = new PrintStream(mainsock.getOutputStream());
      enter = new BufferedReader
        (new InputStreamReader(mainsock.getInputStream()));

      System.out.println("Sending the HTML Response now: " + Integer.toString(WebResponse.a) + "\n" );
      String HTMLResponse = "<html> <h2> Welcome to Totaliser Program " + Integer.toString(WebResponse.a++) +  "</h2> <p><p> <hr> <p>";
     
       exit.println("HTTP/1.1 200 OK");
       exit.println("Connection: close"); 
    
       exit.println("Content-Length: 400"); 
       exit.println("Content-Type: text/html \r\n\r\n");
       exit.println(HTMLResponse);
	    
      
      String line=enter.readLine(); /
      /// GET /WebAdd.fake-cgi?person=Huseyn&num1=4&num2=5 HTTP/1.1
      
      StringTokenizer res = new StringTokenizer(line,"?"); 
      String first; 
      first= (String) res.nextElement(); 
      first= (String) res.nextElement();



      res = new StringTokenizer(first); 
      first= (String) res.nextElement();



      res = new StringTokenizer(first,"&"); 
      String name;
      name = (String) res.nextElement(); 
      String [] temp = name.split("="); 
      String nick = temp[1];
      ///exit.println(nick);





      ///exit.println(name);
      String main1; 
      main1 = (String) res.nextElement();
         ///exit.println(main1);
      String main2;
      main2 = (String) res.nextElement();
      ///exit.println(main2);


    res = new StringTokenizer(main1,"="); 
    String number1;
    number1 = (String) res.nextElement();
    number1 = (String) res.nextElement(); 
    ///exit.println(number1);

    res = new StringTokenizer(main2,"=");  
    String number2;
    number2 = (String) res.nextElement();
    number2 = (String) res.nextElement();
    ///exit.println(number2);
    


 /// Integer.toString(number1)+Integer.toString(number2);
 /// Integer.parseInt(number1)+Integer.parseInt(number2);
 int result; 
 result =0;
 result= Integer.parseInt(number1)+Integer.parseInt(number2); 
 exit.println("Hello Mr/Mrs: "+nick+ "  " +"Do you know? sum of the " +" : "+ number1 + "  and " + number2 + " are equal to " + "  " + result + "<br>");
 



      for(int j=0; j<6; j++){ 
        exit.println(enter.readLine() + "<br>\n"); 
      }                                        
      exit.println("</html>"); 
	
      mainsock.close();  
    } catch (IOException x) {
      System.out.println("Error: Connetion reset. Listening again...");
    }
  }
}



