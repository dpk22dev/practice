package progs;

import java.util.TreeSet;

class Driver {

    private static class Candidate {
        public String name;
        public Integer vote;

        public Candidate(String name, Integer vote) {
            this.name = name;
            this.vote = vote;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("equals called");
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Candidate candidate = (Candidate) obj;
            return name.equals(candidate.name);
        }

        @Override
        public int hashCode() {
            System.out.println("hashcode called");
            return name.hashCode();
        }

    }

    public static void main(String[] args) {

        TreeSet<Candidate> set = new TreeSet<>((c1, c2) -> {
            System.out.println("comparator called");
            return c2.vote.compareTo(c1.vote);
        });

        set.add(new Candidate("a", 10));
        set.add(new Candidate("b", 30));
        set.add(new Candidate("c", 20));
        set.add(new Candidate("a", 40));
        set.add(new Candidate("b", 10));
        set.add(new Candidate("a", 20));

        set.stream().forEach(candidate -> {
            System.out.println(candidate.name + ":" + candidate.vote + ", ");
        });

        TreeSet<String> set1 = new TreeSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("a");
        set1.add("c");
        set1.add("b");
        set1.stream().forEach(System.out::println);

    }


}
