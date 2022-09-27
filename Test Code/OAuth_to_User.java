package net.codejava.security.oauth;
 
public class OAuth_to_User implements OAuth2User {
    
    private String Oauth_2_Client_Name;
    
    private OAuth_to_User Oauth_2_User;
     
    public OAuth_to_User(OAuth2User oauth2User, String Oauth_2_Client_Name) {
    
        this.Oauth_2_User = Oauth_2_User;
    
        this.Oauth_2_Client_Name = Oauth_2_Client_Name;
    
    }
 
    @Override
    public Map<String, Object> getAttributes() {
    
        return Oauth_2_User.getAttributes();
    
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        return Oauth_2_User.getAuthorities();
    
    }
 
    @Override
    public String getName() {
    
        System.out.println(Oauth_2_User.<String>getAttribute("email"));
    
        return Oauth_2_User.getAttribute("name");
    
    }
 
    public String getEmail() {
    
        return Oauth_2_User.<String>getAttribute("email");     
    
    }
 
    public String getOauth_2_Client_Name() {

        return this.Oauth_2_Client_Name;
    
    }
}