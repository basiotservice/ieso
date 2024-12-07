public void onStart() throws Exception
{
  // Setup the first timer to start execution
  updateTimer();
}

public void onExecute() 
  throws Exception
{
  // Create a new thread, with the starting point set as the current program 
  // object.

  Thread thread = new Thread(this, getProgram().getName());
  
  // Start the thread
  thread.start();
  updateTimer();
}

public void run()
{
  //http://www.ieso.ca/-/media/files/ieso/uploaded/chart/price_multiday.xml
        try
        {
        URL url=new URL("http://reports.ieso.ca/public/RealtimeMktPrice/PUB_RealtimeMktPrice.xml");         
        HttpURLConnection si=(HttpURLConnection) url.openConnection();
        si.setRequestMethod("GET");
        
        int responseCode = si.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode); 
        
         try {
              BufferedReader in = new BufferedReader(
              new InputStreamReader(si.getInputStream())); 
  
    
                //StringBuffer response = new StringBuffer();
                String line;
              
              int i=0;
              int mm=0;
              int priceLine=0;
                
              
                while ((line = in.readLine()) != null) {
//                    response.append(line);
                i++;

                
                if (i==10)
                {
                System.out.println("*************reading realtime price*************");
                System.out.println(line);
                getTime().setValue(line.substring(11,30));
                mm=Integer.parseInt(line.substring(25,27));                
                System.out.println("Minutes: "+mm);
                int temp = mm/5;
                priceLine = (temp ==0)? 76: 21+5*(temp-1);

                System.out.println("Realtime price on line "+priceLine); 
                } 
                //System.out.println(i);
                if (i==priceLine)
                {                
                  System.out.println("line "+i+": "+line);
                  int a=line.indexOf("</MCP>");
                  
                  getOut().setValue(Double.parseDouble((line.substring(5,a))));
                  System.out.println("*********reading completed********************");
                  
                  break;
                }
                
                }//end while
                in.close();
                }
           catch (Exception e){System.out.print("stopped premature #1");}               
        }
        catch (Exception e){System.out.print("stopped premature #2");}
}

void updateTimer()
{            
  if (ticket != null) ticket.cancel();
  BAbsTime now=BAbsTime.now();
  int m= now.getMinute();
  int a= (((m-2)/5 + 1)*5 + 3-m)*60;
  BAbsTime next=now.add(BRelTime.makeSeconds(a));
  ticket = Clock.schedule(getProgram(), next, BProgram.execute, null);
  System.out.println("next action scheduled at "+next);
} 

Clock.Ticket ticket;
