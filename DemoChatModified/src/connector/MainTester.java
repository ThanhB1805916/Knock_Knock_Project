package connector;

import java.util.LinkedList;
import java.util.Queue;

public class MainTester {
	 static int i =0;
//	 static boolean flag = false;
	 static boolean isDo = false;
	 
	static Queue<Integer> number = new LinkedList<Integer>();
	public static void main(String[] args) {
		
		Thread thread1 = new Thread(()->{
			while(i<50){
				number.add(i);
				System.out.println("Add:"+i);
				i++;
//				if(!flag) flag=true;
				weed();
				if(i==20)
					try {
						Thread.sleep(15000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		thread1.start();
	}
	
	public static void wakeup(){
		while(number.peek()!=null){
			System.out.println("Process:"+ number.poll());
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isDo=false;
//		flag = false;
		System.out.println("Do comple");
	}
	public static void weed(){
		Thread thread2 = new Thread(()->{
			if(!isDo) {
				isDo=true;
				wakeup();
				
			}
	});
		thread2.start();
	}
}
