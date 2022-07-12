package lamb.key.send.entity;

/**
 * @author JoinYang
 * @date 2022/6/1 19:23
 */
public class Sms {
    String phoneNumber;
    String code;
    int min;

    public Sms() {
    }

    public Sms(String phoneNumber, String code, int min) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.min = min;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
