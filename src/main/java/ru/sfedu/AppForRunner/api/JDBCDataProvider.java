package ru.sfedu.AppForRunner.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JDBCDataProvider implements DataProvider{

    private final String DB_USER = ConfigurationUtil.getConfigurationEntry(Constants.DB_USER);
    private final String DB_PASSWORD = ConfigurationUtil.getConfigurationEntry(Constants.DB_PASSWORD);

    private final String DB_PROTOCOL = ConfigurationUtil.getConfigurationEntry(Constants.DB_PROTOCOL);
    private final String PATH_TO_DB = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_DB);
    private final String DB_NAME = ConfigurationUtil.getConfigurationEntry(Constants.DB_NAME);

    private final String PATH_TO_SCHEMA = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_SQL_SCHEMA);

    private Connection connection;
    private Statement statement;

    private final Logger log = LogManager.getLogger(JDBCDataProvider.class);



    public JDBCDataProvider() throws IOException {
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_PROTOCOL + PATH_TO_DB + DB_NAME, DB_USER, DB_PASSWORD);
        statement = connection.createStatement();
    }


    private void initDataSource() throws SQLException {
        log.debug(Constants.LOG_CREATING_FILE);
        File file = new File(PATH_TO_SCHEMA);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                String query = new String(data, StandardCharsets.UTF_8);
                initConnection();
                log.debug(query);
                statement.executeUpdate(query);
            } catch (IOException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            }
        }
        initConnection();
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
    public Result<Training> addTraining(List<Training> trainingList, boolean check) throws Exception {
        Result<Training> result = addRecord(trainingList, check, Training.class);
        if (result.getResultType() == ResultType.ERROR) {
            return result;
        }
        for (Training training : trainingList) {
            addRecord(Collections.singletonList(training.getRunner()), true, Runner.class);
            setParent(Training.class, Runner.class, training.getRunner().getId(), training.getId());
            addRecord(Collections.singletonList(training.getChallenge()), true, Challenge.class);
            setParent(Training.class, Challenge.class, training.getChallenge().getId(), training.getId());
        }
        return result;
    }

    /**
     *
     * @param marathonTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> addMarathonTrn(List<MarathonTraining> marathonTrainingList, boolean check) throws Exception {
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
    public Result<SprinterTraining> addSprinterTrn(List<SprinterTraining> sprinterTrainingList, boolean check) throws Exception {
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

    @Override
    public Result<Training> getTraining() throws Exception {
        Result<Training> result = getRecords(Training.class);
        if (result.getResultType() == ResultType.ERROR) {
            return  result;
        }
        List<Training> trainingList = result.getData();
        trainingList.stream().forEach(training -> {
            Runner runner = training.getRunner();
            Result<Runner> runnerResult = getRunnerById(runner.getId());
            if (runnerResult.getResultType() == ResultType.OK) {
                log.info(runnerResult.getData());
                runner = runnerResult.getData().get(0);
            }
            training.setRunner(runner);
            Challenge challenge = training.getChallenge();
            Result<Challenge> challengeResult = getChallengeById(challenge.getId());
            if (challengeResult.getResultType() == ResultType.OK) {
                log.info(challengeResult.getData());
                challenge = challengeResult.getData().get(0);
            }
            training.setChallenge(challenge);
        });
        result.setData(trainingList);
        log.info(trainingList);
        return  result;
    }

    @Override
    public Result<MarathonTraining> getMarathonTrn() throws Exception {
        return getRecords(MarathonTraining.class);
    }

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
     */
    @Override
    public Result<Runner> getRunnerById(Long id) {
        return getRecordById(id, Runner.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Result<Training> getTrainingById(Long id) {
        return getRecordById(id, Training.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Result<MarathonTraining> getMarathonTrainingById(Long id) {
        return getRecordById(id, MarathonTraining.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Result<SprinterTraining> getSprinterTrainingById(Long id){
        return getRecordById(id, SprinterTraining.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Result<Challenge> getChallengeById(Long id){
        return getRecordById(id, Challenge.class);
    }

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
     * @param listRecord
     * @param append
     * @param cn
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Result<T> addRecord(List<T> listRecord, boolean append, Class<T> cn) throws Exception {
        initDataSource();
        if (!append) {
            flushTable(cn);
        }
        return add(listRecord, cn);
    }

    /**
     *
     * @param cn
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Result<T> getRecords(Class cn) throws Exception {
        initDataSource();
        Result<T> res = get(cn);
        if (res.getResultType() == ResultType.ERROR) {
            return res;
        }
        List<T> listRecords = res.getData();
        if (listRecords.size() == 0) {
            return new Result<>(listRecords, ResultType.ERROR, Constants.EMPTY);
        }
        return res;
    }

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     */
    @Override
    public <T> Result<T> getRecordById(Long id, Class<T> tClass) {
        try {
            initDataSource();
            ResultSet rs = statement.executeQuery(String.format(Constants.DB_BY_ID, tClass.getSimpleName(), id));
            List<T> res = getDataFormResultSet(rs, tClass);
            if (res.size() > 0) {
                return new Result<>(res, ResultType.OK, Constants.FOUND_ELEM);
            }
            return new Result<>(null, ResultType.ERROR, Constants.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.SQL_FAIL);
        }
    }

    /**
     *
     * @param id
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Result<T> deleteRecord(Long id, Class<T> tClass) throws Exception{
        initDataSource();
        int rowCount = statement.executeUpdate(
                String.format(
                        Constants.DELETE_BY_ID,
                        tClass.getSimpleName(),
                        id));
        close();
        log.debug(rowCount);
        if (rowCount > 0) {
            return new Result<>(null, ResultType.OK, Constants.DELETE_SUCCES);
        }
        return new Result<>(null, ResultType.ERROR, Constants.NOT_FOUND);
    }

    /**
     *
     * @param id
     * @param record
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Result<T> updateRecord(Long id, T record) throws Exception{
        initDataSource();
        int rowCount = statement.executeUpdate(
                String.format(
                        Constants.UPDATE,
                        record.getClass().getSimpleName(),
                        String.join(Constants.PARAM_DELIMITER, getSqlUpdateString(record)), id));
                close();
                log.debug(rowCount);
                if (rowCount > 0) {
                    return new Result<>(null, ResultType.OK, Constants.UPDATE_SUCCESS);
                }
                return new Result<>(null, ResultType.ERROR, Constants.NOT_FOUND);
    }

    /**
     *
     * @param record
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private <T> List<String> getSqlUpdateString (T record) throws InvocationTargetException, IllegalAccessException{
        String[] data = getSqlString(record);
        List<String> columns = Arrays.asList(data[0].split(Constants.PARAM_DELIMITER));
        List<String> values = Arrays.asList(data[1].split(Constants.PARAM_DELIMITER));
        List<String> res = IntStream
                .range(0, columns.size())
                .mapToObj(index -> columns.get(index) + Constants.SINGLE_EQUAL + values.get(index))
                .collect(Collectors.toList());
        return res;
    }

    /**
     *
     * @param record
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <T> String[] getSqlString (T record) throws InvocationTargetException, IllegalAccessException {
        Class<?> cn = record.getClass();
        List<Method> methods = Arrays.stream(cn.getMethods())
                .filter(method -> method.getName().matches(Constants.GET_METHODS_PATTERN))
                .filter(method -> !method.getName().equals(Constants.GET_CLASS))
                .collect(Collectors.toList());
        log.debug(methods);
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Method method : methods) {
            if (Constants.PRIMITIVE_CLASSES.contains(method.getReturnType()) || method.getReturnType().isEnum())  {
                System.out.println(method.invoke(record));
                columns.add(method.getName().replace(Constants.GET, Constants.EMPTY_STRING).toLowerCase(Locale.ROOT));
                if (method.getReturnType() == String.class) {
                    values.add(String.format(Constants.SQL_STRING,method.invoke(record)));
                } else if (method.getReturnType().isEnum()){
                    values.add(method.invoke(record).toString());
                } else {
                    values.add(String.valueOf(method.invoke(record)));
                }
            }
        }
        log.debug(columns);
        log.debug(values);
        return new String[]{String.join(Constants.PARAM_DELIMITER, columns), String.join(Constants.PARAM_DELIMITER, values)};
    }

    /**
     *
     * @param listRecord
     * @param cn
     * @param <T>
     * @return
     * @throws SQLException
     */
    private <T> Result<T> add(List<T> listRecord, Class<?> cn) throws SQLException {
        initDataSource();
        try {
            List<String> query = new ArrayList<>();
            for (T record : listRecord) {
                String[] data = getSqlString(record);
                log.debug(String.format(Constants.DB_INSERT,cn.getSimpleName().toUpperCase(Locale.ROOT),data[0], data[1]));
                query.add(
                        String.format(
                                Constants.DB_INSERT,
                                cn.getSimpleName().toUpperCase(Locale.ROOT),
                                data[0], data[1]));
            }
            log.info(String.join(Constants.EMPTY_STRING, query));
            statement.executeUpdate(String.join(Constants.EMPTY_STRING, query));
            return new Result<>(listRecord, ResultType.OK, Constants.INSERTED_SUCCESSFULLY);
        } catch( Exception e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.SQL_FAIL);
        }
        finally {
            close();
        }
    }

    /**
     *
     * @param cn
     * @param <T>
     * @return
     * @throws SQLException
     */
    private <T> Result<T> get(Class<?> cn) throws SQLException {
        initDataSource();
        try {
            ResultSet rs = statement.executeQuery(String.format(Constants.DB_SELECT, cn.getSimpleName()));
            List<T> res = getDataFormResultSet(rs, cn);
            log.info(res);
            return new Result<T>(res, ResultType.OK, Constants.RECORDS_FOUND);
        }
        catch (SQLException e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.SQL_FAIL);
        }
        finally {
            close();
        }
    }


    private void close() throws SQLException {
        connection.close();
        statement.close();
    }


    private <T> void setParent(Class<?> cn, Class<?> parentCn, Long parentId, Long id) throws SQLException{
        initDataSource();
        try {
            log.info(String.format(Constants.CHANGE_PARENT_ID, cn.getSimpleName(), parentCn.getSimpleName(), parentId, id));
            statement.executeUpdate(String.format(Constants.CHANGE_PARENT_ID, cn.getSimpleName(), parentCn.getSimpleName(), parentId, id));
        }
        catch (SQLException e) {
            log.error(e);
        }
        finally {
            close();
        }
    }


    private <T> Result<T> getByParent(Long parentId, Class<?> cn, Class<?> parentCn) {
        try {
            initDataSource();
            log.info(String.format(Constants.GET_BY_PARENT_ID, cn.getSimpleName(), parentCn.getSimpleName(), parentId));
            ResultSet rs = statement.executeQuery(String.format(Constants.GET_BY_PARENT_ID, cn.getSimpleName(), parentCn.getSimpleName(), parentId));
            List<T> res = getDataFormResultSet(rs, cn);
            return new Result<>(res, ResultType.OK, Constants.REC_FOUND);
        } catch (Exception e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.SQL_FAIL);
        }
    }


    public void flushTable(Class<?> cn) throws SQLException {
        initDataSource();
        log.info(Constants.FLUSH_FILE + cn.getSimpleName().toUpperCase(Locale.ROOT));
        statement.executeUpdate(String.format(Constants.DELETE_ALL, cn.getSimpleName().toUpperCase(Locale.ROOT)));
        close();
    }


    private <T> List<T> getDataFormResultSet(ResultSet rs, Class cn) throws SQLException {
        List<T> res = new ArrayList<>();
        if (cn == Runner.class) {
            res = (List<T>) AppliedMethods.getRunnerFromResultSet(rs);
        }
        if (cn == Training.class){
            res = (List<T>) AppliedMethods.getTrainingFromResultSet(rs);
        }
        if (cn == MarathonTraining.class) {
            res = (List<T>) AppliedMethods.getMarathonTrainingFromResultSet(rs);
        }
        if (cn == SprinterTraining.class) {
            res = (List<T>) AppliedMethods.getSprinterTrainingFromResultSet(rs);
        }
        if (cn == Challenge.class) {
            res = (List<T>) AppliedMethods.getChallengeFromResultSet(rs);
    }
        return res;
    }
}
