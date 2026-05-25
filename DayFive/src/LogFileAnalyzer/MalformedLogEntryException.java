package LogFileAnalyzer;

public class MalformedLogEntryException extends Exception{
    
    public MalformedLogEntryException(String message){
        super(message);
    }
}
