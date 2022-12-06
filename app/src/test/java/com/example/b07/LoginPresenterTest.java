package com.example.b07;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.jetbrains.annotations.Contract;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginPresenterTest {

    private LoginPresenter loginPresenter;

    private LoginCredentials loginCredentials = new LoginCredentials(
      "ankhaa14", "123");

    @Mock
    private LoginRepositoryIMpl loginRepository;

    @Mock
    private Contract.LoginView loginView;

    @Captor
    private ArgumentCaptor<LoginRepository.LoginListener> loginListnerArgumentCaptor;

    @Before
    public void setUpLoginPresenter(){
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(loginRepository, loginView);
    }

    @Test
    public void login(){
        loginPresenter.login(loginCredentials);
        verify(loginRepository).login(eq(loginCredentials), loginListnerArgumentCaptor.capture());
        loginListnerArgumentCaptor.getValue().onSuccess();
        verify(loginView).onSuccess();
        loginListnerArgumentCaptor.getValue().onFailure("Invalid Credentials");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(loginView).onFailed(argumentCaptor.capture());
        assertEquals("Invalid Credentials", argumentCaptor.getValue());
    }

    public void testExampleWithCorrectValues(){

    }

    public void testExampleWithIncorrectUsername(){

    }

    public void testExampleWithIncorrectPassword(){

    }

    public void testExampleWithIncorrectValues(){

    }

    public void testExampleWithemptyUsername(){

    }

    public void testExampleWithemptyPassword(){

    }

    public void testExampleWithemptyvalues(){

    }
}
