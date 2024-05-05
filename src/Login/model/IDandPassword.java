package Login.model;

import java.util.HashMap;

public class IDandPassword {

    private HashMap<String,String> loginData = new HashMap<>();

    public IDandPassword(){
        loginData.put("Dottore", "password1");
        loginData.put("Segretaria", "password2");
        loginData.put("Amministratore", "admin");
        loginData.put("demo", "demo");
    }

    public HashMap getLoginData(){
        return loginData;
    }
}
