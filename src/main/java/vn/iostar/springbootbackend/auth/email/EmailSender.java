package vn.iostar.springbootbackend.auth.email;

public interface EmailSender {
    void send(String to, String email);
}
