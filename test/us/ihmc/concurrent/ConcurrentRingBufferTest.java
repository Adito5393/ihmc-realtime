/*
 *   Copyright 2014 Florida Institute for Human and Machine Cognition (IHMC)
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *    
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Written by Alex Lesman with assistance from IHMC team members
 */
package us.ihmc.concurrent;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class ConcurrentRingBufferTest
{
   @Test
   public void test()
   {
      final long iterations = 100000000L;
      final long writesPerIteration = 1L;
      final long seed = 89126450L;
      
      final ConcurrentRingBuffer<MutableLong> concurrentRingBuffer = new ConcurrentRingBuffer<MutableLong>(new MutableLongBuilder(), 1024);
            
      // Producer
      new Thread(new Runnable()
      {
         public void run()
         {
            Random random = new Random(seed);
            for(long value = 0; value < iterations; value++)
            {
               for(int y = 0; y < writesPerIteration; y++)
               {
                  MutableLong nextValue;
                  while((nextValue = concurrentRingBuffer.next()) == null);  // Spinlock
                  nextValue.value = random.nextLong();
               }
               concurrentRingBuffer.commit();
            }
            
         }
      }).start();
      
      
     
      boolean running = true; 
      long iteration = 0;
      Random random = new Random(seed);
      while(running)
      {
         if(concurrentRingBuffer.poll())
         {
            MutableLong value;
            while((value = concurrentRingBuffer.read()) != null)
            {
               assertTrue(random.nextLong() == value.value);
               
               ++iteration;
               if(iteration >= (iterations-1) * writesPerIteration)
               {
                  running = false;
                  break;
               }
            }
            concurrentRingBuffer.flush();
         }
      }
   }

   private class MutableLong
   {
      public long value;

   }

   public class MutableLongBuilder implements Builder<MutableLong>
   {
      
      public MutableLong newInstance()
      {
         return new MutableLong();
      }
   }
}
