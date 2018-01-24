import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Random;

public class SingleSourceShortestPath {
    public static void main(String args[]) {
        /*StopWatch s = new StopWatch();
        ArrayList<Float> list = new ArrayList<Float>();
        for (int i = 100; i <= 1000; i = i + 20) {
            for (int k = 0; k < 5; k++) {
                for (int j = 10; j <= i; j = j + 10) {
                    DirectedGraph G1 = DirectedGraph.RandomGraph(i, j, false);
                //    DirectedGraph G2 = DirectedGraph.RandomGraph(i, j, false);
                    Dijkstra D = new Dijkstra();
                    BellmanFord B = new BellmanFord();
                    s.start();
                    D.Execute(G1, G1.getV().get(0));
                    float t1 = (float) s.stop() / 1000000;
                    s.start();
                    B.Execute(G1, G1.getV().get(0));
                    float t2 = (float) s.stop() / 1000000;
                    if (t1 <= t2) {
                        float r = (float) i / (float) j;
                        System.out.println(r);
                        list.add(r);
                        break;
                    }
                }
            }
        }

        System.out.println("No. of Vertice,1,2,3,4,5");
        for (int i = 0; i < list.size(); i = i + 5) {
            System.out.print(list.get(i) + ",");
            System.out.print(list.get(i+1) + ",");
            System.out.print(list.get(i+2) + ",");
            System.out.print(list.get(i+3) + ",");
            System.out.println(list.get(i+4));
        }
*/


        StopWatch s = new StopWatch();
        System.out.println("No. of Vertices,No.of Edges,T_D,T_B,T_D<T_B,Selection,T_S,T_S<(T_D+T_B)/2");
       for (int i = 100; i <= 1000; i = i + 100) {
           int a = 32*i/100;
           for (int j = 1; j <= 2; j++) {
               DirectedGraph G1 = DirectedGraph.RandomGraph(i, j * a / 3, false);
               DirectedGraph G2 = DirectedGraph.RandomGraph(i, j * a / 3, false);
               DirectedGraph G3 = DirectedGraph.RandomGraph(i, j * a / 3, false);
               Dijkstra D = new Dijkstra();
               BellmanFord B = new BellmanFord();
               float r = (float) j * a / 3 / (float) i;

               s.start();
               D.Execute(G1, G1.getV().get(0));
               float t1 = (float) s.stop() / 1000000;
               s.start();
               B.Execute(G2, G2.getV().get(0));
               float t2 = (float) s.stop() / 1000000;
               String str;
               s.start();
               selAlgorithm(G3, G3.getV().get(0));
               float t3 = (float) s.stop() / 1000000;

               boolean b1 = (t1<t2);
               boolean b2 = (t3<(t1+t2)/2);

               System.out.println(i + "," + j * a / 3 + "," + t1 + "," + t2 + "," + b1 + "," + "B" + "," + t3 + "," + b2);
           }
           for (int j = 0; j<=2; j++) {
               int k = a + (j*(i*(i-1)-a)/3);
               DirectedGraph G1 = DirectedGraph.RandomGraph(i, k, false);
               DirectedGraph G2 = DirectedGraph.RandomGraph(i, k, false);
               DirectedGraph G3 = DirectedGraph.RandomGraph(i, k, false);

               Dijkstra D = new Dijkstra();
               BellmanFord B = new BellmanFord();
               float r = (float) j * a / 3 / (float) i;

               s.start();
               D.Execute(G1, G1.getV().get(0));
               float t1 = (float) s.stop() / 1000000;
               s.start();
               B.Execute(G2, G2.getV().get(0));
               float t2 = (float) s.stop() / 1000000;
               String str;
               s.start();
               selAlgorithm(G3, G3.getV().get(0));
               float t3 = (float) s.stop() / 1000000;

               boolean b1 = (t1<t2);
               boolean b2 = (t3<(t1+t2)/2);

               System.out.println(i + "," + k + "," + t1 + "," + t2 + "," + b1 + "," + "D" + "," + t3 + "," + b2);
           }
       }

    }


    public static void selAlgorithm (DirectedGraph G, Vertex s) {
        Dijkstra D = new Dijkstra();
        BellmanFord B = new BellmanFord();
        /*for (DirectedEdge e : G.getE()) {
            if (e.getWeight() < 0) {
                B.Execute(G, s);
                return "B";
                //return true; //Negative edge: choose Bellman Ford
            }
        }*/
        if (32*G.getV().size()/100 > G.getE().size()) {
            B.Execute(G, s);
        //    return "B";
        }
        else {
            D.Execute(G, s);
        //    return  "D";
        }
            //|V| > |E|, choose Bellman Ford; |V| <= |E|, choose Dijkstra.
    }
}
