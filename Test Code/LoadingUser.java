package net.codejava.security.oauth;
 
@Service
public class LoeadingUser extends DefaultOAuth2UserService  {
 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        String Client_Name = userRequest.getClientRegistration().getClient_Name();
        
        OAuth2User User =  super.loadUser(userRequest);
        
        return new CustomOAuth2User(User, Client_Name);
    }
 
}

