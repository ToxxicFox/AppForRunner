package ru.sfedu.AppForRunner.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.Result;

import java.io.IOException;
import java.util.List;

public interface DataProvider{
    /**
     *
     * @param runnerList
     * @param check
     * @return
     * @throws Exception
     */
    Result<Runner> addRunner(List<Runner> runnerList, boolean check) throws Exception;

    /**
     *
     * @param trainingList
     * @param check
     * @return
     * @throws Exception
     */
    Result<Training> addTraining(List<Training> trainingList, boolean check) throws Exception;

    /**
     *
     * @param marathonTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    Result<MarathonTraining> addMarathonTrn(List<MarathonTraining> marathonTrainingList, boolean check) throws Exception;

    /**
     *
     * @param sprinterTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    Result<SprinterTraining> addSprinterTrn(List<SprinterTraining> sprinterTrainingList, boolean check) throws Exception;

    /**
     *
     * @param challengeList
     * @param check
     * @return
     * @throws Exception
     */
    Result<Challenge> addChallenge(List<Challenge> challengeList, boolean check) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    Result<Runner> getRunner() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    Result<Training> getTraining() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    Result<MarathonTraining> getMarathonTrn() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    Result<SprinterTraining> getSprinterTrn() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    Result<Challenge> getChallenge() throws Exception;

    /**
     *
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     * @throws Exception
     */
    <T> Result<T> getRecords(Class tClass) throws IOException, Exception;

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> Result<T> getRecordById(Long id, Class<T> tClass) throws IOException;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Runner> getRunnerById(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Training> getTrainingById(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<MarathonTraining> getMarathonTrainingById(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<SprinterTraining> getSprinterTrainingById(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Challenge> getChallengeById(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Runner> deleteRunner(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Training> deleteTraining(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<MarathonTraining> deleteMarathonTraining(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<SprinterTraining> deleteSprinterTraining(Long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Result<Challenge> deleteChallenge(Long id) throws Exception;

    /**
     *
     * @param id
     * @param runner
     * @return
     * @throws Exception
     */
    Result<Runner> updateRunner(Long id, Runner runner) throws Exception;

    /**
     *
     * @param id
     * @param training
     * @return
     * @throws Exception
     */
    Result<Training> updateTraining(Long id, Training training) throws Exception;

    /**
     *
     * @param id
     * @param marathonTraining
     * @return
     * @throws Exception
     */
    Result<MarathonTraining> updateMarathonTraining(Long id, MarathonTraining marathonTraining) throws Exception;

    /**
     *
     * @param id
     * @param sprinterTraining
     * @return
     * @throws Exception
     */
    Result<SprinterTraining> updateSprinterTraining(Long id, SprinterTraining sprinterTraining) throws Exception;

    /**
     *
     * @param id
     * @param challenge
     * @return
     * @throws Exception
     */
    Result<Challenge> updateChallenge(Long id, Challenge challenge) throws Exception;

    /**
     *
     * @param recList
     * @param check
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     * @throws IllegalAccessException
     * @throws Exception
     */
    <T> Result<T> addRecord(List<T> recList, boolean check, Class<T> tClass) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalAccessException, Exception;

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Result<T> deleteRecord(Long id, Class<T> tClass) throws Exception;

    /**
     *
     * @param id
     * @param rec
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Result<T> updateRecord(Long id, T rec) throws Exception;
}
