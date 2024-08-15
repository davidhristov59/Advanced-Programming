package GenerickoProgramiranje.Kutija;

public class Rectangle implements Drawable{

    private long id;
    private static long ID=1L;

    public Rectangle(){
        id = ID++;
    }

    @Override
    public void draw() {
        System.out.println("This is a rectangle drawing " + id);
    }
}
