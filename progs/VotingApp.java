package progs;

import java.util.*;

public class VotingApp {

    private static class Candidate implements Comparable<Candidate> {
        private final Integer id;
        private final String name;
        private final Integer age;
        private Integer firstVoteTimestamp;

        public Candidate(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public void setFirstVoteTimestamp(Integer firstVoteTimestamp) {
            this.firstVoteTimestamp = firstVoteTimestamp;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Integer getFirstVoteTimestamp() {
            return firstVoteTimestamp;
        }

        /*
                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    Candidate candidate = (Candidate) o;
                    return Objects.equals(id, candidate.id);
                }

                @Override
                public int hashCode() {
                    return Objects.hash(id);
                }
        */
        @Override
        public int compareTo(Candidate other) {
            return this.id.compareTo(other.getId());
        }

    }

    private Map<Integer, Integer> candidateVoteCountMap = new HashMap<>();
    private TreeMap<Integer, TreeSet<Candidate>> countCandidateMap = new TreeMap<>();
    private static int voteInx = 0;

    public void processVote(Candidate candidate) {

        Comparator<Candidate> highAgeWinnerStrategy = (c1, c2) -> {
            return c2.getAge().compareTo(c1.getAge());
        };

        Comparator<Candidate> minFirstVoteInxWinnerStrategy = Comparator.comparing(Candidate::getFirstVoteTimestamp);
        Comparator<Candidate> maxFirstVoteInxWinnerStrategy = (c1, c2) -> {
            return c2.getFirstVoteTimestamp().compareTo(c1.getFirstVoteTimestamp());
        };
        Comparator<Candidate> maxAgeMaxFirstVoteInxWinnerStrategy = (c1, c2) -> {
            if (!c1.getAge().equals(c2.getAge())) {
                return c2.getAge().compareTo(c1.getAge());
            } else {
                return c2.getFirstVoteTimestamp().compareTo(c1.getFirstVoteTimestamp());
            }
        };


        if (candidate.getFirstVoteTimestamp() == null) {
            candidate.setFirstVoteTimestamp(voteInx++);
        }
        int candidateId = candidate.getId();
        candidateVoteCountMap.computeIfAbsent(candidateId, (oldCandidateId) -> 0);
        int oldVoteCount = candidateVoteCountMap.getOrDefault(candidateId, 0);
        candidateVoteCountMap.computeIfPresent(candidateId, (oldCandidateId, prevVoteCount) -> prevVoteCount + 1);

        countCandidateMap.computeIfPresent(oldVoteCount, (oldCount, oldCandidateSet) -> {
            oldCandidateSet.remove(candidate);
            return oldCandidateSet;
        });
        if (countCandidateMap.get(oldVoteCount) != null && countCandidateMap.get(oldVoteCount).isEmpty()) {
            countCandidateMap.remove(oldVoteCount);
        }
        int newVoteCount = candidateVoteCountMap.getOrDefault(candidateId, 0);
        countCandidateMap.computeIfAbsent(newVoteCount, (k) -> {
            return new TreeSet<>(maxAgeMaxFirstVoteInxWinnerStrategy);
        });
        countCandidateMap.computeIfPresent(newVoteCount, (k, set) -> {
            set.add(candidate);
            return set;
        });
    }

    public Candidate getWinner() {
        Candidate result = null;
        if (countCandidateMap.lastEntry() != null) {
            result = countCandidateMap.lastEntry().getValue().first();
        }
        return result;
    }


    public static void main(String[] args) {
        Candidate c1 = new Candidate(1, "c1", 40);
        Candidate c2 = new Candidate(2, "c2", 20);
        Candidate c3 = new Candidate(3, "c3", 30);
        Candidate c4 = new Candidate(4, "c4", 40);

        VotingApp votingApp = new VotingApp();

        votingApp.processVote(c1);
        votingApp.processVote(c2);
        votingApp.processVote(c3);
        votingApp.processVote(c1);
        votingApp.processVote(c4);
        votingApp.processVote(c2);
        votingApp.processVote(c1);
        votingApp.processVote(c3);
        votingApp.processVote(c4);
        votingApp.processVote(c4);

        Candidate winner = votingApp.getWinner();
        System.out.println(winner.getName());

    }

}
