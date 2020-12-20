import edu.princeton.cs.algs4.StdDraw; // library to draw

public class Main {

    public static void main(String[] args) {

        StdDraw.setCanvasSize(800,600);
        int N = 1000; // number of birds
        Bird [] birds = new Bird[N];

        // init all the birds
        for(int i = 0; i < N ; i++){
            birds[i] = new Bird(0.01);
            // reference
            birds[i].birds = birds;
        }

        while (true){// Renderer loop
            StdDraw.clear();
            for(int i = 1; i < N ; i++){
                // calculate the new position for this bird then move it
                birds[i].move(0.0001);
                birds[i].draw();
            }
            StdDraw.show(5);
        }

    }
}
