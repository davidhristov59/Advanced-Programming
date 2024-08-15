package GenerickoProgramiranje.Kutija;

public class TestBox {
    public static void main(String[] args) {
        Box<Circle> circleBox = new Box(); //mora da imame klasa sto ke implementira Drawable zatoa Circle ja stavame
        circleBox.add(new Circle());
        circleBox.add(new Circle());
        circleBox.add(new Circle());
        circleBox.add(new Circle());
        circleBox.drawItemsInBox();

        Box<Rectangle> rectangleBox = new Box<>();
        rectangleBox.add(new Rectangle());
        rectangleBox.add(new Rectangle());
        rectangleBox.add(new Rectangle());
        rectangleBox.add(new Rectangle());
        rectangleBox.drawItemsInBox();

    }
}
