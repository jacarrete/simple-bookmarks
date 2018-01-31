package com.simple.bookmarks.dao;

import com.simple.bookmarks.model.Bookmark;

import java.util.List;

/**
 * Created by jcarretero on 23/01/2018.
 */
public interface BookmarkDao {
    List<Bookmark> getAllBookmarks();

    Bookmark getBookmark(int id);

    Bookmark getBookmarkByName(String name);

    List<Bookmark> getBookmarksByUsername(String username);

    Bookmark addBookmark(Bookmark bookmark);

    void updateBookmark(Bookmark bookmark);

    void deleteBookmark(int id);

}