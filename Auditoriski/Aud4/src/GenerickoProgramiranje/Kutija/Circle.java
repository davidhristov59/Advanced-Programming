package GenerickoProgramiranje.Kutija;

public class Circle implements Drawable{

    private long id;
    private static long ID=1L;

    public Circle(){
        id = ID++;
    }

    @Override
    public void draw() {
        System.out.println("This is a circle drawing" + id);
    }
}
