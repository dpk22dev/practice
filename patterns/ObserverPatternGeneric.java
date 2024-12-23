package patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*
generic program not correcly implemented
 */
public class ObserverPatternGeneric {

    static interface Observer<T extends ObservableData> {
        void onData(T data);
    }

    static abstract class AbstractObserver<T extends ObservableData> implements Observer<T> {
        @Override
        public void onData(T data) {
            System.out.println("Abstract observer is getting data");
        }
    }

    static abstract class ObservableData {
        abstract String getData();
    }


    static class ScoreData extends ObservableData {
        public int value;

        public ScoreData(int value) {
            this.value = value;
        }

        @Override
        String getData() {
            return String.valueOf(value);
        }
    }

    static interface Observable<T extends Observer> {
        boolean subscribe(T observer);

        boolean unsubscribe(T observer);

        void publishData(ObservableData observableData);
    }

    static abstract class AbstractObservable<T extends Observer> implements Observable<T> {
        List<T> observableList = new ArrayList<>();

        @Override
        public boolean subscribe(T observer) {
            observableList.add(observer);
            return true;
        }

        @Override
        public boolean unsubscribe(T observer) {
            observableList.remove(observer);
            return true;
        }

        @Override
        public void publishData(ObservableData observableData) {
            for (T observer : observableList) {
                observer.onData(observableData);
            }
        }
    }

    static class ScoreGenerator<K> extends AbstractObservable<Observer> {
        public void publishScore() {
            int score = ThreadLocalRandom.current().nextInt(1, 7);
            ScoreData scoreData = new ScoreData(score);
            System.out.println("Score generator scored " + score);
            publishData(scoreData);
        }
    }

    static class ScoreBoardObserver<P> extends AbstractObserver<ObservableData> {

        @Override
        public void onData(ObservableData data) {
            System.out.println("ScoreBoard observed " + data.getData());
        }
    }

    static class TweetObserver<P> extends AbstractObserver<ObservableData> {

        @Override
        public void onData(ObservableData data) {
            System.out.println("tweeting score " + data.getData());
        }
    }

    public static void main(String[] args) {
        ObserverPattern observerPattern = new ObserverPattern();
        ScoreGenerator<Observer> scoreGenerator = new ScoreGenerator<>();
        ScoreBoardObserver<ObservableData> scoreBoardObserver = new ScoreBoardObserver<>();
        TweetObserver<ObservableData> tweetObserver = new TweetObserver<>();

        scoreGenerator.subscribe(scoreBoardObserver);
        scoreGenerator.subscribe(tweetObserver);

        scoreGenerator.publishScore();
        scoreGenerator.publishScore();

        scoreGenerator.unsubscribe(scoreBoardObserver);
        scoreGenerator.publishScore();
        scoreGenerator.publishScore();

    }
}
