package com.example.mad_project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    //IT19076362 - Test cases for check valid username and email
    /* User email should be an valid email address
       It can only contains a-z letters, A-Z letters, 0-9 numbers and @ symbol
       It must contain @ symbol
    * */
    @Test
    public void isValidEmail() {
        boolean result = StudentRegisterActivity.emailValidate("test@gmail.com");
        assertTrue(result);
        boolean result1 = StudentRegisterActivity.emailValidate("test");
        assertFalse(result1);
    }

    /* User username should be an valid username
       It can only contains a-z letters, A-Z letters, 0-9 numbers
       It must contain letters and numbers
    * */
    @Test
    public void isValidUsername() {
        boolean result = StudentRegisterActivity.usernameValidate("test01");
        assertTrue(result);
        boolean result1 = StudentRegisterActivity.usernameValidate("test%%");
        assertFalse(result1);
    }

    
    /*IT18125580 - Test Cases for validation email in Add Question*/
    @Test
    public void emailValidationQuestion() {
        boolean check = AddQuestion.emailValidation("test@gmail.com");
        assertTrue(check);
        boolean check2 = AddQuestion.emailValidation("check");
        assertFalse(check2);
    }


    //IT19002484 - Test case for check whether update lesson name is valid
    /*lesson name can not contain any numbers
    it can only contain letters fron a-z and A-Z
     */
    @Test
    public void LessonNameValidate() {
        boolean result = Admin_lesson_update.LessonNameValidate("Java");
        assertTrue(result);
        boolean result1 = Admin_lesson_update.LessonNameValidate("JAVA101");
        assertFalse(result1);
    }
}