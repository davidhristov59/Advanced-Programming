package GenerickoProgramiranje.Kutija;

import java.util.*;

//public class Box<E extends Drawable> {
//    private List<E> elements;
//
//    public Box(){
//        elements = new ArrayList<>();
//    }
//
//    public void addElement(E element){ //od tip generik
//        elements.add(element);
//    }
//
//    public boolean isEmpty(){
//        return elements.isEmpty();
//    }
//
//
//    public E drawItem() throws EmptyBoxException { //go vraka elementot E kako rezultat
//        if(isEmpty()){
//            throw new EmptyBoxException(); //ako e prazno frli exception
//        }
//
//        //1 nacin
////        Collections.shuffle(elements);
////        return elements.get(0); //go vraka prviot
//
//        //2 nacin
//        Random random = new Random();
//        int position = random.nextInt(elements.size());
//        return elements.get(position);
//
//    }
//
//    public void drawItemsInBox(){ //ne znaeme kakov tip na elementi ke se stai zatoa morame da go ogranicime generikot
//
//        elements.stream().forEach(i -> i.draw());
//
//        //2 opcija
////        for(E element: elements){
////            element.draw();
////        }
//
//    }
//
//}


public class Box<E extends Drawable>{ //E moze da se zameni sekoj tip koj sto go imple,entira drawable interface-ot

    //CIM VIKA SLUCAJNO DA SE IZBERE ZNACI DEKA TREBA GENERICI DA KORISTIME

    private List<E> elements;

    public Box(){

        elements = new ArrayList<>();
    }

    public void add(E element){

         elements.add(element);
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public E drawItem() throws EmptyBoxException{ // go vraka elementot kako rezultat

        if(isEmpty()){
            throw new EmptyBoxException();
        }

        Random random = new Random();
        int position = random.nextInt(elements.size());
//        int position = (int) (Math.random() * elements.size());
        return elements.get(position);
    }

    public void drawItemsInBox(){ //cela

        elements.stream().forEach(i -> i.draw());
    }


}