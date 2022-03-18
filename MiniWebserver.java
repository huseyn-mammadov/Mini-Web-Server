/* MiniWebserver.java



1. How MIME-types are used to tell the browser what data is coming.
Mime types related with file's extensions for this reason mime types know what kind data are receiving. They are used for recognizing data for example audio, message , model, text(readable text).
Main purpose related with headers, from http requests or messages
there are 2 types of mime which mentioned below
2. How you would return the contents of requested files of type HTML
(text/html)
setContentType("");
I think that "content type  text/html" helps to find what kind of data is coming and receiving from another side
if extension come with .html, it means comes with html file
3. How you would return the contents of requested files of type TEXT
(text/plain)
I need to text's length, and if extension .txt
setContentType("");


*/

import java.io.*;  /// from library it is for library display input and output

import java.net.*;  /// function for network


import java.util.StringTokenizer; /// it is a library function to slice and insert client's number or name
public class WebResponse {  /// project name and class description

	static int a = 0;
  
	public static void main(String a[]) throws IOException { /// it is a main functuon 
	  int queue_length = 6;   /// waiting list continue with 6 requests
	  int Numberforport = 2540; ///   port number
	  Socket mainsock; /// create a communication  with socket
  
	  ServerSocket updatedSocket = new ServerSocket(Numberforport, queue_length);
  
	  System.out.println("Hello from Huseyn Mammadov's MiniWebserver with port number 2540."); /// displaying welcome statement
	  System.out.println("Point Firefox browser to http://localhost:2540/abc.\n");
	  int i=9;
	  while (i==9) {   //// if it is true continue
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
      /// header with style which called h2 related with size of header's string
       exit.println("HTTP/1.1 200 OK");
       exit.println("Connection: close"); 
    
       exit.println("Content-Length: 400"); 
       exit.println("Content-Type: text/html \r\n\r\n");
       exit.println(HTMLResponse);
	    
      
      String line=enter.readLine(); /// reads line and I can modify everthing in this string
      /// GET /WebAdd.fake-cgi?person=Huseyn&num1=4&num2=5 HTTP/1.1
      /// I used stringtokenizer function instead of slicing or finding index
      StringTokenizer res = new StringTokenizer(line,"?"); /// taking person name which beging with ? sign.
      String first; /// first process with begin first string
      first= (String) res.nextElement(); /// I will pass strings with this statement
      first= (String) res.nextElement();



      res = new StringTokenizer(first); /// again equal to res to easily manipulation
      first= (String) res.nextElement();



      res = new StringTokenizer(first,"&"); /// find and insert user's name which is begin & sign
      String name;
      name = (String) res.nextElement(); 
      String [] temp = name.split("="); // I couldnt delete person because I begin to manipulate with integers howevers it is beginning with string
/// I used to create list. After that I split and slice with index
      String nick = temp[1];
      ///exit.println(nick);





      ///exit.println(name);
      String main1; /// continue to find integers which are my main purposes to sum these numbers
      main1 = (String) res.nextElement();
         ///exit.println(main1);
      String main2;
      main2 = (String) res.nextElement();
      ///exit.println(main2);


    res = new StringTokenizer(main1,"="); /// Now I come to first integers which is begin after = sign  
    String number1;
    number1 = (String) res.nextElement();
    number1 = (String) res.nextElement(); //// find and insert
    ///exit.println(number1);

    res = new StringTokenizer(main2,"=");  /// second integer or number same process
    String number2;
    number2 = (String) res.nextElement();
    number2 = (String) res.nextElement();
    ///exit.println(number2);
    


 /// Integer.toString(number1)+Integer.toString(number2);
 /// Integer.parseInt(number1)+Integer.parseInt(number2);
 int result; /// I calculate numbers and create a new integers to use at println statement
 result =0;
 result= Integer.parseInt(number1)+Integer.parseInt(number2); /// it is calculate with first and second number
 exit.println("Hello Mr/Mrs: "+nick+ "  " +"Do you know? sum of the " +" : "+ number1 + "  and " + number2 + " are equal to " + "  " + result + "<br>");
 



      for(int j=0; j<6; j++){ 
        exit.println(enter.readLine() + "<br>\n"); 
      }                                        
      exit.println("</html>"); 
	
      mainsock.close();  /// shutting the system
    } catch (IOException x) {
      System.out.println("Error: Connetion reset. Listening again...");
    }
  }
}



