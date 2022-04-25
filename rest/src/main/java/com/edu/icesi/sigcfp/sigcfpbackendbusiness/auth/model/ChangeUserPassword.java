package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model;

public class ChangeUserPassword {
    private String userName;
    private String oldUserPassword;
    private String newUserPassword;
    private String confirmPassword;

    public ChangeUserPassword() {

    }

    public ChangeUserPassword(String userName, String oldUserPassword, String newUserPassword, String confirmPassword) {
        this.userName = userName;
        this.oldUserPassword = oldUserPassword;
        this.newUserPassword = newUserPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldUserPassword() {
        return oldUserPassword;
    }

    public void setOldUserPassword(String oldUserPassword) {
        this.oldUserPassword = oldUserPassword;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
