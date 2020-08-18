/* 
   PrintTask.java
   PrintTask class sleeps for a random time from 0 to 5 seconds
   PrintTask class implements Runnable, so that multiple 
   PrintTasks can execute concurrently.
   Each thread running a Print-Tasks sleeps for
   the amount of time specified by sleepTime, 
   then outputs its task’s name and
   a message indicating that it’s done sleeping.
*/

import java.security.SecureRandom;

public class PrintTask implements Runnable 
{
   private final int sleepTime; // random sleep time for thread
   private final String taskName; // name of task
   private final static SecureRandom generator = new SecureRandom();
    
   // constructor
   public PrintTask( String name )
   {
      taskName = name; // set task name
        
      // pick random sleep time between 0 and 5 seconds
      sleepTime = generator.nextInt( 5000 ); // milliseconds
   } // end PrintTask constructor
   
   
   // A PrintTask executes when a thread calls the PrintTask’s run method
   // method run contains the code that a thread will execute
   public void run()
   {
      try // put thread to sleep for sleepTime amount of time 
      {
         System.out.printf( "%s going to sleep for %d milliseconds.\n", 
            taskName, sleepTime );
         // place the thread in the timed waiting state for the specified time
         Thread.sleep( sleepTime ); // put thread to sleep
         // At this point, the thread loses the processor, 
         // and the system allows another thread to execute.
      } // end try        
      catch ( InterruptedException exception)
      {
         //System.out.printf( "%s %s\n", taskName,
            //"terminated prematurely due to interruption" );
         exception.printStackTrace();
         // obtains a reference to the currently executing Thread
         // then uses that Thread’s interrupt method to deliver the 
         // InterruptedException to the currentthread
         Thread.currentThread().interrupt(); // re-interrupt the thread 
          
      } // end catch
        
      // print task name
      // the thread awakens, it reenters the runnable state.
      // Now, the PrintTask is assigned to a processor again.
      System.out.printf( "%s done sleeping\n", taskName ); 
   } // end method run
} // end class PrintTask


