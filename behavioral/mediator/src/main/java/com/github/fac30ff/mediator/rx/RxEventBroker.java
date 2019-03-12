package com.github.fac30ff.mediator.rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
/*import rx.Observable;
import rx.Observer;*/

import java.util.ArrayList;
import java.util.List;
/*import java.util.Observable;
import java.util.Observer;*/

public class RxEventBroker {
}

class EventBroker extends Observable<Integer> {
    private List<Observer<? super Integer>>
            observers = new ArrayList<>();

    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        observers.add(observer);
    }

    public void publish(int n) {
        for (Observer<? super Integer> o : observers)
            o.onNext(n);
    }
}

class FootballPlayer {
    private int goalsScored = 0;
    private EventBroker broker;
    public String name;

    public FootballPlayer(EventBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    public void score() {
        broker.publish(++goalsScored);
    }
}

class FootballCoach {
    public FootballCoach(EventBroker broker) {
        broker.subscribe(i -> {
            System.out.println("Hey, you scored " + i + " goals!");
        });
    }
}

class RxEventBrokerDemo {
    public static void main(String[] args) {
        EventBroker broker = new EventBroker();
        FootballPlayer player = new FootballPlayer(broker, "jones");
        FootballCoach coach = new FootballCoach(broker);

        player.score();
        player.score();
        player.score();
    }
}
