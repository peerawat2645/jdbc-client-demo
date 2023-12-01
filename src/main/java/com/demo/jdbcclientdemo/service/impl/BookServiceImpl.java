package com.demo.jdbcclientdemo.service.impl;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DateTimeConstant;
import com.demo.jdbcclientdemo.constant.ExceptionConstant;
import com.demo.jdbcclientdemo.dao.BookDao;
import com.demo.jdbcclientdemo.dao.TranAllDao;
import com.demo.jdbcclientdemo.dao.TranBookDao;
import com.demo.jdbcclientdemo.domain.Book;
import com.demo.jdbcclientdemo.domain.TranAll;
import com.demo.jdbcclientdemo.domain.TranBook;
import com.demo.jdbcclientdemo.exceptions.ServiceException;
import com.demo.jdbcclientdemo.model.rest.BaseResponse;
import com.demo.jdbcclientdemo.model.rest.request.InsertBookRequest;
import com.demo.jdbcclientdemo.model.rest.request.UpdateBookRequest;
import com.demo.jdbcclientdemo.model.rest.response.GetBookPageableResponse;
import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import com.demo.jdbcclientdemo.service.BookService;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.DateUtil;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private TranAllDao tranAllDao;
    @Autowired
    private TranBookDao tranBookDao;

    @Override
    public GetBookResponse getAll() throws Exception {
        GetBookResponse response = new GetBookResponse();
        Date currentDate = new Date();
        try {
            List<Book> bookListDB = bookDao.findAll();
            if (bookListDB != null && !bookListDB.isEmpty()) {
                response.setBooks(bookListDB);
            }
            System.out.println(response);

            response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            response.setStatus(CommonConstant.SUCCESS_CODE);
            response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getName(), e);
            throw new DatabaseException(ExceptionConstant.DATABASE_EXCEPTION);

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getSimpleName(), e);
            throw new ServiceException(ExceptionConstant.SERVICE_EXCEPTION);
        }
        return response;
    }

    public GetBookPageableResponse getAll(int page) throws Exception {
        Date currentDate = new Date();
        try {
            // Create a Pageable object for pagination
            Pageable pageable = PageRequest.of(page - 1, CommonConstant.SIZE);

            // Fetch a page of books from the repository using Pageable
            Page<Book> bookPage = (Page<Book>) bookDao.findAll(pageable);

            GetBookPageableResponse getBookPageableResponse = new GetBookPageableResponse();

            getBookPageableResponse.setBooks(bookPage);

            // Construct a response object containing the page of books
            return getBookPageableResponse;

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getName(), e);
            throw new DatabaseException(ExceptionConstant.DATABASE_EXCEPTION);
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getSimpleName(), e);
            throw new ServiceException(ExceptionConstant.SERVICE_EXCEPTION);
        }
    }

    public GetBookPageableResponse getAllByName(String name, int page) throws Exception {
        Date currentDate = new Date();
        try {
            // Create a Pageable object for pagination
            Pageable pageable = PageRequest.of(page - 1, CommonConstant.SIZE);

            // Fetch a page of books from the repository using Pageable
            Page<Book> bookPage = (Page<Book>) bookDao.findAllByName(name, pageable);

            GetBookPageableResponse getBookPageableResponse = new GetBookPageableResponse();

            getBookPageableResponse.setBooks(bookPage);

            // Construct a response object containing the page of books
            return getBookPageableResponse;

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getName(), e);
            throw new DatabaseException(ExceptionConstant.DATABASE_EXCEPTION);
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getSimpleName(), e);
            throw new ServiceException(ExceptionConstant.SERVICE_EXCEPTION);
        }
    }

    @Override
    public GetBookResponse insert(InsertBookRequest insertBookRequest) throws Exception {
        Date currentDate = new Date();

        //1) Insert data to main transaction.
        insertTransactionMain(CommonConstant.TRAN_CREATE_BOOK, CommonConstant.FLAG_N, null, currentDate);

        TranAll tranAll = findTransactionMain(null, CommonConstant.TRAN_CREATE_BOOK, CommonConstant.FLAG_N, null, null, null, currentDate);

        Book book = new Book();
        book.setCreateDate(currentDate);
        book.setCreateBy(CommonConstant.SYSTEM);
        book.setIsDelete(CommonConstant.FLAG_N);

        book.setName(insertBookRequest.getName());
        book.setTitle(insertBookRequest.getTitle());
        book.setTranIDGenerate(null);
        book.setTranIDVerify(null);

        book.setTranStatusGroup(CommonConstant.TRAN_GROUP);
        book.setTranStatusCode(CommonConstant.FLAG_ACTIVE);

        bookDao.insert(book);
        loggerService.systemLogger(CommonConstant.SID, "Insert book complete.",
                book);

        book = findBook(null, book.getName(), book.getTitle(), null, null, null, null, currentDate);

        insertTransactionBookMain(CommonConstant.FLAG_N, book.getBookID(), book.getName(), book.getTitle(), CommonConstant.TRAN_CREATE_BOOK, tranAll.getTranID(), null, currentDate);

        book.setTranIDGenerate(tranAll.getTranID());

        bookDao.update(book);

        List<Book> bookList = new ArrayList<>();

        bookList.add(book);

        GetBookResponse response = new GetBookResponse();
        response.setBooks(bookList);
        response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        response.setStatus(CommonConstant.SUCCESS_CODE);
        response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));

        return response;
    }

    @Override
    public GetBookResponse update(UpdateBookRequest updateBookRequest) throws Exception {
        Date currentDate = new Date();

        Book book = bookDao.findById(updateBookRequest.getBookID());

        TranBook tranBook = findTransactionBookMain(book.getBookID(), CommonConstant.FLAG_N, null, null
                , null, null, book.getName(), book.getTitle(), book.getTranIDGenerate()
                , null);

        insertTransactionMain(CommonConstant.TRAN_UPDATE_BOOK, CommonConstant.FLAG_N, tranBook.getTranID(), currentDate);

        TranAll tranAll = findTransactionMain(null, CommonConstant.TRAN_UPDATE_BOOK, CommonConstant.FLAG_N, null, null, tranBook.getTranID(), currentDate);

        insertTransactionBookMain(CommonConstant.FLAG_N, updateBookRequest.getBookID(), updateBookRequest.getName(), updateBookRequest.getTitle(), CommonConstant.TRAN_UPDATE_BOOK, tranAll.getTranID(), tranBook.getTranID(), currentDate);

        book.setName(updateBookRequest.getName());
        book.setTitle(updateBookRequest.getTitle());
        book.setTranIDVerify(tranAll.getTranID());

        bookDao.update(book);

        GetBookResponse response = new GetBookResponse();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        response.setBooks(bookList);
        response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        response.setStatus(CommonConstant.SUCCESS_CODE);
        response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));

        return response;
    }

    @Override
    public GetBookResponse findById(BigInteger bookID) throws Exception {
        Date currentDate = new Date();
        Book book = bookDao.findById(bookID);
        GetBookResponse response = new GetBookResponse();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        response.setBooks(bookList);
        response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        response.setStatus(CommonConstant.SUCCESS_CODE);
        response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));

        return response;
    }

    @Override
    public BaseResponse deleteById(BigInteger bookID) throws Exception {
        BaseResponse response = new BaseResponse();

        Book book = bookDao.findById(bookID);
        if (book != null) {
            Date currentDate = new Date();
            TranBook tranBook = tranBookDao.findById(book.getTranIDVerify());
            TranAll tranAll = tranAllDao.findById(tranBook.getTranRefID());
            insertTransactionMain(CommonConstant.TRAN_DELETE_BOOK, CommonConstant.FLAG_Y, tranAll.getTranID(), currentDate);
            tranAll = findTransactionMain(null, CommonConstant.TRAN_DELETE_BOOK, CommonConstant.FLAG_Y, null, null, tranAll.getTranID(), currentDate);
            insertTransactionBookMain(CommonConstant.FLAG_Y, null, book.getName(), book.getTitle(), CommonConstant.TRAN_DELETE_BOOK, tranAll.getTranID(), tranAll.getTranID(), currentDate);
            bookDao.deleteById(bookID);

            response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            response.setStatus(CommonConstant.SUCCESS_CODE);
            response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));
            response.setMessage(CommonConstant.RESPONSE_SUCCESS);
        }
        return response;
    }

    private void insertTransactionMain(String tranCode, String isDelete, BigInteger tranRefID, Date date) throws Exception {


        TranAll tranAllInsertObj = new TranAll();
        tranAllInsertObj.setCreateDate(date);
        tranAllInsertObj.setCreateBy(CommonConstant.SYSTEM);
        tranAllInsertObj.setIsDelete(isDelete);

        tranAllInsertObj.setTranGroup(CommonConstant.TRAN_GROUP);
        tranAllInsertObj.setTranCode(tranCode);
        tranAllInsertObj.setTranRefID(tranRefID);
        tranAllInsertObj.setSid(CommonConstant.SID);

        List<TranAll> tranAllInsertObjList = new ArrayList<>();
        tranAllInsertObjList.add(tranAllInsertObj);
        tranAllDao.insert(tranAllInsertObjList);
        loggerService.systemLogger(CommonConstant.SID, "Insert transaction main complete.",
                tranAllInsertObj);
    }

    private TranAll findTransactionMain(BigInteger tranID, String tranCode, String isDelete, Date updateDate, String updateBy, BigInteger tranRefID, Date date) throws Exception {

        TranAll tranAllFindObj = new TranAll();
        tranAllFindObj.setTranID(tranID);
        tranAllFindObj.setCreateDate(date);
        tranAllFindObj.setCreateBy(CommonConstant.SYSTEM);
        tranAllFindObj.setIsDelete(isDelete);
        tranAllFindObj.setUpdateDate(updateDate);
        tranAllFindObj.setUpdateBy(updateBy);

        tranAllFindObj.setTranGroup(CommonConstant.TRAN_GROUP);
        tranAllFindObj.setTranCode(tranCode);
        tranAllFindObj.setTranRefID(tranRefID);
        tranAllFindObj.setSid(CommonConstant.SID);

        List<TranAll> tranAllList = tranAllDao.find(tranAllFindObj);
        loggerService.systemLogger(CommonConstant.SID, "find transaction main complete.",
                tranAllFindObj);
        return tranAllList.get(0);
    }

    private Book findBook(BigInteger bookID, String name, String title, BigInteger tranIDGenerate, BigInteger tranIDVerify, Date updateDate, String updateBy, Date date) throws Exception {

        Book bookFindObj = new Book();
        bookFindObj.setBookID(bookID);
        bookFindObj.setCreateDate(date);
        bookFindObj.setCreateBy(CommonConstant.SYSTEM);
        bookFindObj.setIsDelete(CommonConstant.FLAG_N);
        bookFindObj.setUpdateDate(updateDate);
        bookFindObj.setUpdateBy(updateBy);

        bookFindObj.setName(name);
        bookFindObj.setTitle(title);
        bookFindObj.setTranIDGenerate(tranIDGenerate);
        bookFindObj.setTranIDVerify(tranIDVerify);
        bookFindObj.setTranStatusCode(CommonConstant.TRAN_GROUP);
        bookFindObj.setTranStatusCode(CommonConstant.FLAG_ACTIVE);

        List<Book> bookList = bookDao.find(bookFindObj);
        loggerService.systemLogger(CommonConstant.SID, "find book complete.",
                bookFindObj);
        return bookList.get(0);
    }

    private void insertTransactionBookMain(String isDelete, BigInteger bookID, String name, String title, String tranCode, BigInteger tranID, BigInteger tranRefID, Date date) throws Exception {


        TranBook tranBookInsertObj = new TranBook();
        tranBookInsertObj.setCreateDate(date);
        tranBookInsertObj.setCreateBy(CommonConstant.SYSTEM);
        tranBookInsertObj.setIsDelete(isDelete);

        tranBookInsertObj.setBookID(bookID);
        tranBookInsertObj.setName(name);
        tranBookInsertObj.setTitle(title);
        tranBookInsertObj.setTranGroup(CommonConstant.TRAN_GROUP);
        tranBookInsertObj.setTranCode(tranCode);
        tranBookInsertObj.setTranRefID(tranRefID);
        tranBookInsertObj.setTranID(tranID);

        List<TranBook> tranBookInsertObjList = new ArrayList<>();
        tranBookInsertObjList.add(tranBookInsertObj);
        tranBookDao.insert(tranBookInsertObjList);
        loggerService.systemLogger(CommonConstant.SID, "Insert transaction book complete.",
                tranBookInsertObjList);
    }

    private TranBook findTransactionBookMain(BigInteger bookID, String isDelete, Date updateDate, String updateBy, String tranCode, BigInteger tranRefID, String name, String title, BigInteger tranID, Date date) throws Exception {


        TranBook tranBookFindObj = new TranBook();
        tranBookFindObj.setCreateDate(date);
        tranBookFindObj.setCreateBy(CommonConstant.SYSTEM);
        tranBookFindObj.setIsDelete(isDelete);
        tranBookFindObj.setUpdateDate(updateDate);
        tranBookFindObj.setUpdateBy(updateBy);

        tranBookFindObj.setBookID(bookID);
        tranBookFindObj.setName(name);
        tranBookFindObj.setTitle(title);
        tranBookFindObj.setTranGroup(CommonConstant.TRAN_GROUP);
        tranBookFindObj.setTranCode(tranCode);
        tranBookFindObj.setTranRefID(tranRefID);
        tranBookFindObj.setTranID(tranID);

        TranBook tranBook = tranBookDao.find(tranBookFindObj).get(0);
        loggerService.systemLogger(CommonConstant.SID, "Insert transaction book complete.",
                tranBookFindObj);
        return tranBook;
    }


}
