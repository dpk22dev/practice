package javalang.lang;

import java.util.*;

/*
although after implementing hashcode, equals Candidate class should replace previous element in
TreeSet but it's not working
checking equality using partial fields in TreeSet is not possible

treeset doesn't use hashcode, equals. it uses comparator for comparing elements in BST
dont't try to mingle with two things eg name, vote here for uniqueness and ordering criteria
elements are sorted and unique based on single thing.
https://stackoverflow.com/questions/4447461/in-treeset-sorting-uniqueness-of-custom-objects-based-on-different-properties


It is imp to note that HashSet/Map uses hashcode, equals to unique elements
TreeMap/Set uses comparator to sort, unique elements. You can't use different attr to sort and unique
Eg TreeSet<Candidate> to have unique name of candidates and sort by vote count

Better to have:
HM<Name, Count> for vote count of each candidate
TM<Integer, Set<String>> for dec sorted list of candidates at each vote count



 */

class SetProblem {

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
        // PriorityQueue<Candidate> pq = new PriorityQueue<>();
        TreeSet<Candidate> set = new TreeSet<>((c1, c2) -> {
            return c2.vote.compareTo(c1.vote);
        });

        set.add(new Candidate("a", 10));
        set.add(new Candidate("b", 30));
        set.add(new Candidate("c", 20));
        set.add(new Candidate("a", 40));
        // but even with hashcode, equals which depends on name only; "a" won't be overwritten
        //but you can remove a,10 before inserting a,40, problem is you won't know vote count for a


        set.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });

        System.out.println("======== 1.5 =====");

        // you want deduplication during addition using equals on name or removal on name
        // but want to keep sorted based on votes count both can't be achieved
        // below can't remove a with dummy vote count value;
        set.remove(new Candidate("a", 10000));
        set.remove(new Candidate("a", 40));

        set.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });


        System.out.println("======== 2 =====");

        // hacking impl can be done as follow
        // only below way seems to be working correctly
        Map<String, Candidate> candMap = new HashMap<>();
        addToMap(candMap, new Candidate("a", 10));
        addToMap(candMap, new Candidate("b", 30));
        addToMap(candMap, new Candidate("c", 20));
        addToMap(candMap, new Candidate("a", 40));

        candMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue().vote + ","));

        // below logic of removing element from set doesn't work
        System.out.println("======== 3 =====");
        TreeSet<Candidate> set1 = new TreeSet<>((c1, c2) -> {
            return c2.vote.compareTo(c1.vote);
        });
        // you can't remove old element because hashcode, equals are not being used
        // remove method want exact params in set
        addToSet(set1, new Candidate("a", 10));
        addToSet(set1, new Candidate("b", 30));
        addToSet(set1, new Candidate("c", 20));
        addToSet(set1, new Candidate("a", 40));

        System.out.println(set1.size());
        set1.stream().forEach(cand -> {
            System.out.println(cand.name + ":" + cand.vote + ", ");
        });


    }

    private static void addToSet(TreeSet<Candidate> set, Candidate candidate) {
        if (set.contains(candidate)) {
            set.remove(candidate);
        }
        set.add(candidate);
    }

    private static void addToMap(Map<String, Candidate> candMap, Candidate candidate) {
        candMap.computeIfAbsent(candidate.name, (k) -> new Candidate("a", 10));
        candMap.computeIfPresent(candidate.name, (k, oldCand) -> {
            if (oldCand.vote < candidate.vote) {
                return candidate;
            }
            return oldCand;
        });
    }


}
