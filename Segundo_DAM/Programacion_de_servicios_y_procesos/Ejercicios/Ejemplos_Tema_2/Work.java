package ejemplos;


public class Work implements Runnable{
	private static int cont=0;
	private int numTask;
	
	public Work () {
		cont++;
		numTask=cont;
	}
	
	public void run() {
	      System.out.println("Tarea "+numTask);
	      try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
	   }
}
