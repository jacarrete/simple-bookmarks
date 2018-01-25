package com.simple.bookmarks.dao;

import com.simple.bookmarks.model.Bookmark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jcarretero on 23/01/2018.
 */
@Repository
public class BookmarkDaoImpl implements BookmarkDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<Bookmark> getAllBookmarks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Bookmark>  bookmarkList = session.createQuery("from Bookmark").list();
        return bookmarkList;
    }

    public Bookmark getBookmark(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Bookmark bookmark = (Bookmark) session.get(Bookmark.class, id);
        return bookmark;
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(bookmark);
        return bookmark;
    }

    public void updateBookmark(Bookmark bookmark) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(bookmark);
    }

    public void deleteBookmark(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Bookmark p = (Bookmark) session.load(Bookmark.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }
}
