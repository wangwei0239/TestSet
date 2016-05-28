// IBookManager.aidl
package com.example.wangwei.testbinder;

import com.example.wangwei.testbinder.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
