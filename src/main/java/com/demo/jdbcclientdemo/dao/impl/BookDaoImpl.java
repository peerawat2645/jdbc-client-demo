package com.demo.jdbcclientdemo.dao.impl;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DatabaseConstant;
import com.demo.jdbcclientdemo.dao.BookDao;
import com.demo.jdbcclientdemo.domain.Book;
import com.demo.jdbcclientdemo.domain.TranAll;
import com.demo.jdbcclientdemo.domain.TranBook;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "book";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String BOOK_ID = "book_id";
    private final String BOOK_NAME = "book_name";
    private final String BOOK_TITLE = "book_title";

    private final String TRAN_ID_GENERATE = "tran_id_generate";
    private final String TRAN_ID_VERIFY = "tran_id_verify";
    private final String TRAN_STATUS_CODE = "tran_status_code";
    private final String TRAN_STATUS_GROUP = "tran_status_group";

    final RowMapper<Book> ROW_MAPPER = (ResultSet rs, int i) -> {
        final Book mapperObject = new Book();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setBookID(rs.getObject(BOOK_ID) != null ? BigInteger.valueOf(rs.getLong(BOOK_ID)) : null);
        mapperObject.setName(rs.getString(BOOK_NAME));
        mapperObject.setTitle(rs.getString(BOOK_TITLE));

        mapperObject.setTranIDGenerate(rs.getObject(TRAN_ID_GENERATE) != null ? BigInteger.valueOf(rs.getLong(TRAN_ID_GENERATE)) : null);
        mapperObject.setTranIDVerify(rs.getObject(TRAN_ID_VERIFY) != null ? BigInteger.valueOf(rs.getLong(TRAN_ID_VERIFY)) : null);
        mapperObject.setTranStatusCode(rs.getString(TRAN_STATUS_CODE));
        mapperObject.setTranStatusGroup(rs.getString(TRAN_STATUS_GROUP));

        return mapperObject;
    };
    @Override
    public List<Book> findAll() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Book> resultList = new ArrayList<>();

        try {
            sql.append("SELECT *").append(DatabaseConstant.FROM).append(TABLE);
            //sql.append(DatabaseConstant.WHERE_0_EQUAL_0);
            sql.append(" ORDER BY ").append(CREATE_DATE).append(" DESC");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());

            resultList = jdbcTemplate.query(sql.toString(), ROW_MAPPER);
        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    public Page<Book> findAll(Pageable pageable) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Book> resultList;

        try {
            sql.append("SELECT *").append(DatabaseConstant.FROM).append(TABLE);
            //sql.append(DatabaseConstant.WHERE_0_EQUAL_0);

            sql.append(" ORDER BY ").append(CREATE_DATE).append(" DESC");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());

            resultList = jdbcTemplate.query(sql.toString(), ROW_MAPPER);

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > resultList.size() ? resultList.size() : (start + pageable.getPageSize());

            return new PageImpl<>(resultList.subList(start, end), pageable, resultList.size());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }

    public Page<Book> findAllByName(String name, Pageable pageable) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Book> resultList;

        try {
            sql.append("SELECT *").append(DatabaseConstant.FROM).append(TABLE);
            sql.append(" WHERE ").append(BOOK_NAME).append(" LIKE ?"); // Adjust to your database's wildcard syntax

            // Additional search conditions or parameters can be added here
            // For example:
            // sql.append(" AND ").append(ANOTHER_COLUMN).append(" = ?");

            sql.append(" ORDER BY ").append(CREATE_DATE).append(" DESC");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());

            // Pass the name parameter to the query
            resultList = jdbcTemplate.query(sql.toString(), ROW_MAPPER, "%" + name + "%"); // Adjust to your database's wildcard syntax

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > resultList.size() ? resultList.size() : (start + pageable.getPageSize());

            return new PageImpl<>(resultList.subList(start, end), pageable, resultList.size());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }


    @Override
    public List<Book> find(Book findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Book> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(CREATE_DATE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getCreateBy())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(CREATE_BY)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateBy());
                }
                if (findObject.getUpdateDate() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(UPDATE_DATE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUpdateBy())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(UPDATE_BY)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateBy());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getIsDelete())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(IS_DELETE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getIsDelete());
                }
                if (findObject.getBookID() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(BOOK_ID)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getBookID().longValue());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getName())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(BOOK_NAME)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getName());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTitle())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(BOOK_TITLE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTitle());
                }
                if (findObject.getTranIDGenerate() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_ID_GENERATE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranIDGenerate().longValue());
                }
                if (findObject.getTranIDVerify() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_ID_VERIFY)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranIDVerify());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranStatusCode())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_STATUS_CODE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranStatusCode());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranStatusGroup())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_STATUS_GROUP)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranStatusGroup());
                }

            }

            sql.append(" order by ").append(CREATE_DATE).append(" DESC ");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY,
                    sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS,
                    Arrays.toString(parameters.toArray()));

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getBookID()) : null,
                    CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getBookID()) : null,
                    CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    @Override
    public Book findById(BigInteger id) throws Exception {
        StringBuilder sql = new StringBuilder();
        Book resultBook = null;
        List<Object> parameters = new ArrayList<>();

        try {
            sql.append("SELECT * ").append(DatabaseConstant.FROM).append(TABLE);
            sql.append(DatabaseConstant.WHERE_0_EQUAL_0).append(DatabaseConstant.AND)
                    .append(BOOK_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(id);

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            List<Book> resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

            if (!resultList.isEmpty()) {
                resultBook = resultList.get(0);
            }

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(id != null ? String.valueOf(id) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(id != null ? String.valueOf(id) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultBook;
    }


    @Override
    public void insert(Book insertObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {

            //setup column
            sql
                    .append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA)
                    .append(IS_DELETE).append(DatabaseConstant.SIGN_COMMA);

            sql
                    .append(BOOK_ID).append(DatabaseConstant.SIGN_COMMA)
                    .append(BOOK_NAME).append(DatabaseConstant.SIGN_COMMA)
                    .append(BOOK_TITLE).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_ID_GENERATE).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_ID_VERIFY).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_STATUS_GROUP).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_STATUS_CODE)
                    .append(")");

            if (insertObject != null) {
                sql.append(" values ");
                    //setup prepareStatement
                    sql.append(" ( ")
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)

                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(" ) ");

                    //setup object statement
                    parameters.add(insertObject.getCreateDate());
                    parameters.add(insertObject.getCreateBy());
                    parameters.add(insertObject.getIsDelete());

                    parameters.add(null != insertObject.getBookID() ? insertObject.getBookID().longValue() : null);
                    parameters.add(insertObject.getName());
                    parameters.add(insertObject.getTitle());
                    parameters.add(null != insertObject.getTranIDGenerate() ? insertObject.getTranIDGenerate().longValue() : null);
                    parameters.add(null != insertObject.getTranIDVerify() ? insertObject.getTranIDVerify().longValue() : null);
                    parameters.add(insertObject.getTranStatusGroup());
                    parameters.add(insertObject.getTranStatusCode());
            }

            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }

    @Override
    public void update(Book updateObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {
            //setup update columns
            sql.append("UPDATE ").append(TABLE).append(" SET ")
                    .append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(BOOK_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(BOOK_NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(BOOK_TITLE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_ID_GENERATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_ID_VERIFY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_STATUS_GROUP).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_STATUS_CODE).append(DatabaseConstant.EQUAL_QUESTION_MARK);

            sql.append(DatabaseConstant.WHERE_0_EQUAL_0).append(DatabaseConstant.AND)
                    .append(BOOK_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(updateObject.getUpdateDate());
            parameters.add(updateObject.getUpdateBy());
            parameters.add(updateObject.getBookID());
            parameters.add(updateObject.getName());
            parameters.add(updateObject.getTitle());
            parameters.add(updateObject.getTranIDGenerate());
            parameters.add(updateObject.getTranIDVerify());
            parameters.add(updateObject.getTranStatusGroup());
            parameters.add(updateObject.getTranStatusCode());
            parameters.add(updateObject.getBookID());

            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }


    @Override
    public void deleteById(BigInteger bookID) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {
            // Setup delete query
            sql.append("DELETE ").append(DatabaseConstant.FROM).append(TABLE).append(DatabaseConstant.WHERE_0_EQUAL_0)
                    .append(DatabaseConstant.AND).append(BOOK_ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(bookID);

            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(CommonConstant.SID, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }
}
