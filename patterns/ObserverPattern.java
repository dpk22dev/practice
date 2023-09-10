package patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ObserverPattern {

    static interface Observer {
        void onData( ObservableData data );
    }

    static abstract class AbstractObserver implements Observer{
        @Override
        public void onData(ObservableData data) {
            System.out.println( "Abstract observer is getting data" );
        }
    }

    static abstract class ObservableData{
        abstract String getData();
    }


    static class ScoreData extends ObservableData{
        public int value;

        public ScoreData(int value) {
            this.value = value;
        }

        @Override
        String getData() {
            return String.valueOf( value );
        }
    }

    static interface Observable{
        boolean subscribe( Observer observer);
        boolean unsubscribe( Observer observer);
        void publishData( ObservableData observableData );
    }

    static abstract class AbstractObservable implements Observable{
        List<Observer> observableList = new ArrayList<>();

        @Override
        public boolean subscribe(Observer observer) {
            observableList.add( observer );
            return true;
        }

        @Override
        public boolean unsubscribe(Observer observer) {
            observableList.remove( observer );
            return true;
        }

        @Override
        public void publishData(ObservableData observableData) {
            for( Observer observer : observableList ){
                observer.onData( observableData );
            }
        }
    }

    static class ScoreGenerator extends AbstractObservable{
        public void publishScore( ){
            int score = ThreadLocalRandom.current().nextInt(1,7);
            ScoreData scoreData = new ScoreData( score );
            System.out.println("Score generator scored " + score );
            publishData( scoreData );
        }
    }

    static class ScoreBoardObserver extends AbstractObserver{

        @Override
        public void onData(ObservableData data) {
            System.out.println("ScoreBoard observed " + data.getData() );
        }
    }

    static class TweetObserver extends AbstractObserver{

        @Override
        public void onData(ObservableData data) {
            System.out.println("tweeting score " + data.getData() );
        }
    }

    public static void main(String[] args) {
        ObserverPattern observerPattern = new ObserverPattern();
        ScoreGenerator scoreGenerator = new ScoreGenerator();
        ScoreBoardObserver scoreBoardObserver = new ScoreBoardObserver();
        TweetObserver tweetObserver = new TweetObserver();

        scoreGenerator.subscribe( scoreBoardObserver );
        scoreGenerator.subscribe( tweetObserver );

        scoreGenerator.publishScore();
        scoreGenerator.publishScore();

        scoreGenerator.unsubscribe( scoreBoardObserver );
        scoreGenerator.publishScore();
        scoreGenerator.publishScore();

    }
}
