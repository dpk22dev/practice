package javalang.lang;

import java.util.Objects;
import java.util.PriorityQueue;

public class PQProblem {
    private static class Candidate {
        public String name;
        public Integer vote;

        public Candidate(String name, Integer vote) {
            this.name = name;
            this.vote = vote;
        }

        /*
        for checking candidate properly equals, hashcode can be impl below
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Candidate candidate = (Candidate) o;
            return Objects.equals(name, candidate.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {

        System.out.println("======== 1 =====");

        PriorityQueue<Candidate> pq = new PriorityQueue<Candidate>((c1, c2) -> c2.vote.compareTo(c1.vote));
        Candidate ta = new Candidate("a", 10);
        pq.offer(ta);
        pq.offer(new Candidate("b", 30));
        pq.offer(new Candidate("c", 20));
        pq.offer(new Candidate("a", 40));

        // it allows to store duplicates
        pq.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });

        System.out.println("======== 2 =====");
        // remove in PQ iterates over all underlying elements and uses equals method to remove - O(n)
        // then sift operation to adjust heap property - log n
        // problem: instead of removing 10 vote, it removes 40 vote. first found using equals
        pq.remove(ta);
        pq.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });

        System.out.println("======== 2 =====");
        pq.remove(new Candidate("b", 10000));
        pq.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });
        // no meaning of removing by reference. equals method plays role to detect element to remove

        // based on your use case you might want to include votes as well in equality criteria; in that case
        // exact candidate will be removed


        // in dijkstra etc, you can't rely on empty pq. as same elements might have been added multiple
        // times. use visited map to ignore prev visited element if that was repeteted

    }

}
