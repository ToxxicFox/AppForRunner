package ru.sfedu.AppForRunner.models;


import com.opencsv.bean.CsvBindByName;

public class Challenge {
    @CsvBindByName(required = true)
    private Long chId;

    @CsvBindByName(required = true)
    private String challName;

    @CsvBindByName(required = true)
    private String startDate;

    @CsvBindByName(required = true)
    private String finishDate;

    @CsvBindByName(required = true)
    private TrainingTarget trnTarget;

    @CsvBindByName(required = true)
    private Long challDistance;

    @CsvBindByName(required = true)
    private Long challTime;

    @CsvBindByName(required = true)
    private TypeOfChallenge typeOfChallenge;


    public Challenge() {};

    public Long getId() {
        return chId;
    }

    public void setId(Long chId) {
        this.chId = chId;
    }

    public String getChallName() {
        return challName;
    }


    public void setChallName(String challName) {
        this.challName = challName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public TrainingTarget getTrnTarget() {
        return trnTarget;
    }

    public void setTrnTarget(TrainingTarget trnTarget) {
        this.trnTarget = trnTarget;
    }

    public Long getChallDistance() {
        return challDistance;
    }

    public void setChallDistance(Long challDistance) {
        this.challDistance = challDistance;
    }

    public Long getChallTime() {
        return challTime;
    }

    public void setChallTime(Long challTime) {
        this.challTime = challTime;
    }

    public TypeOfChallenge getTypeOfChallenge() {
        return typeOfChallenge;
    }

    public void setTypeOfChallenge(TypeOfChallenge typeOfChallenge) {
        this.typeOfChallenge = typeOfChallenge;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "chId=" + chId +
                ", challName=" + challName +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", trnTarget=" + trnTarget +
                ", challDistance=" + challDistance +
                ", challTime=" + challTime +
                ", typeOfChallenge=" + typeOfChallenge +
                '}';
    }


}
