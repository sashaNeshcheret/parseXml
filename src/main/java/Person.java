import java.math.BigDecimal;

/**
 * Created by Нещерет on 10.06.2018.
 */
public class Person {
    private static int idStat;
    private Integer id;
    private String name;
    private String address;
    private BigDecimal cash;
    private String education;

    public Person() {
    }

    public Person(String name, String address, BigDecimal cash, String education) {
        id = idStat++;
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public BigDecimal getCash() {
        return cash;
    }
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
}
