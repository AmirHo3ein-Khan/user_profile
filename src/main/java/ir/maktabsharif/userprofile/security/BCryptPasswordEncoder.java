package ir.maktabsharif.userprofile.security;
import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

public class BCryptPasswordEncoder {
    public static String encodeBcryptPassword(String password) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        Hash hash = Password.hash(password)
                .addPepper()
                .with(bcrypt);
        return hash.getResult();
    }

    public static Boolean validateBcryptPassword(String password, String hashedPassword) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        return Password.check(password, hashedPassword)
                .with(bcrypt);
    }

}
