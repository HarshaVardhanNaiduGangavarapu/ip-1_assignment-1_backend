package net.codejava.user;
 
public interface user_repo extends CrudRepository<User, Long> {
     
    @Modifying
    @Query("UPDATE Users u and SET u.AuthType = ?2 WHERE u.UserName = ?1")

    public void updateAuthenticationType(String UserName, AuthenticationType AuthType);
}