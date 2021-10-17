//Hagop Tanashian

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/* Map for Sierpinski Triangle to flip from original to the left part of the next shape
0 - up1 -> right - 4
1 - up2 -> down1 - 2
2 - down1 -> up2 - 1
3 - down2 -> left - 5
4 - right -> up1 - 0
5 - left -> down2 - 3
 */

/* Map for Sierpinski Triangle to flip the left part of the new shape to the right part of the new shape
0 - up1 -> down1 - 2
1 - up2 -> down2 - 3
2 - down1 -> up1 - 0
3 - down2 -> up2 - 1
4 - right -> right - 4
5 - left -> left - 5
*/

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}

public class Sierpinski {

    public static int flipLeft(int x) {
        if(x == 0) {
            return 4;
        }
        else if(x == 1) {
            return 2;
        }
        else if(x == 2) {
            return 1;
        }
        else if(x == 3) {
            return 5;
        }
        else if(x == 4) {
            return 0;
        }
        else if(x == 5) {
            return 3;
        }
        return -1;
    }

    public static int flipSymm(int x) {
        if (x == 0) {
            return 2;
        } else if (x == 1) {
            return 3;
        } else if (x == 2) {
            return 0;
        } else if (x == 3) {
            return 1;
        } else if (x == 4) {
            return 4;
        } else if (x == 5) {
            return 5;
        }
        return -1;
    }

    public static Point trigCalc(Point p1, int line) {
        double pi = Math.PI;
        Point p2 = new Point(0, 0);
        double x, y;
        if(line == 0) {
            x = p1.getX() + Math.cos(60 * (pi/180));
            y = p1.getY() + Math.sin(60 * (pi/180));
            p2.setPoint(x, y);
            return p2;
        }
        else if(line == 1) {
            x = p1.getX() - Math.cos(60 * (pi/180));
            y = p1.getY() + Math.sin(60 * (pi/180));
            p2.setPoint(x, y);
            return p2;
        }
        else if(line == 2) {
            x = p1.getX() + Math.cos(60 * (pi/180));
            y = p1.getY() - Math.sin(60 * (pi/180));
            p2.setPoint(x, y);
            return p2;
        }
        else if(line == 3) {
            x = p1.getX() - Math.cos(60 * (pi/180));
            y = p1.getY() - Math.sin(60 * (pi/180));
            p2.setPoint(x, y);
            return p2;
        }
        else if(line == 4) {
            x = p1.getX() + 1;
            y = p1.getY();
            p2.setPoint(x, y);
            return p2;
        }
        else if(line == 5) {
            x = p1.getX() - 1;
            y = p1.getY();
            p2.setPoint(x, y);
            return p2;
        }
        return p2;
    }

    public static ArrayList<Point> drawFractal(int n) {
        ArrayList<Integer> map = new ArrayList<Integer>((int)(Math.pow(3, n)));
        ArrayList<Point> fractalPoints = new ArrayList<Point>();
        Point p = new Point(0, 0);
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                map.add(0);
                map.add(4);
                map.add(2);
            } 
            else {
                int prevSize = map.size();
                for (int j = 0; j < prevSize; j++) { // draws middle (2/3)
                    map.add(map.get(j));
                }
                for (int k = 0; k < prevSize; k++) { // draws first third (1/3)
                    map.set(k, flipLeft(map.get(k)));
                }
                for (int l = prevSize - 1; l >= 0; l--) { // draws third third (3/3)
                    map.add(flipSymm(map.get(l)));
                }
            }
        }
        for (int q = 0; q <= map.size(); q++) {
            if (q == 0) {
                fractalPoints.add(p);
            }
            else {
                p = trigCalc(p, map.get(q - 1));
                fractalPoints.add(p);
            }
        }
        return fractalPoints;
    }

    public static boolean intersect(Point p1, Point p2, Point p3, Point p4) {
        double orientation1 = crossProduct(p3, p4, p1);
        double orientation2 = crossProduct(p3, p4, p2);
        double orientation3 = crossProduct(p1, p2, p3);
        double orientation4 = crossProduct(p1, p2, p4);
        if(((orientation1 > 0 && orientation2 < 0) || (orientation1 < 0 && orientation2 > 0)) && ((orientation3 > 0 && orientation4 < 0) || (orientation3 < 0 && orientation4 > 0))) {
            return true;
        }
        else if(orientation1 == 0 && touch(p3, p4, p1)) {
            return true;
        }
        else if(orientation2 == 0 && touch(p3, p4, p2)) {
            return true;
        }
        else if(orientation3 == 0 && touch(p1, p2, p3)) {
            return true;
        }
        else if(orientation4 == 0 && touch(p1, p2, p4)) {
            return true;
        }
        return false;
    }

    public static double crossProduct(Point p1, Point p2, Point p3) {
        double a = p3.getX() - p1.getX();
        double b = p2.getY() - p1.getY();
        double c = p2.getX() - p1.getX();
        double d = p3.getY() - p1.getY();
        return a*b - c*d;
    }

    public static boolean touch(Point p1, Point p2, Point p3) {
        double a = Math.min(p1.getX(), p2.getX());
        double b = Math.max(p1.getX(), p2.getX());
        double c = Math.min(p1.getY(), p2.getY());
        double d = Math.max(p1.getY(), p2.getY());
        if((a <= p3.getX() && p3.getX() <= b) && (c <= p3.getY() && p3.getY() <= d)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            File outputFile = new File("output.txt");
            PrintWriter printWriter = new PrintWriter(outputFile);
            Scanner scanner = new Scanner(file);
            ArrayList<Point> input = new ArrayList<Point>();

            int n = scanner.nextInt();
            ArrayList<Point> shape = drawFractal(n);

            while(scanner.hasNextDouble()) {
                input.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
            }

            for(int i = 0; i < input.size() - 1; i = i + 2) {
                boolean value = false;
                for(int j = 0; j < shape.size() - 1; j++) {
                    value = intersect(shape.get(j), shape.get(j + 1), input.get(i), input.get(i + 1));
                    if(value == true) {
                        printWriter.println(1);
                        break;
                    }
                }
                if(value == false) {
                    printWriter.println(0);
                }
            }

            scanner.close();
            printWriter.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("input.txt file is not found!");
            e.printStackTrace();
        }
    }
}
