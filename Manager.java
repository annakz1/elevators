import java.util.LinkedList;

public class Manager {
	private LinkedList<Integer[]> missions = new LinkedList<Integer[]>();

	public Manager(){
		Elevator elevator1 = new Elevator("1", this);
		Thread t1 = new Thread(elevator1);
		t1.start();

		Elevator elevator2 = new Elevator("2", this);
		Thread t2 = new Thread(elevator2);
		t2.start();
	}

	public void getElevator(int from, int to) {
		Integer[] mission = {from, to};
		
		synchronized(missions) {
			missions.add(mission);
			missions.notify();
		}
	}

	public LinkedList<Integer> getMission(LinkedList<Integer> path) throws InterruptedException {
		synchronized(missions) {
			while(missions.isEmpty()){
				// if there are other floors to visit, visit and check later
				if(!path.isEmpty()) return path;
				missions.wait();
			}
			Integer[] mission =  missions.poll();
			updatePath(path, mission);
			return path;
		}
	}

	private void updatePath(LinkedList<Integer> path, Integer[] mission) {
		int from = mission[0];
		int fromIndex = 0;
		while(fromIndex < path.size() && from > path.get(fromIndex)){
			fromIndex ++;
		}
		if (fromIndex != 0){
			path.add(fromIndex, from);
		}else{
			path.add(from);
		}

		int to = mission[1];
		int toIndex = fromIndex;
		while(toIndex < path.size() && to > path.get(toIndex)){
			toIndex ++;
		}
		if (toIndex != fromIndex){
			path.add(toIndex, to);
		}else{
			path.add(to);
		}
	}

}
