import java.util.LinkedList;

public class Elevator implements Runnable {
	private LinkedList<Integer> path;
	private String name;
	private Manager manager;

	public Elevator(String name, Manager manager){
		this.name = name;
		this.manager = manager;
		this.path = new LinkedList<>();
	}

	@Override
	public void run() {
		while(true){
			try {
				path = manager.getMission(path);
				System.out.println(name + " curr path:::" + path);
				Integer currFloor = path.poll();
				System.out.println("Elevator " + name + ", Stopping at floor: " + currFloor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
