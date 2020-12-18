/*    */ package connector;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.Queue;
/*    */ 
/*    */ public class MainTester {
/*  7 */   static int i = 0;
/*    */   
/*    */   static boolean isDo = false;
/*    */   
/* 11 */   static Queue<Integer> number = new LinkedList<>();
/*    */   
/*    */   public static void main(String[] args) {
/* 14 */     Thread thread1 = new Thread(() -> {
/*    */           while (i < 50) {
/*    */             number.add(Integer.valueOf(i));
/*    */             
/*    */             System.out.println("Add:" + i);
/*    */             i++;
/*    */             weed();
/*    */             if (i == 20)
/*    */               try {
/*    */                 Thread.sleep(15000L);
/* 24 */               } catch (Exception e) {
/*    */                 e.printStackTrace();
/*    */               }  
/*    */             try {
/*    */               Thread.sleep(1000L);
/* 29 */             } catch (Exception e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */           } 
/*    */         });
/*    */     
/* 35 */     thread1.start();
/*    */   }
/*    */   
/*    */   public static void wakeup() {
/* 39 */     while (number.peek() != null) {
/* 40 */       System.out.println("Process:" + number.poll());
/*    */       try {
/* 42 */         Thread.sleep(1500L);
/* 43 */       } catch (InterruptedException e) {
/*    */         
/* 45 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 48 */     isDo = false;
/*    */     
/* 50 */     System.out.println("Do comple");
/*    */   }
/*    */   public static void weed() {
/* 53 */     Thread thread2 = new Thread(() -> {
/*    */           if (!isDo) {
/*    */             isDo = true;
/*    */             
/*    */             wakeup();
/*    */           } 
/*    */         });
/* 60 */     thread2.start();
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\connector\MainTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */