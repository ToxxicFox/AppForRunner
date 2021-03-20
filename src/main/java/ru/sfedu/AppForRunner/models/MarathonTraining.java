package ru.sfedu.AppForRunner.models;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import ru.sfedu.AppForRunner.utils.csvConvs.ChallengeConv;
import ru.sfedu.AppForRunner.utils.csvConvs.RunnerConv;

public class MarathonTraining {
    @CsvBindByName(required = true)
    private Long trnId;

    @CsvCustomBindByName(required = false, converter = RunnerConv.class)
    private Runner runner;

    @CsvBindByName(required = true)
    private long distance;

    @CsvBindByName(required = true)
    private String totalTime;

    @CsvBindByName(required = true)
    private String date;

    @CsvCustomBindByName(required = false, converter = ChallengeConv.class)
    private Challenge challenge;

    @CsvBindByName(required = true)
    private TypeOfTraining typeOfTraining;

    @CsvBindByName(required = true)
    private String avgPace;

    @CsvBindByName(required = true)
    private RouteType typeOfRoute;

    @CsvBindByName(required = true)
    private Long bestTime;

    @CsvBindByName(required = true)
    private Long bestDistance;


    public MarathonTraining() {};


    public String getAvgPace() {
        return avgPace;
    }

    public void setAvgPace(String avgPace) {
        this.avgPace = avgPace;
    }

    public RouteType getTypeOfRoute() {
        return typeOfRoute;
    }

    public void setTypeOfRoute(RouteType typeOfRoute) {
        this.typeOfRoute = typeOfRoute;
    }

    public Long getBestTime() {
        return bestTime;
    }

    public void setBestTime(Long bestTime) {
        this.bestTime = bestTime;
    }

    public Long getBestDistance() {
        return bestDistance;
    }

    public void setBestDistance(Long bestDistance) {
        this.bestDistance = bestDistance;
    }



    public Long getId() {
        return trnId;
    }

    public void setId(Long trnId) {
        this.trnId = trnId;
    }


    public Runner getRunner() {
        return runner;
    }


    public void setRunner(Runner runner) {
        this.runner = runner;
    }


    public Long getDistance() {
        return distance;
    }


    public void setDistance(Long distance) {
        this.distance = distance;
    }


    public String getTotalTime() {
        return totalTime;
    }


    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public Challenge getChallenge() {
        return challenge;
    }


    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }


    public void setTypeOfTraining(TypeOfTraining typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }


    public TypeOfTraining getTypeOfTraining() {
        return typeOfTraining;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trnId=" + trnId +
                ", runner=" + runner +
                ", distance=" + distance +
                ", totalTime=" + totalTime +
                ", date=" + date +
                ", challenge=" + challenge +
                ", typeOfTraining=" + typeOfTraining +
                ", avgPace=" + avgPace +
                ", typeOfRoute=" + typeOfRoute +
                ", bestTime=" + bestTime +
                ", bestDistance=" + bestDistance +
                '}';
    }
}
