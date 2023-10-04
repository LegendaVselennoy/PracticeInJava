package file;

import java.io.*;

public class Square implements Serializable {

    private transient Duck duck=new Duck();
    private int width;
    private int height;

    public Square(int width,int height){
        this.width=width;
        this.height=height;
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public static void main(String[] args) {
        Square mySquare=new Square(50,20);

        try{
            FileOutputStream fs=new FileOutputStream("foo.ser");
            ObjectOutputStream os=new ObjectOutputStream(fs);
            os.writeObject(mySquare);
            os.close();
            ObjectInputStream is=new ObjectInputStream(new FileInputStream("foo.ser"));
            Square two=(Square)is.readObject();
            System.out.println(two);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
