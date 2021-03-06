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
 *    Written by Jesper Smith with assistance from IHMC team members
 */
package us.ihmc.util;

public class RealtimeTools
{
   /**
    * Algorithm from http://graphics.stanford.edu/~seander/bithacks.html#RoundUpPowerOf2
    * Designed for unsigned integers, this algorithm will work for signed values < 1073741825.
    */
   public static int nextPowerOfTwo(int v)
   {
      v--;
      v |= v >> 1;
      v |= v >> 2;
      v |= v >> 4;
      v |= v >> 8;
      v |= v >> 16;
      return ++v;
   }

   public static int nextDivisibleByEight(int v)
   {
      return (v / 8 + 1) * 8;
   }

   public static int nextDivisibleBySixteen(int v)
   {
      return (v / 16 + 1) * 16;
   }
}
