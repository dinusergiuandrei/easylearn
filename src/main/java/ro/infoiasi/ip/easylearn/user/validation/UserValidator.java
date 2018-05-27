package ro.infoiasi.ip.easylearn.user.validation;

import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.user.model.User;

@Component
public class UserValidator {
    public UserValidator(){};
    public boolean validateUserUpdate(User user){
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (user.getName().length() < 3 || user.getName() == null)
            return false;
        if (user.getFirstName().length() < 3 || user.getFirstName() == null)
            return false;
        if ((!user.getEmail().matches(pattern)) || user.getEmail() == null)
            return false;
        return true;
    };

    public boolean validateUserRegister(User user){
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (user.getName().length() < 3 || user.getName() == null)
            return false;
        if (user.getFirstName().length() < 3 || user.getFirstName() == null)
            return false;
        if ((!user.getEmail().matches(pattern)) || user.getEmail() == null)
            return false;
        if (user.getPassword().length() < 4 || user.getPassword() == null)
            return false;
        if (user.getSecretAnswer().length() < 4 || user.getSecretAnswer() == null)
            return false;
        if (user.getSecretQuestion().length() < 4 || user.getSecretQuestion() == null)
            return false;
        return true;
    };
}
