package ru.sfedu.AppForRunner.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.AppForRunner.models.Runner;
import ru.sfedu.AppForRunner.models.Training;
import ru.sfedu.AppForRunner.utils.ModelsSeeder;
import ru.sfedu.AppForRunner.utils.Result;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCDataProviderTest {

    public JDBCDataProvider cpd = new JDBCDataProvider();
    public Logger log = LogManager.getLogger(this.getClass());

    public JDBCDataProviderTest() throws IOException {
    }

    @Before
    public void flushData() throws SQLException {
        cpd.flushTable(Runner.class);
    }

    @Test
    public void addRunner() throws Exception {
        List<Runner> runnerList = ModelsSeeder.seedRunner(12);
        cpd.addRunner(runnerList, false);
        Result<Runner> result = cpd.getRecords(Runner.class);
        log.info(result.getData());
        log.info(result.getMessage());
        Assert.assertEquals(runnerList.size(), result.getData().size());
    }

    @Test
    public void addTraining() throws Exception{
        List<Training> trainingList = ModelsSeeder.seedTraining(1);
        cpd.addTraining(trainingList, false);
        Result<Training> result = cpd.getRecords(Training.class);
        log.info(result.getData());
        log.info(result.getMessage());
        Assert.assertEquals(trainingList.size(), result.getData().size());
    }

    @Test
    public void addMarathonTrn() {
    }

    @Test
    public void addSprinterTrn() {
    }

    @Test
    public void addChallenge() {
    }

    @Test
    public void getRunner() {
    }

    @Test
    public void getTraining() {
    }

    @Test
    public void getMarathonTrn() {
    }

    @Test
    public void getSprinterTrn() {
    }

    @Test
    public void getChallenge() {
    }

    @Test
    public void getRunnerById() {
    }

    @Test
    public void getTrainingById() {
    }

    @Test
    public void getMarathonTrainingById() {
    }

    @Test
    public void getSprinterTrainingById() {
    }

    @Test
    public void getChallengeById() {
    }

    @Test
    public void deleteRunner() {
    }

    @Test
    public void deleteTraining() {
    }

    @Test
    public void deleteMarathonTraining() {
    }

    @Test
    public void deleteSprinterTraining() {
    }

    @Test
    public void deleteChallenge() {
    }

    @Test
    public void updateRunner() {
    }

    @Test
    public void updateTraining() {
    }

    @Test
    public void updateMarathonTraining() {
    }

    @Test
    public void updateSprinterTraining() {
    }

    @Test
    public void updateChallenge() {
    }
}