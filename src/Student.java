public class Student {
    Student(int ID) {
        this.ID = ID;
    }

    private int ID;
    private int takenBooks = 0;

    int getTakenBooks(){
        return this.takenBooks;
    }

    void setTakenBooks(){
        this.takenBooks++;
    }
}