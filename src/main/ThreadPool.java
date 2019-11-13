/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jai
 */
import java.util.concurrent.*;
import javafx.concurrent.Task;

public class ThreadPool{
	private ExecutorService pool;
	private final String name;
	
        public ThreadPool(String name,int numThreads){
            pool = Executors.newFixedThreadPool(numThreads);
            this.name  = name;
	}

	public void addTask(Runnable obj){
		try{
                    System.out.println("Executed by "+name+"...");
                    pool.execute(obj);
		}catch(Exception e){
                    System.out.println(e.getMessage());
		}
	}
        
        public void submitTask(Task task){
            pool.submit(task);
        }
        
        public void submitTask(Runnable task){
            pool.submit(task);
        }

	public void shutdown(){
            pool.shutdown();
	}
        
        public void removeTask(){
            
        }

	public void shutdownAndAwaitTermination() {
   		pool.shutdown(); // Disable new tasks from being submitted
   		try {
     	// Wait a while for existing tasks to terminate
     		if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
       			pool.shutdownNow(); // Cancel currently executing tasks
       			// Wait a while for tasks to respond to being cancelled
       			if (!pool.awaitTermination(60, TimeUnit.SECONDS))
           			System.err.println("Pool did not terminate");
     		}
   		} catch (InterruptedException ie) {
     		// (Re-)Cancel if current thread also interrupted
     		pool.shutdownNow();
     		// Preserve interrupt status
     		Thread.currentThread().interrupt();
            }
        }

 	public void getStatus(){
 		System.out.println(pool);
 	}	
}