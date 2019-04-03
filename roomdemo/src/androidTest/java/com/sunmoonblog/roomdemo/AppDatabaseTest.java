package com.sunmoonblog.roomdemo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sunmoonblog.roomdemo.demo2.Book;
import com.sunmoonblog.roomdemo.demo2.BookDao;
import com.sunmoonblog.roomdemo.demo2.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {

    private PersonDao mPersonDao;

    private BookDao mBookDao;

    private UserDao mUserDao;

    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mUserDao = db.getUserDao();
        mPersonDao = db.getPersonDao();
        mBookDao = db.getBookDao();
    }

    @After
    public void close() {
        db.close();
    }

    @Test
    public void dao() {
        assertNotNull(mUserDao);
        assertNotNull(mPersonDao);
        assertNotNull(mBookDao);
    }

    @Test
    public void testBook() {
        Book book = new Book();
        book.setName("挪威的森林");
        book.setPrice(20.0);
        book.setAuthor("村上春树");
        mBookDao.insertBook(book);

        List<Book> books = mBookDao.getAllBooks();
        assertEquals(1, books.size());

        Book b = books.get(0);
        assertEquals("挪威的森林", b.getName());

        if (b.getPrice() != null) {
            assertEquals(20.0, b.getPrice(), 0.01);
        } else {
            fail();
        }
        assertEquals("村上春树", b.getAuthor());
        System.out.println("test");
    }

}