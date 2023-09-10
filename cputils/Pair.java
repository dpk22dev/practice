class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return first * 31 + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        return first == other.first && second == other.second;
    }
}