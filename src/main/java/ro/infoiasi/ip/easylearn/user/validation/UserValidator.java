package ro.infoiasi.ip.easylearn.user.validation;

import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.user.model.User;

@Component
public class UserValidator {
    public UserValidator() {}

    public boolean validateUserUpdate(User user) {
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (user.getName() == null || user.getName().length() < 3)
            return false;
        if (user.getFirstName() == null || user.getFirstName().length() < 3)
            return false;
        if (user.getEmail() == null || (!user.getEmail().matches(pattern)))
            return false;
        return true;
    }

    public boolean validateUserRegister(User user) {
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (user.getName() == null || user.getName().length() < 3)
            return false;
        if (user.getFirstName() == null || user.getFirstName().length() < 3)
            return false;
        if (user.getEmail() == null || (!user.getEmail().matches(pattern)))
            return false;
        if (user.getPassword() == null || user.getPassword().length() < 4)
            return false;
        if (user.getSecretAnswer() == null || user.getSecretAnswer().length() < 4)
            return false;
        if (user.getSecretQuestion() == null || user.getSecretQuestion().length() < 4)
            return false;
        return true;
    }

}
