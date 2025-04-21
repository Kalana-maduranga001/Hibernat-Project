package lk.ijse.gdse.project.hibernate_project.bo.exeception;



public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
