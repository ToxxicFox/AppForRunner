package ru.sfedu.AppForRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.AppForRunner.api.CSVDataProvider;
import ru.sfedu.AppForRunner.api.DataProvider;
import ru.sfedu.AppForRunner.api.JDBCDataProvider;
import ru.sfedu.AppForRunner.api.XMLDataProvider;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.Constants;
import ru.sfedu.AppForRunner.utils.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Main {
    public static final Logger log = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws Exception {
        checkArgsCount(args);
        DataProvider dp = getDataProvider(args[0]);
        switch (args[1].toUpperCase(Locale.ROOT)){
            case Constants.ADD_RUNNER: {
                Runner runner = new Runner();
                Boolean append = Boolean.valueOf(args[2]);
                runner.setId(Long.valueOf(args[3]));
                runner.setFirstName(args[4]);
                runner.setLastName(args[5]);
                runner.setAge(Long.valueOf(args[6]));
                runner.setHeight(Long.valueOf(args[7]));
                runner.setWeight(Long.valueOf(args[8]));
                List<Runner> list = new ArrayList<>();
                list.add(runner);
                Result<Runner> result = dp.addRunner(list, append);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.ADD_TRAINING: {
                Training training = new Training();
                Boolean append = Boolean.valueOf(args[2]);
                training.setId(Long.valueOf(args[3]));
                training.setRunner(dp.getRunnerById(Long.parseLong(args[4])).getData().get(0));
                training.setDistance(Long.valueOf(args[5]));
                training.setDate(args[6]);
                training.setChallenge(dp.getChallengeById(Long.parseLong(args[7])).getData().get(0));
                training.setTypeOfTraining(TypeOfTraining.valueOf(args[8]));
                List<Training> list = new ArrayList<>();
                list.add(training);
                Result<Training> result = dp.addTraining(list, append);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.ADD_MARATHON_TRAINING: {
                MarathonTraining marathonTraining = new MarathonTraining();
                Boolean append = Boolean.valueOf(args[2]);
                marathonTraining.setId(Long.valueOf(args[3]));
                marathonTraining.setRunner(dp.getRunnerById(Long.parseLong(args[4])).getData().get(0));
                marathonTraining.setDistance(Long.valueOf(args[5]));
                marathonTraining.setDate(args[6]);
                marathonTraining.setChallenge(dp.getChallengeById(Long.parseLong(args[7])).getData().get(0));
                marathonTraining.setTypeOfTraining(TypeOfTraining.valueOf(args[8]));
                marathonTraining.setAvgPace(args[9]);
                marathonTraining.setTypeOfRoute(RouteType.valueOf(args[10]));
                marathonTraining.setBestTime(Long.valueOf(args[11]));
                marathonTraining.setBestDistance(Long.valueOf(args[12]));
                List<MarathonTraining> list = new ArrayList<>();
                list.add(marathonTraining);
                Result<MarathonTraining> result = dp.addMarathonTrn(list, append);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.ADD_SPRINTER_TRAINING: {
                SprinterTraining sprinterTraining = new SprinterTraining();
                Boolean append = Boolean.valueOf(args[2]);
                sprinterTraining.setId(Long.valueOf(args[3]));
                sprinterTraining.setRunner(dp.getRunnerById(Long.parseLong(args[4])).getData().get(0));
                sprinterTraining.setDistance(Long.valueOf(args[5]));
                sprinterTraining.setDate(args[6]);
                sprinterTraining.setChallenge(dp.getChallengeById(Long.parseLong(args[7])).getData().get(0));
                sprinterTraining.setTypeOfTraining(TypeOfTraining.valueOf(args[8]));
                sprinterTraining.setAvgLap(args[9]);
                sprinterTraining.setNumSegments(Long.valueOf(args[10]));
                sprinterTraining.setTotalDistance(Long.valueOf(args[11]));
                sprinterTraining.setBestSigment(Long.valueOf(args[12]));
                List<SprinterTraining> list = new ArrayList<>();
                list.add(sprinterTraining);
                Result<SprinterTraining> result = dp.addSprinterTrn(list, append);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.ADD_CHALLENGE: {
                Challenge challenge = new Challenge();
                Boolean append = Boolean.valueOf(args[2]);
                challenge.setId(Long.valueOf(args[3]));
                challenge.setChallName(args[4]);
                challenge.setStartDate(args[5]);
                challenge.setFinishDate(args[6]);
                challenge.setTrnTarget(TrainingTarget.valueOf(args[7]));
                challenge.setChallDistance(Long.valueOf(args[8]));
                challenge.setChallTime(Long.valueOf(args[9]));
                challenge.setTypeOfChallenge(TypeOfChallenge.valueOf(args[10]));
                List<Challenge> list = new ArrayList<>();
                list.add(challenge);
                Result<Challenge> result = dp.addChallenge(list, append);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_RUNNERS:{
                Result<Runner> result = dp.getRunner();
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_TRAININGS:{
                Result<Training> result = dp.getTraining();
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_MARATHON_TRAININGS:{
                Result<MarathonTraining> result = dp.getMarathonTrn();
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_SPRINTER_TRAININGS:{
                Result<SprinterTraining> result = dp.getSprinterTrn();
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_CHALLENGES:{
                Result<Challenge> result = dp.getChallenge();
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_RUNNER_BY_ID: {
                Result<Runner> result = dp.getRunnerById(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_TRAINING_BY_ID: {
                Result<Training> result = dp.getTrainingById(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_MARATHON_TRAINING_BY_ID: {
                Result<MarathonTraining> result = dp.getMarathonTrainingById(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_SPRINTER_TRAINING_BY_ID: {
                Result<SprinterTraining> result = dp.getSprinterTrainingById(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.GET_CHALLENGE_BY_ID: {
                Result<Challenge> result = dp.getChallengeById(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.DELETE_RUNNER: {
                Result<Runner> result = dp.deleteRunner(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.DELETE_TRAINING: {
                Result<Training> result = dp.deleteTraining(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.DELETE_MARATHON_TRAINING: {
                Result<MarathonTraining> result = dp.deleteMarathonTraining(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.DELETE_SPRINTER_TRAINING: {
                Result<SprinterTraining> result = dp.deleteSprinterTraining(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.DELETE_CHALLENGE: {
                Result<Challenge> result = dp.deleteChallenge(Long.valueOf(args[2]));
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
            case Constants.UPDATE_RUNNER: {
                Runner runner = new Runner();
                Boolean append = Boolean.valueOf(args[2]);
                runner.setId(Long.valueOf(args[3]));
                runner.setFirstName(args[4]);
                runner.setLastName(args[5]);
                runner.setAge(Long.valueOf(args[6]));
                runner.setHeight(Long.valueOf(args[7]));
                runner.setWeight(Long.valueOf(args[8]));
                Result<Runner> result = dp.updateRunner(runner.getId(), runner);
                log.info(result.getMessage());
                log.info(result.getData());
                break;
            }
        }
    }



    private static DataProvider getDataProvider(String dpType) throws IOException {
        switch (dpType.toUpperCase(Locale.ROOT)) {
            case(Constants.CSV):{
                return new CSVDataProvider();
            }
            case(Constants.XML): {
                return new XMLDataProvider();
            }
            case(Constants.DB): {
                return new JDBCDataProvider();
            }
            default: {
                log.error(Constants.DP_ERROR);
                System.exit(0);
            }
        }
        return null;
    }

    private static void checkArgsCount(String[] args) {
        if (args.length < 2) {
            log.error(Constants.FEW_ARGS);
            System.exit(0);
        }
    }
}
