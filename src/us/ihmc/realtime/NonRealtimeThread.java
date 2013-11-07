package us.ihmc.realtime;

import us.ihmc.util.ThreadInterface;

public class NonRealtimeThread extends Thread implements ThreadInterface
{
   public NonRealtimeThread(Runnable runnable, String name)
   {
      super(runnable, name);
   }

   public void getNextTriggerTime(MonotonicTime nextTriggerTime)
   {
      throw new RuntimeException("Not available for non-realtime threads");
      
   }
}
