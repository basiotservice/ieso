public void onStart() throws Exception
{
  // start up code here
  hi=0;
}

public void onExecute() throws Exception
{
  // execute code (set executeOnChange flag on inputs)
  double in=getIn().getValue();
  int m=Integer.parseInt(getInTime().substring(14,16));
  
  switch(m/5)
  {
    case 1:
      getP01().setValue(in);
      getP02().setValue(0);
      getP03().setValue(0);
      getP04().setValue(0);
      getP05().setValue(0);
      getP06().setValue(0);
      getP07().setValue(0);
      getP08().setValue(0);
      getP09().setValue(0);
      getP10().setValue(0);
      getP11().setValue(0);
      getP12().setValue(0);
      break;
    case 2:
      getP02().setValue(in);
      break;    
    case 3:
      getP03().setValue(in);
      break;
    case 4:
      getP04().setValue(in);
      break;       
    case 5:
      getP05().setValue(in);
      break;
    case 6:
      getP06().setValue(in);
      break;
    case 7:
      getP07().setValue(in);
      break;                 
    case 8:
      getP08().setValue(in);
      break;       
    case 9:
      getP09().setValue(in);
      break;
    case 10:
      getP10().setValue(in);
      break;       
    case 11:
      getP11().setValue(in);
      break;       
    case 0:
      getP12().setValue(in);
      break;
    default:
      break;       
    }  

  if (in>=35)
  {
    BEmailAddress[] e= new BEmailAddress[5];
    e[0]=BEmailAddress.make("1234567890@txt.bell.ca");//sw

     
    hi+=100;  
    BEmail email=new BEmail(BEmailAddressList.make(e),"CHPSOP","$"+in+"/MWh at "+getInTime());
    email.send();
  }

  if (in>=25 && in <35)
  {
    hi++;
    
    if (hi==3)
    {   
    BEmailAddress[] e= new BEmailAddress[5];
    e[0]=BEmailAddress.make("4168864475@txt.bell.ca");//sw
    e[1]=BEmailAddress.make("4165737454@txt.bell.ca");//ak
    e[2]=BEmailAddress.make("2264022839@txt.bell.ca");//Meet  
    e[3]=BEmailAddress.make("9052423765@txt.bell.ca");//aj  
    e[4]=BEmailAddress.make("6476165190@txt.bell.ca");//kw
      
    BEmail email=new BEmail(BEmailAddressList.make(e),"CHPSOP","3 times over $30. Now $"+in+"/MWh at "+getInTime());
    email.send();
    }
  }  
  
  
  if (hi>0 && in<25)
  {  
    BEmailAddress[] e= new BEmailAddress[5];
    e[0]=BEmailAddress.make("4168864475@txt.bell.ca");//sw
    e[1]=BEmailAddress.make("4165737454@txt.bell.ca");//ak
    e[2]=BEmailAddress.make("2264022839@txt.bell.ca");//Meet 
    e[3]=BEmailAddress.make("9052423765@txt.bell.ca");//aj  
    e[4]=BEmailAddress.make("6476165190@txt.bell.ca");//kw 
    
    hi=0;  
    BEmail email=new BEmail(BEmailAddressList.make(e),"CHPSOP","back to $"+in+"/MWh at "+getInTime());
    email.send();
  }  
  
}

public void onStop() throws Exception
{
  // shutdown code here
}

public void test() throws Exception
{
  BEmail email=new BEmail(BEmailAddress.make("6474445894@txt.bell.ca"),"CHPSOP test","$"+getIn().getValue()+"/MWh at "+getInTime());
}


int hi;
