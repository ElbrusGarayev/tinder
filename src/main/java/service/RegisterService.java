package service;

import dao.DAOUser;
import encryption.EncodeDecode;
import entity.User;
import lombok.SneakyThrows;

import java.util.List;

public class RegisterService {
    private final String DEFAULT = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";

    private final DAOUser daoUser;

    public RegisterService(DAOUser daoUser) {
        this.daoUser = daoUser;
    }
    @SneakyThrows
    public String registerUser(String email, String name, String surname,
                               String status, String psw, String pswRepeat, String url) {
        List<User> users = daoUser.getAll();
        if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "<label class=\"warning\">This email has already used!</label>\n";
        } else if (psw.equals(pswRepeat)) {
            if(url.isEmpty()) url = DEFAULT;
            User user = new User(email, name, surname, status, new EncodeDecode().encrypt(psw), url);
            daoUser.insert(user);
            return "Registered";
        }
        return "<label class=\"error\">Password didn't match, try again!</label>\n";
    }
}
