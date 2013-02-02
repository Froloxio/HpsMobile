import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UniqueKeyGenerator
{
  public UniqueKeyGenerator()
  {
  }
  public static void main(String[] args) throws Exception {
	  UniqueKeyGenerator generator = new UniqueKeyGenerator();
	  for(int i = 0; i < 5; i++) {
		  	  String key = generator.generatePrimaryKey();
	  
		  	  System.out.println(key);
	  }

  }
  
  // Synchronized to help prevent multiple threads from generating the same key at the same time
  static public synchronized String generatePrimaryKey()
  {
     long time, loc, rand;
     StringBuffer result = new StringBuffer();

     try
     {
        loc = Math.abs(InetAddress.getLocalHost().hashCode());
     }
     catch(Exception e)
     {
        loc = (long)(Math.random() * 2000);
     }
     
     time = System.currentTimeMillis();
     rand = (long)(Math.random() * 100000);
     
     // 15 char max
     result.append("ZZ"); // prefix so all newly generated keys are sorted last
     result.append(numberToKey(time, 9));
     result.append(numberToKey(loc, 1));
     result.append(numberToKey(rand, 3));

     return result.toString(); 
  }
  
  // Synchronized to help prevent multiple threads from generating the same key at the same time
  static public synchronized String generatePrimaryKeyEx()
  {
     long time, loc, rand;
     StringBuffer result = new StringBuffer();

     try
     {
        loc = Math.abs(InetAddress.getLocalHost().hashCode());
     }
     catch(Exception e)
     {
        loc = (long)(Math.random() * 2000);
     }
     
     time = System.currentTimeMillis();
     rand = (long)(Math.random() * 100000000);
     
     // 15 char max
     result.append(numberToKey(time, 9));
     result.append(numberToKey(loc, 1));
     result.append(numberToKey(rand, 5));

     return result.toString(); 
  }
  
  static public synchronized String generateRandomKey()
  {
     long rand1, rand2, rand3;
     StringBuffer result = new StringBuffer();

     rand1 = (long)(Math.random() * 100000000);
     rand2 = (long)(Math.random() * 100000000);
     rand3 = (long)(Math.random() * 100000000);

     result.append(numberToKey(rand1, 5));
     result.append(numberToKey(rand2, 5));
     result.append(numberToKey(rand3, 5));

     return result.toString();
  }

  private static String numberToKey(long value, int length)
  {
     StringBuffer result = new StringBuffer();
     long diff;

     while( value >= 1 && result.length() < length )
     {
        diff = value % 36;

        if( diff > 9 )
           result.append((char)(diff+55));
        else
           result.append((char)(diff+48));

        value = value / 36;
     }
     result.reverse();

     result.append("000000000000000");
     return result.substring(0, length).toString();
  }
}


