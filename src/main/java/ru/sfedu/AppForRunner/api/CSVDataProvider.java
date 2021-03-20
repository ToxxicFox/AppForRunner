package ru.sfedu.AppForRunner.api;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVDataProvider implements DataProvider{

    private CSVReader reader;
    private CSVWriter writer;

    private final String PATH = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_CSV);

    private final String FILE_EXT = ConfigurationUtil.getConfigurationEntry(Constants.CSV_FILE_EXT);

    private final Logger log = LogManager.getLogger(CSVDataProvider.class);

    public CSVDataProvider() throws IOException {
    }


    /**
     *
     * @param runnerList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> addRunner(List<Runner> runnerList, boolean check) throws Exception {
        return addRecord(runnerList, check, Runner.class);
    }

    /**
     *
     * @param trainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> addTraining(List<Training> trainingList, boolean check) throws Exception{
        return addRecord(trainingList, check, Training.class);
    }

    /**
     *
     * @param marathonTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> addMarathonTrn(List<MarathonTraining> marathonTrainingList, boolean check) throws Exception{
        return addRecord(marathonTrainingList, check, MarathonTraining.class);
    }

    /**
     *
     * @param sprinterTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> addSprinterTrn(List<SprinterTraining> sprinterTrainingList, boolean check) throws Exception{
        return addRecord(sprinterTrainingList, check, SprinterTraining.class);
    }

    /**
     *
     * @param challengeList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> addChallenge(List<Challenge> challengeList, boolean check) throws Exception{
        return addRecord(challengeList, check, Challenge.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> getRunner() throws Exception {
        return getRecords(Runner.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> getTraining() throws Exception {
        return getRecords(Training.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> getMarathonTrn() throws Exception {
        return getRecords(MarathonTraining.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> getSprinterTrn() throws Exception {
        return getRecords(SprinterTraining.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> getChallenge() throws Exception {
        return getRecords(Challenge.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> getRunnerById(Long id) throws Exception {
        return getRecordById(id, Runner.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> getTrainingById(Long id) throws Exception {
        return getRecordById(id, Training.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> getMarathonTrainingById(Long id) throws Exception {
        return getRecordById(id, MarathonTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> getSprinterTrainingById(Long id) throws Exception {
        return getRecordById(id, SprinterTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> getChallengeById(Long id) throws Exception {
        return getRecordById(id, Challenge.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> deleteRunner(Long id) throws Exception{
        return deleteRecord(id, Runner.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> deleteTraining(Long id) throws Exception {
        return deleteRecord(id, Training.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> deleteMarathonTraining(Long id) throws Exception {
        return deleteRecord(id, MarathonTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> deleteSprinterTraining(Long id) throws Exception{
        return deleteRecord(id, SprinterTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> deleteChallenge(Long id) throws Exception{
        return deleteRecord(id, Challenge.class);
    }

    /**
     *
     * @param id
     * @param runner
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> updateRunner(Long id, Runner runner) throws Exception {
        return updateRecord(id, runner);
    }

    /**
     *
     * @param id
     * @param training
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> updateTraining(Long id, Training training) throws Exception{
        return updateRecord(id, training);
    }

    /**
     *
     * @param id
     * @param marathonTraining
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> updateMarathonTraining(Long id, MarathonTraining marathonTraining) throws Exception{
        return updateRecord(id, marathonTraining);
    }

    /**
     *
     * @param id
     * @param sprinterTraining
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> updateSprinterTraining(Long id, SprinterTraining sprinterTraining) throws Exception{
        return updateRecord(id, sprinterTraining);
    }

    /**
     *
     * @param id
     * @param challenge
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> updateChallenge(Long id, Challenge challenge) throws Exception{
        return updateRecord(id, challenge);
    }

    /**
     *
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> Result<T> getRecords(Class tClass) throws IOException {
        Result<T> tResult = get(tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        } else {
            List<T> listRec = tResult.getData();
            if (listRec.size() == 0) {
                return new Result<>(null, ResultType.ERROR, Constants.EMPTY);
            }
            return new Result<>(listRec, ResultType.OK, Constants.FOUND);
        }
    }

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> Result<T> getRecordById(Long id, Class<T> tClass) throws IOException{
        Result<T> tResult = get(tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        return AppliedMethods.getById(tResult.getData(), id, tClass);
    }

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
     */
    @Override
    public <T> Result<T> addRecord(List<T> recList, boolean check, Class<T> tClass) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalAccessException {
        if (check) {
            Result<T> tResult = get(tClass);
            List<T> prevRecords = tResult.getData();
            if (tResult.getResultType() == ResultType.ERROR) {
                prevRecords = new ArrayList<>();
            }
            if (AppliedMethods.isReplicate(recList, prevRecords)) {
                return new Result<T>(null, ResultType.ERROR, Constants.EXIST_ERROR);
            }
            recList = Stream.concat(prevRecords.stream(), recList.stream()).collect(Collectors.toList());
        }
        add(recList, tClass);
        return new Result<T>(recList, ResultType.OK, Constants.SUCCESS_ADD);
    }

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    @Override
    public <T> Result<T> deleteRecord (Long id, Class<T> tClass) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Result<T> tResult;
        tResult = getRecordById(id, tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        T recToDel = tResult.getData().get(0);
        tResult = get(tClass);
        if (tResult.getResultType() == ResultType.ERROR){
            return tResult;
        }
        List<T> tList = tResult.getData();
        tList.remove(recToDel);
        tResult = add(tList, tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        return new Result<>(tList, ResultType.OK, Constants.DELETE_SUCCES);
    }

    /**
     *
     * @param id
     * @param record
     * @param <T>
     * @return
     * @throws CsvRequiredFieldEmptyException
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws IllegalAccessException
     */
    @Override
    public <T> Result<T> updateRecord(Long id, T record) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException, IllegalAccessException {
        Class<T> tClass = (Class<T>) record.getClass();
        Result<T> tResult = deleteRecord(id, tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        List<T> tList = tResult.getData();
        tList.add(record);
        return addRecord(tList, false, tClass);
    }


    private <T> Result<T> get(Class<T> tClass) throws IOException, RuntimeException {
        try {
            initReader(tClass);
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType(tClass).build();
            List<T> list = csvToBean.parse();
            return new Result<T>(list, ResultType.OK, Constants.REC_FOUND);
        }
        catch (RuntimeException e) {
            repFile(tClass);
            log.error(e);
            return new Result<T>(null, ResultType.ERROR, Constants.FILE_ERR);
        }
    }


    private <T> Result<T> add(List<T> list, Class<?> tClass) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
       initWriter(tClass);
        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).withApplyQuotesToAll(false).build();
        beanToCsv.write(list);
        close();
        return new Result<>(list, ResultType.OK, Constants.ADD_SUCCCES);
    }

    public String getPath (Class<?> tClass) {
        return PATH + tClass.getSimpleName().toLowerCase() + FILE_EXT;
    }


    public void initDS(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            Path dirPath = Paths.get(PATH);
            Files.createDirectories(dirPath);
            file.createNewFile();
        }
    }


    private void initReader(Class<?> tClass) throws IOException {
        String path = getPath(tClass);
        initDS(path);
        this.reader = new CSVReader(new FileReader(path));
    }


    private void initWriter(Class<?> tClass) throws IOException {
        String path = getPath(tClass);
        initDS(path);
        this.writer = new CSVWriter(new FileWriter(path, false));
    }


    private void close() {
        try {
            if (reader != null) {
                this.reader.close();
            }
            if (writer != null) {
                this.writer.close();
            }
        } catch (IOException e) {
            log.error(e);
            log.debug(Constants.STREAM_CLOSED);
        }
    }


    public void repFile(Class<?> tClass) throws IOException {
        log.info(Constants.FILE_ERR);
        FileWriter file = new FileWriter(getPath(tClass));
        file.flush();
    }

}
