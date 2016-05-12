package hello.sample.mobile.bpal.ru.helloworldapp.tasks;

/**
 * Created by serg on 13.05.16.
 */
public class CitiesHttpResult {

    public static final String EMPTY_STRING = "";
    public static final int NO_STATUS = -1;

    public int status;
    public String result;

    public CitiesHttpResult() {
        status = NO_STATUS;
        result = EMPTY_STRING;
    }
}
