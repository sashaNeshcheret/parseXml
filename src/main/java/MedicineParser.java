import exceptions.ParseException;

import java.util.ArrayList;
import java.util.List;

public abstract class MedicineParser {
    protected String xmlPath;
    protected List<Person> resultPersons;

    {
        resultPersons = new ArrayList();
    }
    public List<Person> getResultPersons() {
        return resultPersons;
    }

    public abstract void parse() throws ParseException;
}
