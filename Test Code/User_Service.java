package net.codejava.user;
 
@Service
@Transactional
public class User_Service {

    @Autowired
    private UserRepository repo;
     
    public void updateAuthenticationType(String username, String Oauth_2_Client_Name) {
    
        AuthenticationType authType = AuthenticationType.valueOf(Oauth_2_Client_Name.toUpperCase());
    
        repo.updateAuthenticationType(username, authType);
    
    }  

}