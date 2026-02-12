import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Track {
    public static int courseLength = 1000;
    public static AtomicInteger ranking = new AtomicInteger(0);
}

class Biker implements Runnable {
    protected String bikerName;
    protected int currentLocation;
    int rankingPosition = 0;
    Duration duration;

    public Biker(String bikerName) {
        this.bikerName = bikerName;
        this.currentLocation = 0;
    }

    Random rand = new Random();

    @Override
    public void run() {
        Instant start = Instant.now();
        System.out.println(bikerName + " started at " + start);

        while (this.currentLocation < Track.courseLength) {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Something went wrong!");
                Thread.currentThread().interrupt();
            }

            this.currentLocation += 100;
            System.out.println(bikerName + " has covered " + this.currentLocation + "m");
        }

        this.duration = Duration.between(start, Instant.now());
        System.out.println("Biker " + bikerName + " has completed the race!");

        this.rankingPosition = Track.ranking.incrementAndGet();
    }
}

class Race {
    int n;
    Biker racers[];

    public void initializeRacers() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of racers: ");
        n = sc.nextInt();
        sc.nextLine();

        racers = new Biker[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter biker name: ");
            String name = sc.nextLine();
            racers[i] = new Biker(name);
        }

        sc.close();
    }

    public void startRace() throws InterruptedException {
        System.out.println("RACE STARTS!!!");

        ExecutorService executor = Executors.newFixedThreadPool(n);

        for (Biker racer : racers) {
            executor.submit(racer);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    public void displayRanking() {
        System.out.println("\nFinal Rankings:");

        Arrays.sort(racers, Comparator.comparingInt(r -> r.rankingPosition));

        for (Biker racer : racers) {
            System.out.println(
                "Biker " + racer.bikerName +
                " secured position " + racer.rankingPosition +
                " in " + racer.duration.toSeconds() + " seconds"
            );
        }
    }
}

public class BikeRacing {
    public static void main(String args[]) throws InterruptedException {
        Race race = new Race();
        race.initializeRacers();
        race.startRace();
        race.displayRanking();
    }
}
