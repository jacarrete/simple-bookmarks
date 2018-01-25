package com.simple.bookmarks.service;

import com.simple.bookmarks.dao.BookmarkDao;
import com.simple.bookmarks.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by jcarretero on 23/01/2018.
 */
@Service("bookmarkService")
public class BookmarkService {

    @Autowired
    private BookmarkDao bookmarkDao;

    @Transactional
    public List<Bookmark> getAllBookmarks() {
        return bookmarkDao.getAllBookmarks();
    }

    @Transactional
    public Bookmark getBookmark(int id) {
        return bookmarkDao.getBookmark(id);
    }

    @Transactional
    public void addBookmark(Bookmark bookmark) {
        bookmarkDao.addBookmark(bookmark);
    }

    @Transactional
    public void updateBookmark(Bookmark bookmark) {
        bookmarkDao.updateBookmark(bookmark);
    }

    @Transactional
    public void deleteBookmark(int id) {
        bookmarkDao.deleteBookmark(id);
    }

}