package capstone.roomof.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcriptImpl {

    public String encrypt(String loginPwd){
        return BCrypt.hashpw(loginPwd, BCrypt.gensalt());
    }

    public boolean comparePwd(String loginPwd, String dbPwd){
        return BCrypt.checkpw(loginPwd, dbPwd);
    }

}
