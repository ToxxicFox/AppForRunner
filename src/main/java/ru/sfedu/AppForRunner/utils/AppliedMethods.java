package ru.sfedu.AppForRunner.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.AppForRunner.models.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppliedMethods {
    private static Logger log = LogManager.getLogger(AppliedMethods.class);

    public static <T> Result<T> getById(List<T> rec, Long id, Class<T> tClass) {
        try {
            Method getter = tClass.getMethod(Constants.GET_ID);
            for (T elem : rec) {
                Long recId = (Long) getter.invoke(elem);
                if (recId.equals(id)) {
                    return new Result<>(Collections.singletonList(elem), ResultType.OK, Constants.ELEM_FOUND);
                }
            }
            return new Result<>(null, ResultType.ERROR, Constants.NOT_FOUND);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.METH_ERROR);
        }
    }


    public static <T> boolean isReplicate(List<T> newRecs, List<T> prevRecs) throws IllegalAccessException {
        Class<?> tClass = newRecs.get(0).getClass();
        log.debug(tClass);
        try {
            Method getterId = tClass.getMethod(Constants.GET_ID);
            List<Long> newIds = new ArrayList<>();
            for (T elem : newRecs) {
                newIds.add((Long) getterId.invoke(elem));
            }
            List<Long> oldIds = new ArrayList<>();
            log.debug(prevRecs.toString());
            for (T elem : prevRecs) {
                oldIds.add((Long) getterId.invoke(elem));
            }
            return oldIds.stream().anyMatch(newIds::contains);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            log.error(e);
            log.info(Constants.METH_ERROR);
            return false;
        }
    }

    public static <T> List<Long> getIds(List<T> list) {
        List<Long> res = new ArrayList<>();
        Class<?> cn;
        if (list != null && list.size() > 0) {
            cn = list.get(0).getClass();
        } else {
            return new ArrayList<>();
        }
        try {
            Method getter = cn.getMethod(Constants.GET_ID);
            for (T el : list) {
                res.add((Long) getter.invoke(el));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e);
            log.info(Constants.METH_ERROR);
        }
        return res;
    }

    public static List<Runner> getRunnerFromResultSet(ResultSet rs) throws SQLException {
        List<Runner> res = new ArrayList<>();
        while (rs.next()) {
            Runner runner = new Runner();
            runner.setId(rs.getLong(1));
            runner.setFirstName(rs.getString(2));
            runner.setLastName(rs.getString(3));
            runner.setAge(rs.getLong(4));
            runner.setWeight(rs.getLong(5));
            runner.setHeight(rs.getLong(6));
            res.add(runner);
        }
        return res;
    }

    public static List<Training> getTrainingFromResultSet(ResultSet rs) throws SQLException {
        List<Training> res = new ArrayList<>();
        while (rs.next()) {
            Training training = new Training();
            training.setId(rs.getLong(1));
            training.setRunner(new Runner());
            training.getRunner().setId(rs.getLong(2));
            training.setDistance(rs.getLong(3));
            training.setTotalTime(rs.getString(4));
            training.setDate(rs.getString(5));
            training.setChallenge(new Challenge());
            training.getChallenge().setId(rs.getLong(6));
            training.setTypeOfTraining(TypeOfTraining.valueOf(rs.getString(7)));
            res.add(training);
        }
        return res;
    }

    public static List<MarathonTraining> getMarathonTrainingFromResultSet(ResultSet rs) throws SQLException {
        List<MarathonTraining> res = new ArrayList<>();
        while (rs.next()) {
            MarathonTraining marathonTraining = new MarathonTraining();
            marathonTraining.setId(rs.getLong(1));
            marathonTraining.setRunner(new Runner());
            marathonTraining.getRunner().setId(rs.getLong(2));
            marathonTraining.setDistance(rs.getLong(3));
            marathonTraining.setTotalTime(rs.getString(4));
            marathonTraining.setDate(rs.getString(5));
            marathonTraining.setChallenge(new Challenge());
            marathonTraining.getChallenge().setId(rs.getLong(6));
            marathonTraining.setTypeOfTraining(TypeOfTraining.valueOf(rs.getString(7)));
            marathonTraining.setAvgPace(rs.getString(8));
            marathonTraining.setTypeOfRoute(RouteType.valueOf(rs.getString(9)));
            marathonTraining.setBestTime(rs.getLong(10));
            marathonTraining.setBestDistance(rs.getLong(11));
            res.add(marathonTraining);
        }
        return res;
    }

    public static List<SprinterTraining> getSprinterTrainingFromResultSet(ResultSet rs) throws SQLException {
        List<SprinterTraining> res = new ArrayList<>();
        while (rs.next()) {
            SprinterTraining sprinterTraining = new SprinterTraining();
            sprinterTraining.setId(rs.getLong(1));
            sprinterTraining.setRunner(new Runner());
            sprinterTraining.getRunner().setId(rs.getLong(2));
            sprinterTraining.setDistance(rs.getLong(3));
            sprinterTraining.setTotalTime(rs.getString(4));
            sprinterTraining.setDate(rs.getString(5));
            sprinterTraining.setChallenge(new Challenge());
            sprinterTraining.getChallenge().setId(rs.getLong(6));
            sprinterTraining.setTypeOfTraining(TypeOfTraining.valueOf(rs.getString(7)));
            sprinterTraining.setAvgLap(rs.getString(8));
            sprinterTraining.setNumSegments(rs.getLong(9));
            sprinterTraining.setTotalDistance(rs.getLong(10));
            sprinterTraining.setBestSigment(rs.getLong(11));
            res.add(sprinterTraining);
        }
        return res;
    }


    public static List<Challenge> getChallengeFromResultSet(ResultSet rs) throws SQLException {
        List<Challenge> res = new ArrayList<>();
        while (rs.next()) {
            Challenge challenge = new Challenge();
            challenge.setId(rs.getLong(1));
            challenge.setChallName(rs.getString(2));
            challenge.setStartDate(rs.getString(3));
            challenge.setFinishDate(rs.getString(4));
            challenge.setTrnTarget(TrainingTarget.SPEED);
            challenge.setChallDistance(rs.getLong(6));
            challenge.setChallTime(rs.getLong(7));
            res.add(challenge);
        }
        return res;
    }


}
