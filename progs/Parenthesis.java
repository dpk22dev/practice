package progs;

import java.util.ArrayList;
import java.util.List;

class Parenthesis {

    private List<String> res;
    private int n;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList();
        this.n = n;
        helper(0,0,0,"");
        return res;
    }

    public void helper( int oc, int cc, int pos, String str ){
        if( pos == 2*n ){
            if( oc == cc )
                res.add( str );
            return;
        }
        if( oc < cc ) return;
        if( oc > n || cc > n ) return;

        helper( oc+1, cc, pos+1, str+'(');
        helper( oc, cc+1, pos+1, str+')');
    }

    public static void main(String[] args) {
        Parenthesis obj = new Parenthesis();
        List<String> strings = obj.generateParenthesis(3);
        System.out.printf(strings.toString());
    }

}