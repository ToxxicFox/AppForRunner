package ru.sfedu.AppForRunner.utils;

import ru.sfedu.AppForRunner.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelsSeeder {

    public static List<Runner> seedRunner (int num) {
        List<Runner> runnerList = new ArrayList<>();
        for (int i = 0; i<num; i++) {
            Runner runner = new Runner();
            runner.setId(genLong());
            runner.setFirstName(genStr());
            runner.setLastName(genStr());
            runner.setAge(genLong());
            runner.setWeight(genLong());
            runner.setHeight(genLong());
            runnerList.add(runner);
        }
        return runnerList;
    }

    public static List<Training> seedTraining (int num) {
        List<Training> trainingList = new ArrayList<>();
        for (int i = 0; i<num; i++) {
            Training training = new Training();
            training.setId(genLong());
            Runner runner = seedRunner(1).get(0);
            training.setRunner(runner);
            training.setDistance(genLong());
            training.setTotalTime(genStr());
            training.setDate(genStr());
            Challenge challenge = seedChallenge(1).get(0);
            training.setChallenge(challenge);
            training.setTypeOfTraining(TypeOfTraining.DEFAULT);
            trainingList.add(training);
        }
        return trainingList;
    }


    public static List<MarathonTraining> seedMarathonTrn (int num) {
        List<MarathonTraining> marathonTrainingList = new ArrayList<>();
        for (int i = 0; i<num; i++) {
            MarathonTraining marathonTraining = new MarathonTraining();
            marathonTraining.setId(genLong());
            Runner runner = seedRunner(1).get(0);
            marathonTraining.setRunner(runner);
            marathonTraining.setDistance(genLong());
            marathonTraining.setTotalTime(genStr());
            marathonTraining.setDate(genStr());
            Challenge challenge = seedChallenge(1).get(0);
            marathonTraining.setChallenge(challenge);
            marathonTraining.setTypeOfTraining(TypeOfTraining.MARATHON_TRN);
            marathonTraining.setAvgPace(genStr());
            marathonTraining.setTypeOfRoute(RouteType.TRAILS);
            marathonTraining.setBestTime(genLong());
            marathonTraining.setBestDistance(genLong());
            marathonTrainingList.add(marathonTraining);
        }
        return marathonTrainingList;
    }


    public static List<SprinterTraining> seedSprinterTrn(int num) {
        List<SprinterTraining> sprinterTrainingList = new ArrayList<>();
        for (int i = 0; i<num; i++) {
            SprinterTraining sprinterTraining = new SprinterTraining();
            sprinterTraining.setId(genLong());
            Runner runner = seedRunner(1).get(0);
            sprinterTraining.setRunner(runner);
            sprinterTraining.setDistance(genLong());
            sprinterTraining.setTotalTime(genStr());
            sprinterTraining.setDate(genStr());
            Challenge challenge = seedChallenge(1).get(0);
            sprinterTraining.setChallenge(challenge);
            sprinterTraining.setTypeOfTraining(TypeOfTraining.SPRINT);
            sprinterTraining.setAvgLap(genStr());
            sprinterTraining.setNumSegments(genLong());
            sprinterTraining.setTotalDistance(genLong());
            sprinterTraining.setBestSigment(genLong());
            sprinterTrainingList.add(sprinterTraining);
        }
        return sprinterTrainingList;
    }


    public static List<Challenge> seedChallenge(int num) {
        List<Challenge> challengeList = new ArrayList<>();
        for (int i = 0; i<num; i++) {
            Challenge challenge = new Challenge();
            challenge.setId(genLong());
            challenge.setChallName(genStr());
            challenge.setStartDate(genStr());
            challenge.setFinishDate(genStr());
            challenge.setTrnTarget(TrainingTarget.SPEED);
            challenge.setChallDistance(genLong());
            challenge.setChallTime(genLong());
            challenge.setTypeOfChallenge(TypeOfChallenge.SPRINT);
            challengeList.add(challenge);
        }
        return challengeList;
    }


    private static Long genLong(){
        return Long.valueOf(genInt());
    }


    private static int genInt() {
        return new Random().nextInt(500);
    }


    private static String genStr() {
        Random random = new Random();
        char[] letters = new char[random.nextInt(9) + 5];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) ('a' + random.nextInt(26));
        }
        return new String(letters);
    }


}
