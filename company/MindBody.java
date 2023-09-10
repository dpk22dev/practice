package company;// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MindBody {

    static class Point{
        public char tag;
        public int x, y;
        Point( char tag, int i, int j){
            this.tag = tag;
            this.x = i;
            this.y = j;
        }

    }

    static class PointSet{
        public double dist;
        public List<Point> points;

        PointSet( double d ){
            this.dist = d;
            this.points = new ArrayList<Point>();
        }

    }

    private boolean hasRepeatedPoints( List<Point> list){
        Set<Character> set = new HashSet<>();
        for( Point p: list ){
            if( set.contains(p.tag) )
                return true;
            set.add( p.tag );
        }
        return false;
    }

    public boolean isListPointPartOfResultSet( List<Point> list, Set<Character> resSet ){
        for( Point p : list ){
            if( resSet.contains( p.tag )){
                return true;
            }
        }
        return false;
    }

    public int solution(String S, int[] X, int[] Y) {
        // Implement your solution here
        Map<Double, List<Point>> distMap = new TreeMap<Double, List<Point>>();
        double dist;
        for( int i = 0; i < S.length(); i++ ){
            int x = X[i]; int y = Y[i];
            dist = Math.sqrt( x*x + y*y );
            Point p = new Point( S.charAt(i), x, y );
            distMap.computeIfAbsent( dist, (k) -> { return new ArrayList<Point>(); } );
            distMap.computeIfPresent( dist, (k,list) -> { list.add(p); return list;} );
        }
        Set<Character> resSet = new HashSet<Character>();
        for( Map.Entry<Double, List<Point>> entry : distMap.entrySet() ){
            List<Point> mapList = entry.getValue();
            if( hasRepeatedPoints( mapList ) ){
                break;
            }
            if( isListPointPartOfResultSet( mapList, resSet) ){
                break;
            }
            for( Point p : mapList ){
                resSet.add( p.tag );
            }
        }
        return resSet.size();
    }

    public static void main(String[] args) {
        //int X[] = new int[]{1, -2, -2};
        MindBody s = new MindBody();
        int res = s.solution("ABB", new int[]{1, -2, -2}, new int[]{1, -2, 2});
        System.out.println( res );
    }

}