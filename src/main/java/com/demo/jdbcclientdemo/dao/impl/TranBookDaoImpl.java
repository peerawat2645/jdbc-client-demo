package com.demo.jdbcclientdemo.dao.impl;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DatabaseConstant;
import com.demo.jdbcclientdemo.dao.TranBookDao;
import com.demo.jdbcclientdemo.domain.TranBook;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TranBookDaoImpl implements TranBookDao {
    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "tran_book";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String BOOK_ID = "book_id";
    private final String BOOK_NAME = "book_name";
    private final String BOOK_TITLE = "book_title";

    private final String TRAN_ID = "tran_id";
    private final String TRAN_GROUP = "tran_group";
    private final String TRAN_CODE = "tran_code";
    private final String TRAN_REF_ID = "tran_ref_id";

    final RowMapper<TranBook> ROW_MAPPER = (ResultSet rs, int i) -> {
        final TranBook mapperObject = new TranBook();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setBookID(rs.getObject(BOOK_ID) != null ? BigInteger.valueOf(rs.getLong(BOOK_ID)) : null);
        mapperObject.setTranGroup(rs.getString(BOOK_NAME));
        mapperObject.setTranCode(rs.getString(BOOK_TITLE));

        mapperObject.setTranID(rs.getObject(TRAN_ID) != null ? BigInteger.valueOf(rs.getLong(TRAN_ID)) : null);
        mapperObject.setTranGroup(rs.getString(TRAN_GROUP));
        mapperObject.setTranCode(rs.getString(TRAN_CODE));
        mapperObject.setTranRefID(rs.getObject(TRAN_REF_ID) != null ? BigInteger.valueOf(rs.getLong(TRAN_REF_ID)) : null);

        return mapperObject;
    };

    @Override
    public List<TranBook> find(TranBook findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<TranBook> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql.append(DatabaseConstant.AND).append(CREATE_DATE).append(DatabaseConstant.EQUAL_STR_DATE_QUESTION_MARK);
                    parameters.add(findObject.getCreateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getCreateBy())) {
                    sql.append(DatabaseConstant.AND).append(CREATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateBy());
                }
                if (findObject.getUpdateDate() != null) {
                    sql.append(DatabaseConstant.AND).append(UPDATE_DATE).append(DatabaseConstant.EQUAL_STR_DATE_QUESTION_MARK);
                    parameters.add(findObject.getUpdateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUpdateBy())) {
                    sql.append(DatabaseConstant.AND).append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateBy());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getIsDelete())) {
                    sql.append(DatabaseConstant.AND).append(IS_DELETE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getIsDelete());
                }
                if (findObject.getBookID() != null) {
                    sql.append(DatabaseConstant.AND).append(BOOK_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getBookID().longValue());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getName())) {
                    sql.append(DatabaseConstant.AND).append(BOOK_NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getName());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTitle())) {
                    sql.append(DatabaseConstant.AND).append(BOOK_TITLE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTitle());
                }
                if (findObject.getTranID() != null) {
                    sql.append(DatabaseConstant.AND).append(TRAN_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranID().longValue());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranGroup())) {
                    sql.append(DatabaseConstant.AND).append(TRAN_GROUP).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranGroup());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranCode())) {
                    sql.append(DatabaseConstant.AND).append(TRAN_CODE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranCode());
                }
                if (findObject.getTranRefID() != null) {
                    sql.append(DatabaseConstant.AND).append(TRAN_REF_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranRefID().longValue());
                }

            }

            sql.append(" order by ").append(CREATE_DATE).append(" DESC ");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getTranID()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getTranID()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    @Override
    public BigInteger insert(List<TranBook> insertObjectList) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder prepareObject = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {

            sql.append(" insert into ").append(TABLE).append(" (").append(IS_DELETE).append(DatabaseConstant.SIGN_COMMA).append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA).append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA);

            sql.append(BOOK_ID).append(DatabaseConstant.SIGN_COMMA).append(BOOK_NAME).append(DatabaseConstant.SIGN_COMMA).append(BOOK_TITLE).append(DatabaseConstant.SIGN_COMMA).append(TRAN_ID).append(DatabaseConstant.SIGN_COMMA).append(TRAN_GROUP).append(DatabaseConstant.SIGN_COMMA).append(TRAN_CODE).append(DatabaseConstant.SIGN_COMMA).append(TRAN_REF_ID).append(")");

            if (insertObjectList != null && !insertObjectList.isEmpty()) {
                int size = insertObjectList.size();
                sql.append(" values ");
                for (int i = 0; i < size; i++) {
                    TranBook insertObj = insertObjectList.get(i);

                    sql.append(" ( ").append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA).append(DatabaseConstant.SIGN_QUESTION_MARK).append(" ) ");

                    if (i < size - 1) {
                        sql.append(DatabaseConstant.SIGN_COMMA);
                    }

                    //setup object statement
                    parameters.add(insertObj.getIsDelete());
                    parameters.add(insertObj.getCreateDate());
                    parameters.add(insertObj.getCreateBy());

                    parameters.add(insertObj.getBookID() != null ? insertObj.getBookID().longValue() : null);
                    parameters.add(insertObj.getName());
                    parameters.add(insertObj.getTitle());

                    parameters.add(insertObj.getTranID() != null ? insertObj.getTranID().longValue() : null);
                    parameters.add(insertObj.getTranGroup());
                    parameters.add(insertObj.getTranCode());

                    parameters.add(insertObj.getTranRefID() != null ? insertObj.getTranRefID().longValue() : null);

                }
            }

            sql.append(prepareObject.toString());

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            jdbcTemplate.update(sql.toString(), parameters.toArray());

            BigInteger lastInsertedId = BigInteger.valueOf(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
            return lastInsertedId;

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }

    @Override
    public TranBook findById(BigInteger id) throws Exception {
        StringBuilder sql = new StringBuilder();
        TranBook resultTranBook = null;
        List<Object> parameters = new ArrayList<>();

        String refID = id != null ? String.valueOf(id) : null;
        try {
            sql.append("SELECT * ").append(DatabaseConstant.FROM).append(TABLE);
            sql.append(DatabaseConstant.WHERE_0_EQUAL_0).append(DatabaseConstant.AND).append(TRAN_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(id);

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            List<TranBook> resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

            if (!resultList.isEmpty()) {
                resultTranBook = resultList.get(0);
            }

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(refID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(refID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultTranBook;
    }


}
