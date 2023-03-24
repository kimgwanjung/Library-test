package kr.ac.kopo.Library;

public interface Book extends Library{
    public void borrow();
    public void returnBook();
    public void bookSearch();
    public void printBookList();
    public void bookInfoChange();
    public void bookAdd();
    public void bookDelete();
}