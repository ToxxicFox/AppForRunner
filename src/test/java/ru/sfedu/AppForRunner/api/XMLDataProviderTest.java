package ru.sfedu.AppForRunner.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.Constants;
import ru.sfedu.AppForRunner.utils.ModelsSeeder;
import ru.sfedu.AppForRunner.utils.Result;
import ru.sfedu.AppForRunner.utils.ResultType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLDataProviderTest {

    public XMLDataProvider cpd = new XMLDataProvider();
    public Logger log = LogManager.getLogger(this.getClass());

    public XMLDataProviderTest() throws IOException {
    }

    @Before
    public void prepareDS() throws CsvDataTypeMismatchException, IllegalAccessException, CsvRequiredFieldEmptyException, IOException {
        List<Runner> runnerList = ModelsSeeder.seedRunner(12);
        cpd.addRecord(runnerList, false, Runner.class);
        List<Training> trainingList = ModelsSeeder.seedTraining(12);
        cpd.addRecord(trainingList, false, Training.class);
        List<MarathonTraining> marathonTrainingList = ModelsSeeder.seedMarathonTrn(12);
        cpd.addRecord(marathonTrainingList, false, MarathonTraining.class);
        List<SprinterTraining> sprinterTrainingList = ModelsSeeder.seedSprinterTrn(12);
        cpd.addRecord(sprinterTrainingList, false, SprinterTraining.class);
        List<Challenge> challengeList = ModelsSeeder.seedChallenge(12);
        cpd.addRecord(challengeList, false, Challenge.class);
    }

    @Test
    public void addRunner() throws Exception {
        List<Runner> runnerList = ModelsSeeder.seedRunner(12);
        cpd.addRunner(runnerList, false);
        Result<Runner> result = cpd.getRecords(Runner.class);
        log.info(result.getMessage());
        Assert.assertEquals(runnerList.size(), result.getData().size());
    }

    @Test
    public void addTraining() throws Exception{
        List<Training> trainingList = ModelsSeeder.seedTraining(12);
        cpd.addTraining(trainingList, false);
        Result<Training> result = cpd.getRecords(Training.class);
        log.info(result.getMessage());
        Assert.assertEquals(trainingList.size(), result.getData().size());
    }

    @Test
    public void addMarathonTrn() throws Exception {
        List<MarathonTraining> marathonTrainingList = ModelsSeeder.seedMarathonTrn(12);
        cpd.addMarathonTrn(marathonTrainingList, false);
        Result<MarathonTraining> result = cpd.getRecords(MarathonTraining.class);
        log.info(result.getMessage());
        Assert.assertEquals(marathonTrainingList.size(), result.getData().size());
    }

    @Test
    public void addSprinterTrn() throws Exception {
        List<SprinterTraining> sprinterTrainingList = ModelsSeeder.seedSprinterTrn(12);
        cpd.addSprinterTrn(sprinterTrainingList, false);
        Result<SprinterTraining> result = cpd.getRecords(SprinterTraining.class);
        log.info(result.getMessage());
        Assert.assertEquals(sprinterTrainingList.size(), result.getData().size());
    }

    @Test
    public void addChallenge() throws Exception {
        List<Challenge> challengeList = ModelsSeeder.seedChallenge(12);
        cpd.addChallenge(challengeList, false);
        Result<Challenge> result = cpd.getRecords(Challenge.class);
        log.info(result.getMessage());
        Assert.assertEquals(challengeList.size(), result.getData().size());
    }

    @Test
    public void getRunner() throws Exception {
        addRunner();
        Assert.assertEquals(cpd.getRunner().getResultType(), ResultType.OK);
    }

    @Test
    public void getTraining() throws Exception {
        addTraining();
        Assert.assertEquals(cpd.getTraining().getResultType(), ResultType.OK);
    }

    @Test
    public void getMarathonTrn() throws Exception {
        addMarathonTrn();
        Assert.assertEquals(cpd.getMarathonTrn().getResultType(), ResultType.OK);
    }

    @Test
    public void getSprinterTrn() throws Exception {
        addSprinterTrn();
        Assert.assertEquals(cpd.getSprinterTrn().getResultType(), ResultType.OK);
    }

    @Test
    public void getChallenge() throws Exception {
        addChallenge();
        Assert.assertEquals(cpd.getChallenge().getResultType(), ResultType.OK);
    }

    @Test
    public void getRunnerById() throws Exception{
        Runner rec = ModelsSeeder.seedRunner(1).get(0);
        List list = new ArrayList();
        list.add(rec);
        cpd.addRunner(list,false);
        Assert.assertEquals(cpd.getRunnerById(rec.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void getTrainingById() throws Exception{
        Training training = ModelsSeeder.seedTraining(1).get(0);
        List list = new ArrayList();
        list.add(training);
        cpd.addTraining(list, false);
        Assert.assertEquals(cpd.getTrainingById(training.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void getChallengeById() throws Exception{
        Challenge challenge = ModelsSeeder.seedChallenge(1).get(0);
        List list = new ArrayList();
        list.add(challenge);
        cpd.addChallenge(list, false);
        Assert.assertEquals(cpd.getChallengeById(challenge.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void getMarathonTrainingById() throws Exception{
        MarathonTraining marathonTraining = ModelsSeeder.seedMarathonTrn(1).get(0);
        List list = new ArrayList();
        list.add(marathonTraining);
        cpd.addMarathonTrn(list, false);
        Assert.assertEquals(cpd.getMarathonTrainingById(marathonTraining.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void getSprinterTrainingById() throws Exception{
        SprinterTraining sprinterTraining = ModelsSeeder.seedSprinterTrn(1).get(0);
        List list = new ArrayList();
        list.add(sprinterTraining);
        cpd.addSprinterTrn(list, false);
        Assert.assertEquals(cpd.getSprinterTrainingById(sprinterTraining.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void deleteRunner() throws Exception {
        Runner rec = ModelsSeeder.seedRunner(1).get(0);
        List list = new ArrayList();
        list.add(rec);
        cpd.addRunner(list, false);
        Assert.assertEquals(cpd.deleteRunner(rec.getId()).getResultType(), ResultType.OK);
    }

    @Test
    public void updateRunner() throws Exception {
        Long id = 99L;
        List<Runner> runnerList = ModelsSeeder.seedRunner(2);
        runnerList.get(0).setId(id);
        cpd.addRunner(runnerList, false);
        Runner runner = runnerList.get(0);
        runner.setFirstName(runner.getFirstName() + Constants.TEST);
        Assert.assertEquals(cpd.updateRunner(id, runner).getResultType(), ResultType.OK);
    }

    @Test
    public void updateTraining() throws Exception {
        Long id = 99L;
        List<Training> trainingList = ModelsSeeder.seedTraining(2);
        trainingList.get(0).setId(id);
        cpd.addTraining(trainingList, false);
        Training training = trainingList.get(0);
        training.setDate(training.getDate() + Constants.TEST);
        Assert.assertEquals(cpd.updateTraining(id, training).getResultType(), ResultType.OK);
    }

    @Test
    public void updateMarathonTraining() throws Exception{
        Long id = 99L;
        List<MarathonTraining> marathonTrainingList = ModelsSeeder.seedMarathonTrn(2);
        marathonTrainingList.get(0).setId(id);
        cpd.addMarathonTrn(marathonTrainingList, false);
        MarathonTraining marathonTraining = marathonTrainingList.get(0);
        marathonTraining.setTotalTime(marathonTraining.getTotalTime() + Constants.TEST);
        Assert.assertEquals(cpd.updateMarathonTraining(id, marathonTraining).getResultType(), ResultType.OK);
    }

    @Test
    public void updateSprinterTraining() throws Exception{
        Long id = 99L;
        List<SprinterTraining> sprinterTrainingList = ModelsSeeder.seedSprinterTrn(2);
        sprinterTrainingList.get(0).setId(id);
        cpd.addSprinterTrn(sprinterTrainingList, false);
        SprinterTraining sprinterTraining = sprinterTrainingList.get(0);
        sprinterTraining.setTotalTime(sprinterTraining.getTotalTime() + Constants.TEST);
        Assert.assertEquals(cpd.updateSprinterTraining(id, sprinterTraining).getResultType(), ResultType.OK);
    }

    @Test
    public void updateChallenge() throws Exception{
        Long id = 99L;
        List<Challenge> challengeList = ModelsSeeder.seedChallenge(2);
        challengeList.get(0).setId(id);
        cpd.addChallenge(challengeList, false);
        Challenge challenge = challengeList.get(0);
        challenge.setChallName(challenge.getChallName() + Constants.TEST);
        Assert.assertEquals(cpd.updateChallenge(id, challenge).getResultType(), ResultType.OK);
    }
}