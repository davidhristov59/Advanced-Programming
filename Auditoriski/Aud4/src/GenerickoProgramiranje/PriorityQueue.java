package GenerickoProgramiranje;

import java.util.List;

//elementite so najgolema vrednost imaat najgolem prioritet
public class PriorityQueue<T> {

    private List<T> elements;
    private List<Integer> priorities;

    public PriorityQueue() {
        this.elements = elements;
        this.priorities = priorities;
    }

    public void add(T element, int priority){

        int position=0;
        for (int i = 0; i < elements.size(); i++) {
            if(priorities.get(i) < priority){ //za sekoj element gledaj dali prioritetot na toj element e pomal od prioritetot nas
                break;
            }
        }

        elements.add(position,element); //na i-ta pozicija dodadi go elementot
        priorities.add(position,priority);
    }

    //KAJ STO VIKA NULL, MOZEME DA NAPRAVIME DA FRLA ISKLUCOK

    public T remove() throws EmptyPriorityQueueException {

        if(elements.isEmpty()){
            throw new EmptyPriorityQueueException();
        }

        return elements.remove(0);
    }

    public static void main(String[] args) {

        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("A", 10);
        priorityQueue.add("F", 100);
        priorityQueue.add("P", 1);
        priorityQueue.add("Z", 1000);
        priorityQueue.add("X", 5);

        try {
            priorityQueue.remove(); //so try/catch za metod
        } catch (EmptyPriorityQueueException e) {
            System.out.println(e.getMessage());
        }

    }

}
